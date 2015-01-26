package gui.elements;

import core.rendering.Texture;
import org.newdawn.slick.Color;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 3:11 PM
 */

public class Tag extends GUIElement{

    private Color color = new Color(160, 160, 160);

    public Tag(vec2 pos, Texture texture){
        super(pos, texture);
    }

    @Override
    public void draw() {
        texture.draw(position.x, position.y, color);
    }
}
