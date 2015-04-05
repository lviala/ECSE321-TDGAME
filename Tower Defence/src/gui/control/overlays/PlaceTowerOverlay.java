package gui.control.overlays;

import gui.control.GUIOverlay;
import gui.control.GUIState;
import gui.control.states.Play_gui;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.non_interfaceable.TowerAlpha;
import org.newdawn.slick.Input;
import util.MouseWrapper;

/**
 * Created by Francis O'Brien - 3/10/2015 - 9:00 AM
 */

public class PlaceTowerOverlay extends GUIOverlay {

    private TowerAlpha towerAlpha;
    private DoublePNGButton downedButton;

    public PlaceTowerOverlay(GUIState parentState, MouseWrapper mouse, TowerAlpha towerAlpha, DoublePNGButton downedButton) {
        super(parentState, mouse);
        this.towerAlpha = towerAlpha;
        this.downedButton = downedButton;
        downedButton.setDown();
    }

    @Override
    public void init() {

    }

    @Override
    public void customUpdate() {
        towerAlpha.update();
    }

    @Override
    public void customDraw() {
        towerAlpha.draw();
    }

    @Override
    public void customMouseClicked(int mouseButton, int clickCount) {
        if (mouseButton == Input.MOUSE_LEFT_BUTTON) ((Play_gui) parentState).buyTower(towerAlpha.getType());
        else close();
    }

    @Override
    public void customClose() {
        downedButton.up();
    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {

    }
}
