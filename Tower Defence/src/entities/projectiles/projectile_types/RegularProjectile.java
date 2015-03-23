package entities.projectiles.projectile_types;

import core.rendering.Texture;
import entities.critters.Critter;
import entities.projectiles.Projectile;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 6:59 AM
 */

public class RegularProjectile extends Projectile {

    public RegularProjectile(Texture texture, vec2 pos, Critter target) {
        super(texture, pos, target);
    }

    @Override
    protected void applyEffect(Critter target) {

    }

}
