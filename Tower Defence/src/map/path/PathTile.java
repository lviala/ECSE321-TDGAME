package map.path;

import core.rendering.Texture;
import map.Tile;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 3:19 PM
 */

public class PathTile extends Tile {

    private PathTile next;
    private PathTile previous;
    private vec2 centre;
    private boolean isStart = false;
    private boolean isEnd = false;

    public PathTile(vec2 position, Texture texture) {
        super(position, texture, false);

        centre = new vec2(position.x + (texture.getWidth() / 2), position.y + (texture.getHeight() / 2));
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

    public void setStart(boolean bool){ isStart = bool; }

    public void setEnd(boolean bool){ isEnd = bool; }

    public boolean isStart(){ return isStart; }

    public boolean isEnd(){ return isEnd; }
}
