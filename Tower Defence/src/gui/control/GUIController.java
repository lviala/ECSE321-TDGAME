package gui.control;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import util.MouseWrapper;

import java.util.HashMap;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:06 AM
 */

public class GUIController {

    private MouseWrapper mouse;
    private GameContainer gameContainer;
    private StateBasedGame stateBasedGame;

    private GUIState currentstate;
    private HashMap<Integer, GUIState> states = new HashMap<Integer, GUIState>();


    public GUIController(GameContainer gameContainer, StateBasedGame stateBasedGame, MouseWrapper mouse){
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;
        this.mouse = mouse;
    }

    public void update(){
        currentstate.update();
    }

    public void render(){
        currentstate.draw();
    }

    public void mouseClicked(int mouseButton, int clickCount){
        currentstate.mouseClicked(mouseButton, clickCount);
    }

    public void addState(GUIState state){
        state.setController(this);
        state.setMouse(mouse);
        state.init();
        states.put(state.getID(), state);
    }

    public void enterState(int stateID){
        if (currentstate != null) {currentstate.exit();}
        currentstate = states.get(stateID);
        currentstate.enter();
    }

    public GameContainer getGameContainer(){ return gameContainer; }
    public StateBasedGame getStateBasedGame(){ return stateBasedGame; }
}
