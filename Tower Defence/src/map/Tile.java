package map;

import core.rendering.Texture;
import util.vectors.ivec2;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 3:13 PM
 */

public class Tile {

    private boolean buildable;

    private vec2 position;
    private Texture texture;

    public Tile(vec2 position, Texture texture, boolean buildable){
        this.position = position;
        this.texture = texture;
        this.buildable = buildable;
    }

    public vec2 getPosition(){
        return position;
    }

    public ivec2 get_ij() {
        return new ivec2((int) (position.x / texture.getWidth()), (int)(position.y / texture.getHeight()));
    }

    public void draw(){
        texture.draw(position.x, position.y);
    }

}
