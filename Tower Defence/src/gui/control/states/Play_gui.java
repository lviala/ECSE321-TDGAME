package gui.control.states;

import core.Core;
import core.gamestates.Play;
import data.Textures;
import entities.Player;
import entities.towers.Tower;
import gui.control.GUIState;
import gui.control.overlays.PlaceTowerOverlay;
import gui.elements.buttons.Button;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.non_interfaceable.Tag;
import gui.elements.non_interfaceable.TowerAlpha;
import map.Tile;
import map.TileMap;
import org.newdawn.slick.Input;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/9/2015 - 4:13 AM
 */

public class Play_gui extends GUIState {

    private Player player;
    private TileMap map;
    Play playState;

    /// Button IDs ///
    private final int SNIPER_TOWER_BUTTON_ID = 0;
    private final int SLOW_TOWER_BUTTON_ID = 1;
    private final int RAPID_TOWER_BUTTON_ID = 2;

    public Play_gui(int id, Player player, TileMap map, Play playState) {
        super(id);

        this.player = player;
        this.map = map;
        this.playState = playState;
    }

    @Override
    public void init() {
        addElement(new Tag(new vec2(0, Core.HEIGHT - 144), Textures.PLAY_MENU_BACKGROUND, null));
        addElement(new DoublePNGButton(SNIPER_TOWER_BUTTON_ID, new vec2(200, Core.HEIGHT - 120), Textures.SNIPER_BUTTON_IDLE, Textures.SNIPER_BUTTON_MOUSEOVER, Textures.SNIPER_BUTTON_DOWN));
        addElement(new DoublePNGButton(SLOW_TOWER_BUTTON_ID, new vec2(350, Core.HEIGHT - 120), Textures.SLOW_BUTTON_IDLE, Textures.SLOW_BUTTON_MOUSEOVER, Textures.SLOW_BUTTON_DOWN));
        addElement(new DoublePNGButton(RAPID_TOWER_BUTTON_ID, new vec2(500, Core.HEIGHT - 120), Textures.RAPID_BUTTON_IDLE, Textures.RAPID_BUTTON_MOUSEOVER, Textures.RAPID_BUTTON_DOWN));

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void mouseClicked(int mouseButton, int clickCount, boolean fromOverlay){

        if (mouseButton == Input.MOUSE_RIGHT_BUTTON && currentOverlay == null){
            if (map.withinMap(mouse.getPosition())) {
                Tile tile = map.getTile(mouse.getPosition().x, mouse.getPosition().y);
                if (tile.getTower() != null) {
                    playState.sellTower(tile);
                }
            }
        }

        if (currentOverlay != null){
            currentOverlay.mouseClicked(mouseButton, clickCount);
        }

        for (Button b : buttons){
            if (b.mouseClicked(mouseButton, clickCount)) buttonClicked(b.getID(), mouseButton, clickCount);
        }


    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        if (buttonID == SNIPER_TOWER_BUTTON_ID){
            currentOverlay = new PlaceTowerOverlay(this, mouse, new TowerAlpha(Tower.Type.SNIPER, map, mouse, player), (DoublePNGButton) buttons.get(buttonID));
        }

        if (buttonID == SLOW_TOWER_BUTTON_ID){
            currentOverlay = new PlaceTowerOverlay(this, mouse, new TowerAlpha(Tower.Type.SLOW, map, mouse, player), (DoublePNGButton) buttons.get(buttonID));
        }

        if (buttonID == RAPID_TOWER_BUTTON_ID){
            currentOverlay = new PlaceTowerOverlay(this, mouse, new TowerAlpha(Tower.Type.RAPID, map, mouse, player), (DoublePNGButton) buttons.get(buttonID));
        }
    }

    public void buyTower(Tower.Type type){
        playState.addTower(type);
    }
}
