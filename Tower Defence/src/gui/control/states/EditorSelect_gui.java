package gui.control.states;

import core.Core;
import core.gamestates.Editor;
import data.Textures;
import gui.control.GUIState;
import gui.elements.buttons.MenuButton;
import map.MapGenerator;
import org.newdawn.slick.Input;
import util.vectors.vec2;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class EditorSelect_gui extends GUIState {
    private final int LARGE_ID = 0;
    private final int MEDIUM_ID = 1;
    private final int SMALL_ID = 2;
    private final int BACK_ID = 3;

    boolean vsync;
    boolean fullscreen;


    public EditorSelect_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(LARGE_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 100), Textures.LARGE_BUTTON));
        addElement(new MenuButton(MEDIUM_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 225), Textures.MEDIUM_BUTTON));
        addElement(new MenuButton(SMALL_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 350), Textures.SMALL_BUTTON));
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
            case LARGE_ID:
                Editor.mapsize = MapGenerator.Size.LARGE;
                controller.getStateBasedGame().enterState(Core.EDITOR);
                break;
            case MEDIUM_ID:
                Editor.mapsize = MapGenerator.Size.MEDIUM;
                controller.getStateBasedGame().enterState(Core.EDITOR);
                break;
            case SMALL_ID:
                Editor.mapsize = MapGenerator.Size.SMALL;
                controller.getStateBasedGame().enterState(Core.EDITOR);
                break;
        }
    }
}
