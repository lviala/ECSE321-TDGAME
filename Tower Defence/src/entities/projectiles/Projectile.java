package entities.projectiles;

import core.rendering.Texture;
import data.Textures;
import entities.Entity;
import entities.critters.Critter;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 2/12/2015 - 10:41 PM
 */

public class Projectile extends Entity {

    private float speed = 0.7f;
    private int damage = 10;
    private Critter target;

    private boolean test = true;

    public Projectile(Texture texture, vec2 pos, Critter target) {
        super(texture);

        this.pos.setSelf(pos);
        this.target = target;

    }

    @Override
    public void update(int delta) {
        if (test) {
            if (target.isDead()) {
                test = false;
            }
            vec2 direction = target.getBox().centre().diff(box.centre());
            direction.normalize();

            pos.addSelf(direction.mult(speed).mult(delta));

            if (box.isWithin(target.getBox().centre())) {
                target.damage(damage);
                test = false;
            }
        }
    }

    @Override
    public void draw(){
        if (test) texture.drawCentre(pos.x, pos.y);
    }

    public static Projectile newProjectile(vec2 pos, Critter target){
        return new Projectile(Textures.RED_DOT_TEXTURE, pos, target);
    }
}
