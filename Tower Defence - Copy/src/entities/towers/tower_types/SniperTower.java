package entities.towers.tower_types;

import data.Textures;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import org.newdawn.slick.particles.ParticleSystem;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/2/2015 - 7:18 AM
 */

public class SniperTower extends Tower {
    public SniperTower(vec2 pos, CritterManager critterManager, ParticleSystem particleSystem, Projectile.Type type, int baseCost) {
        super(Textures.SNIPER_TOWER_TEXTURE, pos, critterManager, particleSystem, type, baseCost);
    }
}
