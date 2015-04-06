package gui.control.states;

import data.Textures;
import gui.control.GUIState;
import gui.elements.buttons.MenuButton;
import org.newdawn.slick.Input;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:51 AM
 */

public class MainMenu_gui extends GUIState {

    private final int PLAY_ID = 0;
    private final int SETTINGS_ID = 1;
    private final int CONTROLS_ID = 2;
    private final int EXIT_ID = 3;
    private final int EDITOR_ID = 4;



    public MainMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        addElement(new MenuButton(PLAY_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.PLAY_BUTTON_TEXTURE.getWidth() / 2), 50), Textures.PLAY_BUTTON_TEXTURE));
        addElement(new MenuButton(SETTINGS_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.SETTINGS_BUTTON_TEXTURE.getWidth() / 2), 175), Textures.SETTINGS_BUTTON_TEXTURE));
        addElement(new MenuButton(CONTROLS_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.CONTROLS_BUTTON_TEXTURE.getWidth() / 2), 300), Textures.CONTROLS_BUTTON_TEXTURE));
        addElement(new MenuButton(EXIT_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.EXIT_BUTTON_TEXTURE.getWidth() / 2), 575), Textures.EXIT_BUTTON_TEXTURE));
        addElement(new MenuButton(EDITOR_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.EXIT_BUTTON_TEXTURE.getWidth() / 2), 425), Textures.EDITOR_BUTTON_TEXTURE));
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
                    controller.enterState(GUIStateIDs.MAP_TYPE.ID);
                }
                break;
            case SETTINGS_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.SETTINGS.ID);
                }
                break;
            case CONTROLS_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.CONTROLS.ID);
                }
                break;
            case EXIT_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.getGameContainer().exit();
                }
                break;
            case EDITOR_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.EDITOR_SELECT.ID);
                }
                break;
        }
    }
}
