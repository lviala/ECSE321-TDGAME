package entities.towers;

import core.rendering.Texture;
import entities.Entity;
import entities.critters.Critter;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.tower_types.RapidTower;
import entities.towers.tower_types.SlowTower;
import entities.towers.tower_types.SniperTower;
import org.newdawn.slick.particles.ParticleSystem;
import util.vectors.vec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 2/3/2015 - 6:12 PM
 */

public abstract class Tower extends Entity {

    public static enum Type {
        SNIPER(100),
        RAPID(400),
        SLOW(200);

        private int baseCost;

        Type(int cost){
            baseCost = cost;
        }

        public int cost(){
            return baseCost;
        }

    }


    protected boolean hasTarget = false;
    protected Critter target;
    protected long lastShot = 0;

    protected int totalCost = 0;
    protected int range = 500;
    protected int fireRate = 1000;
    protected Projectile.Type projectile_type;

    protected ArrayList<Projectile> projectiles;
    protected ArrayList<Projectile> projectilesToRemove;

    protected CritterManager critterManager;
    protected ParticleSystem particleSystem;

    public Tower(Texture texture, vec2 pos, CritterManager critterManager, ParticleSystem particleSystem, Projectile.Type type, int baseCost) {
        super(texture.deepCopy());
        this.pos.setSelf(pos);
        totalCost += baseCost;

        this.critterManager = critterManager;
        this.particleSystem = particleSystem;

        this.projectile_type = type;
        projectiles = new ArrayList<Projectile>();
        projectilesToRemove = new ArrayList<Projectile>();
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

        for (Projectile p : projectilesToRemove){
            projectiles.remove(p);
        }
    }

    @Override
    public void draw(){

        if (target != null){
            texture.setRotation(target.getPos().getAngle(pos) + 180);
        }

        texture.draw(pos.x, pos.y);
        for (Projectile p : projectiles){
            p.draw();
        }
    }

    public static Tower create(Type type, vec2 pos, CritterManager manager, ParticleSystem particleSystem){
        switch (type){
            case SNIPER:
                return new SniperTower(pos, manager, particleSystem, Projectile.Type.REGULAR, type.cost());
            case RAPID:
                return new RapidTower(pos, manager, particleSystem, Projectile.Type.RAPID, type.cost());
            case SLOW:
                return new SlowTower(pos, manager, particleSystem, Projectile.Type.SLOW, type.cost());
        }

        throw new RuntimeException("Critter type does not exist");
    }

    public void fire(){
        projectiles.add(Projectile.newProjectile(box.centre(), this, target, projectile_type, particleSystem));
    }

    public int getTotalCost(){
        return totalCost;
    }

    public int getSellValue(){
        return (int)(totalCost * 0.75);
    }

    public void removeProjectile(Projectile projectile){
        projectilesToRemove.add(projectile);
    }

    public void destroyTower(){
        for (Projectile p : projectiles){
            p.destroy();
        }
        projectiles.clear();
        projectilesToRemove.clear();
    }
}
