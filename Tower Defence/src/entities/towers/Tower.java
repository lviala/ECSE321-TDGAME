package entities.towers;

import core.rendering.Texture;
import entities.Entity;
import entities.critters.Critter;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import util.vectors.vec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 2/3/2015 - 6:12 PM
 */

public class Tower extends Entity {

    protected int range = 500;
    private boolean hasTarget = false;
    private Critter target;
    private int fireRate = 1000;
    private long lastShot = 0;

    private ArrayList<Projectile> projectiles;

    private CritterManager critterManager;

    public Tower(Texture texture, vec2 pos, CritterManager critterManager) {
        super(texture);
        this.pos.setSelf(pos);

        this.critterManager = critterManager;
        projectiles = new ArrayList<Projectile>();
    }

    @Override
    public void update(int delta) {
        if (hasTarget) {
            if (target.isDead() || pos.distance(target.getPos()) > range){
                target = null;
                hasTarget = false;
            }
            else if (System.currentTimeMillis() - lastShot > fireRate){
                fire();
                lastShot = System.currentTimeMillis();
            }
        }else{
            for (Critter c : critterManager.getLiveCritters()) {
                if (pos.distance(c.getPos()) < range) {
                    target = c;
                    hasTarget = true;
                    break;
                }
            }
        }

        for (Projectile p : projectiles){
            p.update(delta);
        }
    }

    @Override
    public void draw(){
        texture.draw(pos.x, pos.y);
        for (Projectile p : projectiles){
            p.draw();
        }
    }

    public void fire(){
        projectiles.add(Projectile.newProjectile(box.centre(), target));

    }
}
