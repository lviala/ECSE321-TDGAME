package gui.control.states;

import core.Core;
import data.Textures;
import gui.control.GUIState;
import gui.elements.buttons.Button;
import gui.elements.buttons.MenuButton;
import org.newdawn.slick.Input;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:51 AM
 */

public class MainMenu_gui extends GUIState {

    private final int PLAY_ID = 0;
    private final int SETTINGS_ID = 1;
    private final int EXIT_ID = 2;



    public MainMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        addElement(new MenuButton(PLAY_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.PLAY_BUTTON_TEXTURE.getWidth() / 2), 100), Textures.PLAY_BUTTON_TEXTURE));
        addElement(new MenuButton(SETTINGS_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.SETTINGS_BUTTON_TEXTURE.getWidth() / 2), 250), Textures.SETTINGS_BUTTON_TEXTURE));
        addElement(new MenuButton(EXIT_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.EXIT_BUTTON_TEXTURE.getWidth() / 2), 400), Textures.EXIT_BUTTON_TEXTURE));
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
            case PLAY_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.getStateBasedGame().enterState(Core.PLAY);
                }
                break;
            case SETTINGS_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.SETTINGS.ID);
                }
                break;
            case EXIT_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.getGameContainer().exit();
                }
                break;
        }
    }
}
