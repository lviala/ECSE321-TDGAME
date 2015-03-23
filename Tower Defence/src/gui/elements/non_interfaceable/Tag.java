package gui.elements.non_interfaceable;

import core.rendering.Texture;
import gui.elements.GUIElement;
import org.newdawn.slick.Color;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 3:11 PM
 */

public class Tag extends GUIElement {

    private Color color;

    public Tag(vec2 pos, Texture texture, Color color){
        super(pos, texture);
        this.color = color;
    }

    @Override
    public void draw() {
        if (color != null) {
            texture.draw(position.x, position.y, color);
        }else{
            texture.draw(position.x, position.y);
        }
    }
}
