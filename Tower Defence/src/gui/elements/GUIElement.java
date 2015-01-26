package gui.elements;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:05 AM
 *
 * Basic abstract class to be extended by all GUI elements
 */

public abstract class GUIElement {

    protected vec2 position;
    protected Texture texture;

    public GUIElement(){
        position = new vec2();
        texture = null;
    }

    public GUIElement(vec2 pos, Texture texture){
        position = new vec2(pos); ///< Deep copy pos to keep pointers with other objects valid
        this.texture = texture;
    }


    /// Setters ///

    /// Set position using x and y floats
    public void setPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    /// Set position using vec2
    public void setPosition(vec2 pos){
        position.x = pos.x;
        position.y = pos.y;
    }

    /// Set texture to new texture
    public void setTexture(Texture texture){
        this.texture = texture;
    }


    /// Getters ///

    public vec2 getPosition(){ return position; }
    public Texture getTexture(){ return texture; }


    /// Abstract methods ///

    public abstract void draw();
}
