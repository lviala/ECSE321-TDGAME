package entities.projectiles;

import core.rendering.Texture;
import data.Textures;
import entities.Entity;
import entities.critters.Critter;
import entities.projectiles.projectile_types.RapidProjectile;
import entities.projectiles.projectile_types.RegularProjectile;
import entities.projectiles.projectile_types.SlowProjectile;
import entities.towers.Tower;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 2/12/2015 - 10:41 PM
 */

public abstract class Projectile extends Entity {

    public static enum Type {REGULAR, SLOW, RAPID}


    private float speed = 0.7f;
    protected int damage = 10;
    private Critter target;
    private Tower tower;

    protected ConfigurableEmitter emitter;
    private ParticleSystem particleSystem;

    private boolean toRemove = false;

    public Projectile(Texture texture, vec2 pos, Tower tower, Critter target, ParticleSystem patricleSystem) {
        super(texture);

        this.pos.setSelf(pos);
        this.tower = tower;
        this.target = target;

        this.particleSystem = patricleSystem;

    }

    @Override
    public void update(int delta) {
            if (target.isDead()) {
                toRemove = true;
            }
            vec2 direction = target.getBox().centre().diff(box.centre());
            direction.normalize();

            pos.addSelf(direction.mult(speed).mult(delta));

            if (box.isWithin(target.getBox().centre())) {
                applyEffect(target);
                target.damage(damage);
                toRemove = true;
            }
            emitter.setPosition(pos.x, pos.y, false);

        if (toRemove) removeSelf();
    }

    protected abstract void applyEffect(Critter target);

    @Override
    public void draw(){
        texture.drawCentre(pos.x, pos.y);
    }

    public static Projectile newProjectile(vec2 pos, Tower tower, Critter target, Type type, ParticleSystem particleSystem){

        switch (type){
            case REGULAR:
                return new RegularProjectile(Textures.RED_DOT_TEXTURE, pos, tower, target, particleSystem);

            case SLOW:
                return new SlowProjectile(Textures.BLUE_DOT_TEXTURE, pos, tower, target, particleSystem);

            case RAPID:
                return new RapidProjectile(Textures.RED_DOT_TEXTURE, pos, tower, target, particleSystem);

        }

        throw new RuntimeException("Critter type does not exist");

    }

    public ParticleEmitter getEmitter() {
        return emitter;
    }

    private void removeSelf(){
        particleSystem.removeEmitter(emitter);
        tower.removeProjectile(this);
    }

    public void destroy() {
        particleSystem.removeEmitter(emitter);
        emitter = null;
    }
}
