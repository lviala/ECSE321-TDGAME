package entities.projectiles.projectile_types;

import core.rendering.Texture;
import entities.critters.Critter;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import org.newdawn.slick.particles.ParticleSystem;
import particles.ParticleUtilities;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 7:00 AM
 */

public class SlowProjectile extends Projectile {

    private float slowValue = 0.6f;
    private int duration = 5000;

    public SlowProjectile(Texture texture, vec2 pos, Tower tower, Critter target, ParticleSystem particleSystem) {
        super(texture, pos, tower, target, particleSystem);
        emitter = ParticleUtilities.createSlowEmitter(pos);
        particleSystem.addEmitter(emitter);
        damage = 2;
    }

    @Override
    protected void applyEffect(Critter target) {
        target.slow(slowValue, duration);
    }
}
