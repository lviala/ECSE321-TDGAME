package entities.towers;

import core.rendering.Texture;
import entities.Entity;
import entities.critters.Critter;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.tower_types.RapidTower;
import entities.towers.tower_types.SlowTower;
import entities.towers.tower_types.SniperTower;
import util.vectors.vec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 2/3/2015 - 6:12 PM
 */

public abstract class Tower extends Entity {

    public static enum Type {
        SNIPER(100),
        RAPID(100),
        SLOW(300);

        private int baseCost;

        Type(int cost){
            baseCost = cost;
        }

        public int cost(){
            return baseCost;
        }

    }


    private boolean hasTarget = false;
    private Critter target;
    private long lastShot = 0;

    protected int totalCost = 0;
    protected int range = 500;
    protected int fireRate = 1000;
    protected Projectile.Type projectile_type;

    private ArrayList<Projectile> projectiles;

    private CritterManager critterManager;

    public Tower(Texture texture, vec2 pos, CritterManager critterManager, Projectile.Type type, int baseCost) {
        super(texture);
        this.pos.setSelf(pos);
        totalCost += baseCost;

        this.critterManager = critterManager;
        this.projectile_type = type;
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

    public static Tower create(Type type, vec2 pos, CritterManager manager){
        switch (type){
            case SNIPER:
                return new SniperTower(pos, manager, Projectile.Type.REGULAR, type.cost());
            case RAPID:
                return new RapidTower(pos, manager, Projectile.Type.REGULAR, type.cost());
            case SLOW:
                return new SlowTower(pos, manager, Projectile.Type.SLOW, type.cost());
        }

        throw new RuntimeException("Critter type does not exist");
    }

    public void fire(){
        projectiles.add(Projectile.newProjectile(box.centre(), target, projectile_type));

    }

    public int getTotalCost(){
        return totalCost;
    }
}
