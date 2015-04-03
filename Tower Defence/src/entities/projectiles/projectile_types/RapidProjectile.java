package entities.projectiles.projectile_types;

import core.rendering.Texture;
import entities.critters.Critter;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import org.newdawn.slick.particles.ParticleSystem;
import particles.ParticleUtilities;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 6:59 AM
 */

public class RapidProjectile extends Projectile {

    public RapidProjectile(Texture texture, vec2 pos, Tower tower, Critter target, ParticleSystem particleSystem) {
        super(texture, pos, tower, target, particleSystem);
        damage = 3;
        emitter = ParticleUtilities.createSniperEmitter(pos);
        particleSystem.addEmitter(emitter);
    }

    @Override
    protected void applyEffect(Critter target) {

    }

}
