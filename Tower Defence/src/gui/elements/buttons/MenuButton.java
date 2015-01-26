package gui.elements.buttons;

import core.rendering.Texture;
import org.newdawn.slick.Color;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:31 PM
 */

public class MenuButton extends Button{

    private Color idleColor = new Color(160, 160, 160);
    private Color mouseOverColor = new Color(255, 150, 50);


    public MenuButton(int id, vec2 pos, Texture texture){
        super(id, pos, texture);
    }

    @Override
    public void draw() {
        if (mouseOver) texture.draw(position.x, position.y, mouseOverColor);
        else texture.draw(position.x, position.y, idleColor);
    }

    @Override
    protected void internalClickedResponse(int mouseButton, int clickCount) {
        /// Clicking this button does not affect its properties
    }
}