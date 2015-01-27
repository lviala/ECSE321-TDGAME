/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import data.PNGMaps;
import data.Textures;
import entities.Critter;
import map.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import util.Mousew;

public class Play extends BasicGameState {

    private int stateID;

    private Mousew mouse;
    private TileMap map;
    private Critter critter;
    private GameContainer gameContainer;

    public Play(int ID){
        stateID = ID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        mouse = new Mousew(gameContainer.getHeight());
        this.gameContainer = gameContainer;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render();
        critter.draw();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        critter.update(delta);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        if (key == Input.KEY_ESCAPE){
            gameContainer.exit();
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        map = new TileMap(PNGMaps.testmap_1);
        critter = new Critter(Textures.RED_DOT_TEXTURE);
        critter.init(map.getStartTile());
    }
}
