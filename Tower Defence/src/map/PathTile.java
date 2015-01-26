package map;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 3:19 PM
 */

public class PathTile extends Tile {

    private PathTile next;
    private PathTile previous;
    private vec2 centre;


    public PathTile(vec2 position, Texture texture) {
        super(position, texture, false);

        centre = new vec2(position.x - (texture.getWidth() / 2), position.y - (texture.getHeight() / 2));
    }

    public vec2 getCentre(){
        return centre;
    }

    public void setNext(PathTile tile){
        next = tile;
    }

    public void setPrevious(PathTile tile){
        previous = tile;
    }

    public PathTile getNext(){
        return next;
    }

    public PathTile getPrevious(){
        return previous;
    }
}
