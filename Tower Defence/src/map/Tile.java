package map;

import core.rendering.Texture;
import entities.towers.Tower;
import util.vectors.ivec2;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 3:13 PM
 */

public class Tile {


    private boolean buildable; ///< Makes a tile buildeable or unbuildeable depending on if it is a path or if there is a tower on it already

    private vec2 position; ///< The screen pixel position of the tile
    private Texture texture; ///< the png image that will be displayed to the screen at positions's x and y
    private Tower tower; ///< reference to the tower located on this tile if one was built on it (null if empty)

    public Tile(vec2 position, Texture texture, boolean buildable){
        this.position = position;
        this.texture = texture;
        this.buildable = buildable;
    }

    /// Returns the screen pixel location of the top left corner of the tile ///
    public vec2 getPosition(){
        return position;
    }

    /// Returns the index of the tile in the tiles array of of the map ///
    public ivec2 get_ij() {
        return new ivec2((int) (position.x / texture.getWidth()), (int)(position.y / texture.getHeight()));
    }

    public boolean isBuildable(){
        return buildable;
    }

    /// Renders the tile ///
    public void draw(){
        texture.draw(position.x, position.y);
    }

    /// Assigns a tower to the tile when one is bought by the player ///
    public boolean placeTower(Tower tower){
        if (buildable){
            this.tower = tower;
            buildable = false;
            return true;
        }

        return false;
    }

    /// Returns the instance of the tower on the tile ///
    public Tower getTower(){ ///< Used for upgrading / selling towers
        return tower;
    }

    /// Removes the tower from the tile when sold ///
    public void removeTower(){
        if (tower != null){
            tower = null;
            buildable = true;
        }
    }

    public Texture getTexture() {
        return texture;
    }
}
