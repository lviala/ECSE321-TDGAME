package entities.projectiles.projectile_types;

import core.rendering.Texture;
import entities.critters.Critter;
import entities.projectiles.Projectile;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 7:00 AM
 */

public class SlowProjectile extends Projectile {

    private float slowValue = 0.8f;

    public SlowProjectile(Texture texture, vec2 pos, Critter target) {
        super(texture, pos, target);
        damage = 2;
    }

    @Override
    protected void applyEffect(Critter target) {
        target.slow(slowValue);
    }
}
