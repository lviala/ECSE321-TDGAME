/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import data.PNGMaps;
import data.Textures;
import entities.Player;
import entities.critters.CritterManager;
import entities.towers.Tower;
import entities.towers.TowerManager;
import gui.control.GUIController;
import gui.control.states.GUIStateIDs;
import gui.control.states.PlayMain_gui;
import map.Tile;
import map.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import particles.ParticleUtilities;
import util.MouseWrapper;

public class Play extends BasicGameState {

    private int stateID;

    private Player player;
    private TileMap map;
    private MouseWrapper mouse;

    private GameContainer gameContainer;
    private CritterManager critterManager;
    private TowerManager towerManager;
    private GUIController guiController;

    private ParticleSystem particleSystem;


    /// Testing ///



    /// End Testing ///

    public Play(int ID){
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
        this.gameContainer = gameContainer;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render();
        critterManager.render();
        towerManager.render();
        particleSystem.render();
        guiController.render();

        /// Testing ///



        /// End Testing ///

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        mouse.update();
        guiController.update();
        critterManager.update(delta);
        towerManager.update(delta);
        particleSystem.update(delta);

        /// Testing ///


        /// End Testing ///
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
        player = new Player(1000, 30);
        map = new TileMap(PNGMaps.testmap_3);
        critterManager = new CritterManager("res/files/sample_level.txt", map.getStartTile(), map.getEndTile());
        towerManager = new TowerManager(map);
        particleSystem = ParticleUtilities.createSystem(Textures.SQAURE_PARTICLE_TEXTURE, 2000);
        guiController.addState(new PlayMain_gui(GUIStateIDs.PLAY_MAIN.ID, player, map, this));
        guiController.enterState(GUIStateIDs.PLAY_MAIN.ID);



        /// Testing ///


        /// End Testing ///

    }

    @Override
    public void mouseClicked(int mouseButton, int x, int y, int clickCount) {
        super.mouseClicked(mouseButton, x, y, clickCount);
        guiController.mouseClicked(mouseButton, clickCount);
    }

    public void addTower(Tower.Type type){
        if (player.getCurrency() >= type.cost()) {
            Tile tile = map.getTile(mouse.getPosition().x, mouse.getPosition().y);
            Tower tower = Tower.create(type, tile.getPosition(), critterManager);

            if (tile.placeTower(tower)) {
                towerManager.addTower(tower);
                player.updateCurrency(-type.cost());
            }
        }
    }
}
