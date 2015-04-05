package entities.critters;

import core.rendering.Texture;
import data.Textures;
import entities.Entity;
import entities.critters.critter_types.FastCritter;
import entities.critters.critter_types.NormalCritter;
import map.path.PathTile;
import util.vectors.vec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 1/27/2015 - 12:42 AM
 */

public abstract class Critter extends Entity {

    public static enum Type {NORMAL, FAST}

    private static class Effect{
        public Effect(float value, int duration){
            this.value = value;
            this.duration = duration;
        }
        public float value;
        public int duration;
    }

    private PathTile currentTile;

    private CritterManager manager;

    private boolean reachedEnd = false;
    private boolean alive = false;

    protected float speed = 0.15f;
    protected float  DEFAULT_SPEED = 0.2f;
    protected int health = 20;
    protected int reward = 50;

    private ArrayList<Effect> activeEffects = new ArrayList<Effect>();
    private ArrayList<Effect> endedEffects = new ArrayList<Effect>();


    public Critter(Texture texture, CritterManager manager, int level){
        super(texture);
        this.manager = manager;
        health = (int) (health * (1.0f + ((level - 1) / 5.0f)));
    }

    public void spawn(PathTile tile){
        alive = true;
        currentTile = tile;
        pos.x = tile.getCentre().x;
        pos.y = tile.getCentre().y;
    }


    @Override
    public void update(int delta){
        if (!reachedEnd && alive) {
            speed = DEFAULT_SPEED;
            for (Effect e : activeEffects){
                e.duration -= delta;
                if (e.duration <= 0) endedEffects.add(e);
                else{
                    speed *= e.value;
                }
            }
            for (Effect e : endedEffects){
                activeEffects.remove(e);
            }

            movetowards(currentTile.getCentre(), delta);
        }
    }

    @Override
    public void draw(){
        if (alive) {
            texture.drawCentre(pos.x, pos.y);
        }
    }

    public void damage(int damage){
        health -= damage;
        if (health < 1){
            manager.kill(this);
            alive = false;
        }
    }

    public boolean isDead(){
        return !alive;
    }
    public void slow(float value, int duration){
        activeEffects.add(new Effect(value, duration));
    }
    public int getReward() {
        return reward;
    }

    public static Critter create(Type type, CritterManager manager, int level){
        switch (type){
            case NORMAL:
                    return new NormalCritter(Textures.UGLY_CREEP_TEXTURE, manager, level);
            case FAST:
                return new FastCritter(Textures.FAST_CREEP_TEXTURE, manager, level);
        }

        throw new RuntimeException("Critter type does not exist");
    }

    private void movetowards(vec2 destination, int delta){
        float movespeed = speed * delta;

        vec2 move = destination.diff(pos);
        if (move.length() > movespeed){
            move.normalize();
            pos.addSelf(move.mult(movespeed));
        }else{
            if (currentTile.isEnd()){
                manager.reachedEnd(this);
                alive = false;
            }else {
                float moveRemaining = movespeed - move.length();
                currentTile = currentTile.getNext();
                move = currentTile.getCentre().diff(pos);
                move.normalize();
                pos.addSelf(move.mult(moveRemaining));
            }
        }
    }


}
