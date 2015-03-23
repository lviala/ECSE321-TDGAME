package gui.control.states;

import core.gamestates.Play;
import data.Textures;
import entities.Player;
import entities.towers.Tower;
import gui.control.GUIState;
import gui.control.overlays.PlaceTowerOverlay;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.non_interfaceable.Tag;
import gui.elements.non_interfaceable.TowerAlpha;
import map.Tile;
import map.TileMap;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/9/2015 - 4:13 AM
 */

public class PlayMain_gui extends GUIState {

    private Player player;
    private TileMap map;
    Play playState;

    /// Button IDs ///
    private final int SNIPER_TOWER_BUTTON_ID = 0;

    public PlayMain_gui(int id, Player player, TileMap map, Play playState) {
        super(id);

        this.player = player;
        this.map = map;
        this.playState = playState;
    }

    @Override
    public void init() {
        addElement(new Tag(new vec2(0, 576), Textures.PLAY_MENU_BACKGROUND, null));
        addElement(new DoublePNGButton(SNIPER_TOWER_BUTTON_ID, new vec2(200, 600), Textures.SNIPER_BUTTON_IDLE, Textures.SNIPER_BUTTON_MOUSEOVER, Textures.SNIPER_BUTTON_DOWN));

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        if (buttonID == SNIPER_TOWER_BUTTON_ID){
            currentOverlay = new PlaceTowerOverlay(this, mouse, new TowerAlpha(Tower.Type.SNIPER, map, mouse), (DoublePNGButton) buttons.get(buttonID));
        }
    }

    public void buyTower(Tower.Type type){
        playState.addTower(type);
    }
}
