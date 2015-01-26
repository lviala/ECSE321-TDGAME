package gui.elements.buttons;

import core.rendering.Texture;
import gui.elements.Clikeable;
import gui.elements.GUIElement;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import util.Box;
import util.vectors.vec2;

import javax.xml.soap.Text;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:07 PM
 */

public class Toggle extends Button {

    private Color idleColor = new Color(160, 160, 160);
    private Color mouseOverColor = new Color(200, 200, 200);

    private boolean isOn = false;

    private final Texture ON_TEXTURE;
    private final Texture OFF_TEXTURE;

    public Toggle(int id, vec2 pos, Texture offTexture, Texture onTexture, boolean initON){
        super(id, pos, offTexture);
        if (initON) texture = onTexture;

        isOn = initON;
        ON_TEXTURE = onTexture;
        OFF_TEXTURE = offTexture;
    }

    @Override
    public void draw() {
        if (mouseOver) texture.draw(position.x, position.y, mouseOverColor);
        else texture.draw(position.x, position.y, idleColor);
    }

    @Override
    protected void internalClickedResponse(int mouseButton, int clickCount) {
        if (mouseButton == Input.MOUSE_LEFT_BUTTON && clickCount == 1){
            if (isOn){
                texture = OFF_TEXTURE;
                isOn = false;
            } else{
                texture = ON_TEXTURE;
                isOn = true;
            }
        }
    }
}
