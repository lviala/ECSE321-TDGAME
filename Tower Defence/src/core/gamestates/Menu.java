/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import gui.control.GUIController;
import gui.control.states.*;
import org.newdawn.slick.*;
import util.MouseWrapper;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState {

    private int stateID;

    private MouseWrapper mouse;
    private GUIController guiController;

    public Menu(int ID){
        stateID = ID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        mouse = new MouseWrapper(gameContainer.getHeight());

        guiController = new GUIController(gameContainer, stateBasedGame, mouse);
        guiController.addState(new MainMenu_gui(GUIStateIDs.MAIN_MENU.ID));
        guiController.addState(new SettingsMenu_gui(GUIStateIDs.SETTINGS.ID));
        guiController.addState(new ControlsMenu_gui(GUIStateIDs.CONTROLS.ID));
        guiController.addState(new MapTypeSelect_gui(GUIStateIDs.MAP_TYPE.ID));
        guiController.addState(new MapSelect_gui(GUIStateIDs.MAP_SELECT.ID));
        guiController.addState(new RandomSelect_gui(GUIStateIDs.RANDOM_SELECT.ID));
        guiController.addState(new EditorSelect_gui(GUIStateIDs.EDITOR_SELECT.ID));
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        mouse.update();
        guiController.update();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        guiController.render(graphics);

    }

    @Override
    public void mouseClicked(int mouseButton, int x, int y, int clickCount) {
        super.mouseClicked(mouseButton, x, y, clickCount);
        guiController.mouseClicked(mouseButton, clickCount);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        guiController.enterState(GUIStateIDs.MAIN_MENU.ID);
    }
}
