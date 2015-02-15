package util;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 3:06 AM
 *
 * Basic box functionality
 */

public class Box {

    private vec2 pos;
    private float width, height;

    /// Standard constructor
    public Box(vec2 pos, float width, float height){
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    /// Texture constructor gets dimensions directly from texture
    public Box(vec2 pos, Texture texture){
        this.pos = pos;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    /// Return true if a point is within the box
    public boolean isWithin(vec2 pos){
        return ((pos.x > this.pos.x && pos.x < this.pos.x + width) && (pos.y > this.pos.y && pos.y < this.pos.y + height));
    }

    /// Return true if box intersects another box
    public boolean intersects(Box box){
        vec2[] corners = getCorners();
        if (box.isWithin(corners[0])) {
            return true;
        }
        if (box.isWithin(corners[1])) {
            return true;
        }
        if (box.isWithin(corners[2])) {
            return true;
        }
        if (box.isWithin(corners[3])) {
            return true;
        }

        return false;
    }

    public vec2[] getCorners(){
        vec2[] corners = {pos, pos.add(new vec2(width, 0)), pos.add(new vec2(width, height)), pos.add(new vec2(0, height))};
        return corners;
    }

    public vec2 centre(){
        return new vec2(pos.x + (width / 2), pos.y + (height / 2));
    }

    /// Setters ///

    public void setPosition(vec2 pos){
        this.pos = pos;
    }

    public void setDimensions(float width, float height){
        this.width = width;
        this.height = height;
    }
}
