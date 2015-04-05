package gui.control.states;

import core.Core;
import data.Textures;
import gui.control.GUIState;
import gui.elements.buttons.MenuButton;
import gui.elements.buttons.Toggle;
import gui.elements.non_interfaceable.Tag;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import util.vectors.vec2;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class MapTypeSelect_gui extends GUIState {
    private final int PREMADE_ID = 0;
    private final int RANDOM_ID = 1;
    private final int BACK_ID = 2;

    boolean vsync;
    boolean fullscreen;


    public MapTypeSelect_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(PREMADE_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 100), Textures.PREMADE_BUTTON_TEXTURE));
        addElement(new MenuButton(RANDOM_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 225), Textures.RANDOM_BUTTON_TEXTURE));
        addElement(new MenuButton(BACK_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 500), Textures.BACK_BUTTON_TEXTURE));


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
            case PREMADE_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.MAP_SELECT.ID);
                }
                break;
            case RANDOM_ID:
                controller.enterState(GUIStateIDs.RANDOM_SELECT.ID);
                break;
        }
    }
}
