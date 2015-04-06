/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import core.Core;
import data.Textures;
import entities.Player;
import entities.critters.CritterManager;
import entities.towers.Tower;
import entities.towers.TowerManager;
import gui.control.GUIController;
import gui.control.states.GUIStateIDs;
import gui.control.states.Play_gui;
import map.Tile;
import map.TileMap;
import org.newdawn.slick.*;
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
    private StateBasedGame stateBasedGame;
    private CritterManager critterManager;
    private TowerManager towerManager;
    private GUIController guiController;

    private ParticleSystem particleSystem;

    private boolean lost = false;
    private boolean paused = false;


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
        this.stateBasedGame = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(0, 135, 255));
        graphics.clear();
        map.render();
        critterManager.render();
        towerManager.render();
        particleSystem.render();
        guiController.render(graphics);

        graphics.setColor(Color.black);
        graphics.drawString("Cash: " + player.getCurrency(), 10, 600);
        graphics.drawString("Lives: " + player.getLives(), 10, 625);
        graphics.drawString("Wave: " + critterManager.getWaveNumber(), 10, 650);
        graphics.drawString("Health Multiplier: " + (float)(1.0f + (critterManager.getWaveNumber() - 1.0f)/5.0f), 10, 700);


        graphics.drawString("" + Tower.Type.SNIPER.cost(), 215, 670);
        graphics.drawString("" + Tower.Type.SLOW.cost(), 365, 670);
        graphics.drawString("" + Tower.Type.RAPID.cost(), 515, 670);

        graphics.drawString("SNIPER", 205, 580);
        graphics.drawString("SLOW", 362, 580);
        graphics.drawString("RAPID", 509, 580);


        if (lost){
            graphics.setColor(Color.black);
            graphics.fillRect(gameContainer.getWidth() / 2 - 150, gameContainer.getHeight() / 2 - 50, 300, 100);
            graphics.setColor(Color.white);
            graphics.drawString("GAME OVER", gameContainer.getWidth() / 2 - 43, gameContainer.getHeight() / 2 - 30);
            graphics.drawString("Press SPACE to restart", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 14 );
            graphics.drawString("Press ENTER for Main Menu", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 28 );
        }
        if (paused){
            graphics.setColor(Color.black);
            graphics.fillRect(gameContainer.getWidth() / 2 - 150, gameContainer.getHeight() / 2 - 50, 300, 100);
            graphics.setColor(Color.white);
            graphics.drawString("PAUSED", gameContainer.getWidth() / 2 - 43, gameContainer.getHeight() / 2 - 30);
            graphics.drawString("Press ESC to resume", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 0 );
            graphics.drawString("Press SPACE to restart", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 14 );
            graphics.drawString("Press ENTER for Main Menu", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 28 );
        }

        /// Testing ///



        /// End Testing ///

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (!lost && !paused) {
            mouse.update();
            guiController.update();
            critterManager.update(delta);
            towerManager.update(delta);
            particleSystem.update(delta);
        }
        /// Testing ///


        /// End Testing ///
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!paused && !lost) {
            super.keyPressed(key, c);

            switch (key) {
                case Input.KEY_ESCAPE:
                    paused = true;
                    break;
                case Input.KEY_SPACE:
                    critterManager.startNextWave();
            }

        } else if (lost){
            switch (key){
                case Input.KEY_SPACE:
                    stateBasedGame.enterState(Core.PLAY);
                    break;
                case Input.KEY_ENTER:
                    stateBasedGame.enterState(Core.MENU);
                    break;
            }
        } else if (paused) {
            switch (key) {
                case Input.KEY_ESCAPE:
                    paused = false;
                    break;
                case Input.KEY_SPACE:
                    paused = false;
                    stateBasedGame.enterState(Core.PLAY);
                    break;
                case Input.KEY_ENTER:
                    paused = false;
                    stateBasedGame.enterState(Core.MENU);
                    break;
            }

        }


    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        lost = false;
        player = new Player(400, 20, this);
        map = new TileMap();
        critterManager = new CritterManager("res/files/sample_level.txt", map.getStartTile(), map.getEndTile(), this);
        towerManager = new TowerManager(map);
        particleSystem = ParticleUtilities.createSystem(Textures.SQAURE_PARTICLE_TEXTURE, 2000);
        guiController.addState(new Play_gui(GUIStateIDs.PLAY_MAIN.ID, player, map, this));
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
        if (player.getCurrency() >= type.cost() && map.withinMap(mouse.getPosition())) {
            Tile tile = map.getTile(mouse.getPosition().x, mouse.getPosition().y);
            Tower tower = Tower.create(type, tile.getPosition(), critterManager, particleSystem);

            if (tile.placeTower(tower)) {
                towerManager.addTower(tower);
                player.updateCurrency(-type.cost());
            }
        }
    }

    public void sellTower(Tile tile) {
        towerManager.removeTower(tile.getTower());
        player.updateCurrency(tile.getTower().getSellValue());
        tile.removeTower();
    }

    public void removeLife() {
        player.removeLife();
    }

    public void rewardPlayer(int value){
        player.updateCurrency(value);
    }

    public void lostGame() {
        lost = true;
    }
}
