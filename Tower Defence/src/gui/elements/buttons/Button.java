package gui.elements.buttons;

import core.rendering.Texture;
import gui.control.GUIState;
import gui.elements.Clikeable;
import gui.elements.GUIElement;
import org.newdawn.slick.Input;
import util.Box;
import util.MouseWrapper;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:35 AM
 */

public abstract class Button extends GUIElement implements Clikeable {

    private final int ID;
    protected Box box;
    protected boolean isMouseover = false;


    public Button(int id, vec2 pos, Texture texture){
        super(pos, texture);
        ID = id;
        box = new Box(position, texture); ///< Give box an shallow copy of the protected vec2 position, hence, box's x and y will change as position's do
    }

    public boolean isMouseWithin(MouseWrapper mouse){
        if(box.isWithin(mouse.getPosition())){
            isMouseover = true;
            return true;
        }else{
            isMouseover = false;
            return false;
        }
    }

    /// Return button id
    public int getID(){
        return ID;
    }

    @Override
    public boolean mouseClicked(int mouseButton, int clickCount) {
        if (mouseButton == Input.MOUSE_LEFT_BUTTON){
            if (isMouseover){
                internalClickedResponse(mouseButton, clickCount);
                return true;
            }
        }
        return false;
    }

    @Override
    public abstract void draw(); ///< Different buttons might render differently
    protected abstract void internalClickedResponse(int mouseButton, int clickCount); ///< If clicking a button changes its behaviour or state

}

