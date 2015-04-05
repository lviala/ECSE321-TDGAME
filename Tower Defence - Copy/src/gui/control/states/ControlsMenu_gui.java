package gui.control.states;

import data.Textures;
import gui.control.GUIState;
import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import gui.elements.buttons.MenuButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import util.vectors.vec2;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class ControlsMenu_gui extends GUIState {
    private final int BACK_ID = 0;

    boolean vsync;
    boolean fullscreen;


    public ControlsMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(BACK_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 500), Textures.BACK_BUTTON_TEXTURE));

        /// Add tags

    }

    @Override
    public void draw(Graphics graphics){

        graphics.setColor(Color.white);
        graphics.drawString("Use LMB to select towers to buy from menu at bottom of screen", 100, 50);
        graphics.drawString("Use RMB to to cancel tower placement", 100, 75);

        graphics.drawString("Right Click any tower to sell it for 75% of original value", 100, 125);

        graphics.drawString("Press SPACE to start next wave", 100, 175);
        graphics.drawString("Press ESC to close the app at any time", 100, 200);

        graphics.drawString("TOWER TYPES", 100, 250);
        graphics.drawString("SNIPER TOWER - low fire rate, long range, high damage", 100, 275);
        graphics.drawString("SLOW TOWER - low fire rate, long range, low damage, slows enemies and alternates between targets", 100, 300);
        graphics.drawString("RAPID TOWER - high fire rate, short range, highest damage per second", 100, 325);


        for (GUIElement e : elements){
            e.draw();
        }
        for (Button b : buttons) {
            b.draw();
        }

        if (currentOverlay != null){
            currentOverlay.draw();
        }
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        switch (buttonID){
            case BACK_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.MAIN_MENU.ID);
                }
                break;

        }
    }
}
