package entities;

import core.rendering.Texture;
import map.path.PathTile;
import util.Box;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/27/2015 - 12:42 AM
 */

public class Critter {

    private vec2 pos;
    private Box box;
    private Texture texture;
    private PathTile currentTile;

    private boolean update = true;

    private final float SPEED = 0.5f;


    public Critter(Texture texture){
        this.texture = texture;
        pos = new vec2();

    }

    public void init(PathTile tile){
        currentTile = tile;
        pos.x = tile.getCentre().x;
        pos.y = tile.getCentre().y;
    }

    public void update(int delta){
        if (update){
            movetowards(currentTile.getCentre(), delta);
        }
    }

    public void draw(){
        texture.drawCentre(pos.x, pos.y);
    }



    private void movetowards(vec2 destination, int delta){
        float movespeed = SPEED * delta;

        vec2 move = destination.diff(pos);
        if (move.length() > movespeed){
            move.normalize();
            pos.addSelf(move.mult(movespeed));
        }else{
            if (currentTile.isEnd()){
                destroyCritter();
                return;
            }
            float moveRemaining = movespeed - move.length();
            currentTile = currentTile.getNext();
            move = currentTile.getCentre().diff(pos);
            move.normalize();
            pos.addSelf(move.mult(moveRemaining));
        }
    }

    public void destroyCritter(){
        update = false;
    }

}
