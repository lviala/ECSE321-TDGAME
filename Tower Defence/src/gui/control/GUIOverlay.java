package gui.control;

import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import org.newdawn.slick.Input;
import util.MouseWrapper;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:11 AM
 */

public abstract class GUIOverlay {

    private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    protected MouseWrapper mouse;

    protected GUIState parentState;

    public GUIOverlay(GUIState parentState, MouseWrapper mouse){
        this.parentState = parentState;
        this.mouse = mouse;
    }

    public abstract void init();
    public abstract void customUpdate();
    public abstract void customDraw();
    public abstract void customMouseClicked(int mouseButton, int clickCount);
    public abstract void customClose();
    protected abstract void buttonClicked(int buttonID, int mouseButton, int clickCount);

    public void addElement(GUIElement e){
        if (e instanceof Button) buttons.add((Button) e);
        else elements.add(e);
    }

    public void update(){
        customUpdate();
        for (Button b : buttons){
            b.isMouseWithin(mouse);
        }
    }

    public void mouseClicked(int mouseButton, int clickCount){
        if (mouseButton == Input.MOUSE_RIGHT_BUTTON){
            this.close();
        }

        boolean buttonClicked = false;
        for (Button b : buttons){
            if (b.mouseClicked(mouseButton, clickCount)){
                buttonClicked(b.getID(), mouseButton, clickCount);
                buttonClicked = true;
            }
        }

        if (!buttonClicked){
            customMouseClicked(mouseButton, clickCount);
            this.close();
            parentState.mouseClicked(mouseButton, clickCount);
        }
    }

    public void draw(){
        customDraw();

        for (GUIElement e : elements){
            e.draw();
        }
        for (Button b : buttons) {
            b.draw();
        }
    }

    public void close(){
        customClose();
        parentState.closeOverlay();
    }

}
