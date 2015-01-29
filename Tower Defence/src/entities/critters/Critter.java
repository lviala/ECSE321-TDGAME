package entities.critters;

import core.rendering.Texture;
import data.Textures;
import entities.critters.critter_types.NormalCritter;
import map.path.PathTile;
import util.Box;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/27/2015 - 12:42 AM
 */

public abstract class Critter {

    public static enum Type {NORMAL, FAST}

    private vec2 pos;
    private Box box;
    private Texture texture;
    private PathTile currentTile;

    private CritterManager manager;

    private boolean reachedEnd = false;
    private boolean spawned = false;
    private final float SPEED = 0.6f;


    public Critter(Texture texture, CritterManager manager){
        this.texture = texture;
        this.manager = manager;

        pos = new vec2();
        box = new Box(pos, texture);

    }

    public void spawn(PathTile tile){
        spawned = true;
        currentTile = tile;
        pos.x = tile.getCentre().x;
        pos.y = tile.getCentre().y;
    }


    public void update(int delta){
        if (!reachedEnd && spawned) {
            movetowards(currentTile.getCentre(), delta);
        }
    }

    public void draw(){
        if (spawned) {
            texture.drawCentre(pos.x, pos.y);
        }
    }

    public static Critter create(Type type, CritterManager manager){
        switch (type){
            case NORMAL:
                    return new NormalCritter(Textures.UGLY_CREEP_TEXTURE, manager);
        }

        throw new RuntimeException("Critter type does not exist");
    }

    private void movetowards(vec2 destination, int delta){
        float movespeed = SPEED * delta;

        vec2 move = destination.diff(pos);
        if (move.length() > movespeed){
            move.normalize();
            pos.addSelf(move.mult(movespeed));
        }else{
            if (currentTile.isEnd()){
                manager.reachedEnd(this);
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
