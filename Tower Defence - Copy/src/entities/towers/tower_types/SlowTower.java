package entities.towers.tower_types;

import data.Textures;
import entities.critters.Critter;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import org.newdawn.slick.particles.ParticleSystem;
import util.vectors.vec2;

import java.util.LinkedList;

/**
 * Created by Francis O'Brien - 3/2/2015 - 7:18 AM
 */

public class SlowTower extends Tower {



    public SlowTower(vec2 pos, CritterManager critterManager, ParticleSystem particleSystem, Projectile.Type type, int baseCost) {
        super(Textures.SLOW_TOWER_TEXTURE, pos, critterManager, particleSystem, type, baseCost);
        fireRate /= 1;
    }

    @Override
    public void fire(){
        projectiles.add(Projectile.newProjectile(box.centre(), this, target, projectile_type, particleSystem));

        Critter oldTarget = target;
        target = null;

        LinkedList<Critter> critters = critterManager.getLiveCritters();
        int index = critters.indexOf(oldTarget) + 1;

        for (int i = index; i < critters.size() + index; i++){
            Critter current = critters.get(i % critters.size());
            if (pos.distance(current.getPos()) <= range){
                target = current;
                break;
            }
        }

        if (target == null){
            target = oldTarget;
            hasTarget = true;
        }
    }
}
