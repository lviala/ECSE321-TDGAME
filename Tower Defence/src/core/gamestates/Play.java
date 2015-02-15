/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import data.PNGMaps;
import data.Textures;
import entities.critters.Critter;
import entities.critters.CritterManager;
import entities.projectiles.Projectile;
import entities.towers.Tower;
import map.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import util.Mousew;
import util.vectors.vec2;

public class Play extends BasicGameState {

    private int stateID;

    private Mousew mouse;
    private TileMap map;
    private CritterManager critterManager;
    private GameContainer gameContainer;

    private Tower testTower;

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
        critterManager.render();
        testTower.draw();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        critterManager.update(delta);
        testTower.update(delta);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        switch (key){
            case Input.KEY_ESCAPE:
                gameContainer.exit();
                break;
            case Input.KEY_SPACE:
                critterManager.startNextWave();
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        map = new TileMap(PNGMaps.testmap_2);
        critterManager = new CritterManager("res/files/sample_level.txt", map.getStartTile(), map.getEndTile());
        testTower = new Tower(Textures.SHIT_TOWER_TEXTURE, new vec2(9 * 64, 6 * 64), critterManager);
    }
}
