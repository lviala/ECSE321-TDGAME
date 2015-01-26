package gui.control;

import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import util.Mousew;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:11 AM
 */

public abstract class GUIState {

    private final int ID;

    protected GUIController controller;

    private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    protected Mousew mouse;

    public GUIState(int id){
        ID = id;
    }

    public abstract void init();
    public abstract void enter();
    public abstract void exit();
    protected abstract void buttonClicked(int buttonID, int mouseButton, int clickCount);

    public int getID(){ return ID; }

    public void setController(GUIController controller){
        this.controller = controller;
    }

    public void setMouse(Mousew mouse){
        this.mouse = mouse;
    }

    public void addElement(GUIElement e){
        if (e instanceof Button) buttons.add((Button) e);
        else elements.add(e);
    }

    public void update(){
        for (Button b : buttons){
            b.isMouseWithin(mouse);
        }
    }

    public void mouseClicked(int mouseButton, int clickCount){
        for (Button b : buttons){
                if (b.mouseClicked(mouseButton, clickCount)) buttonClicked(b.getID(), mouseButton, clickCount);
        }
    }

    public void draw(){
        for (Button b : buttons) {
            b.draw();
        }
        for (GUIElement e : elements){
            e.draw();
        }
    }

}
