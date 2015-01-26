package util;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 3:06 AM
 *
 * Basic box functionality
 */

public class Box {

    private vec2 position;
    private float width, height;

    /// Standard constructor
    public Box(vec2 pos, float width, float height){
        position = pos;
        this.width = width;
        this.height = height;
    }

    /// Texture constructor gets dimensions directly from texture
    public Box(vec2 pos, Texture texture){
        position = pos;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    /// Return true if a point is within the box
    public boolean isWithin(vec2 pos){
        return ((pos.x > position.x && pos.x < position.x + width) && (pos.y > position.y && pos.y < position.y + height));
    }


    /// Setters ///

    public void setPosition(vec2 pos){
        position = pos;
    }

    public void setDimensions(float width, float height){
        this.width = width;
        this.height = height;
    }
}
