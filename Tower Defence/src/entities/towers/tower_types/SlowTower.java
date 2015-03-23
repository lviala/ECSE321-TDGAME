package entities.towers.tower_types;

import data.Textures;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 7:18 AM
 */

public class SlowTower extends Tower {
    public SlowTower(vec2 pos, CritterManager critterManager, Projectile.Type type, int baseCost) {
        super(Textures.SLOW_TOWER_TEXTURE, pos, critterManager, type, baseCost);
    }
}
