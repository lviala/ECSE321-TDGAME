package entities;

import core.rendering.Texture;
import util.Box;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 2/12/2015 - 9:40 PM
 */

public abstract class Entity {

    protected vec2 pos;
    protected Box box;
    protected Texture texture;


    public Entity(Texture texture){
        this.texture = texture;

        pos = new vec2();
        box = new Box(pos, texture);
    }

    public abstract void update(int delta);

    public void draw(){
        texture.draw(pos.x, pos.y);
    }

    public vec2 getPos(){
        return pos;
    }

    public Box getBox(){
        return box;
    }

}
