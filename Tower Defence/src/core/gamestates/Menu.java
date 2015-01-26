/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import gui.control.GUIController;
import gui.control.states.GUIStateIDs;
import gui.control.states.MainMenu_gui;
import gui.control.states.SettingsMenu_gui;
import org.newdawn.slick.*;
import util.Mousew;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState {

    private int stateID;

    private Mousew mouse;
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
        mouse = new Mousew(gameContainer.getHeight());

        guiController = new GUIController(gameContainer, stateBasedGame, mouse);
        guiController.addState(new MainMenu_gui(GUIStateIDs.MAIN_MENU.ID));
        guiController.addState(new SettingsMenu_gui(GUIStateIDs.SETTINGS.ID));
        guiController.enterState(GUIStateIDs.MAIN_MENU.ID);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        mouse.update();
        guiController.update();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        guiController.render();

    }

    @Override
    public void mouseClicked(int mouseButton, int x, int y, int clickCount) {
        super.mouseClicked(mouseButton, x, y, clickCount);
        guiController.mouseClicked(mouseButton, clickCount);
    }
}
