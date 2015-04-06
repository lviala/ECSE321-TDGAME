package gui.control.states;

import core.Core;
import core.gamestates.Editor;
import core.rendering.Texture;
import data.Textures;
import gui.control.GUIState;
import gui.elements.buttons.Button;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.non_interfaceable.Tag;
import map.Tile;
import map.TileMap;
import map.path.PathTile;
import org.newdawn.slick.Input;
import util.vectors.ivec2;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/9/2015 - 4:13 AM
 */

public class Editor_gui extends GUIState {

    private TileMap map;
    private Editor editstate;

    private int tileState = 0;

    private DoublePNGButton downedButton = null;

    private final int RED_STATE = 1;
    private final int BLUE_STATE = 2;
    private final int YELLOW_STATE = 3;

    private boolean startPlaced = false;
    private boolean endPlaced = false;

    /// Button IDs ///
    private final int RED_BUTTON_ID = 0;
    private final int BLUE_BUTTON_ID = 1;
    private final int YELLOW_BUTTON_ID = 2;
    private final int SAVE_BUTTON_ID = 3;

    public Editor_gui(int id, TileMap map, Editor editstate) {
        super(id);

        this.map = map;
        this.editstate = editstate;
    }

    @Override
    public void init() {
        addElement(new Tag(new vec2(0, Core.HEIGHT - 144), Textures.PLAY_MENU_BACKGROUND, null));
        addElement(new DoublePNGButton(RED_BUTTON_ID, new vec2(350, Core.HEIGHT - 120), Textures.RED_IDLE_TEXTURE, Textures.RED_MO_TEXTURE, Textures.RED_DOWN_TEXTURE));
        addElement(new DoublePNGButton(BLUE_BUTTON_ID, new vec2(200, Core.HEIGHT - 120), Textures.BLUE_IDLE_TEXTURE, Textures.BLUE_MO_TEXTURE, Textures.BLUE_DOWN_TEXTURE));
        addElement(new DoublePNGButton(YELLOW_BUTTON_ID, new vec2(500, Core.HEIGHT - 120), Textures.YELLOW_IDLE_TEXTURE, Textures.YELLOW_MO_TEXTURE, Textures.YELLOW_DOWN_TEXTURE));
        addElement(new DoublePNGButton(SAVE_BUTTON_ID, new vec2(725, Core.HEIGHT - 80), Textures.SAVE_IDLE_TEXTURE, Textures.SAVE_MO_TEXTURE, null));

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void mouseClicked(int mouseButton, int clickCount, boolean fromOverlay){

        for (Button b : buttons){
            if (b.mouseClicked(mouseButton, clickCount)) buttonClicked(b.getID(), mouseButton, clickCount);
        }

        if (mouseButton == Input.MOUSE_LEFT_BUTTON && map.withinMap(mouse.getPosition())){
            ivec2 ij = map.get_ij(mouse.getPosition());
            setTile(ij);
        }

        if (mouseButton == Input.MOUSE_RIGHT_BUTTON && map.withinMap(mouse.getPosition())){
            ivec2 ij = map.get_ij(mouse.getPosition());
            setTileGrass(ij);
        }


    }

    private void setTile(ivec2 index) {
        switch (tileState){
            case RED_STATE:
                PathTile redTile = genPath(index, Textures.RED_TILE_TEXTURE);
                redTile.setEnd(true);
                map.setTile(index.x, index.y, redTile);
                break;
            case BLUE_STATE:
                PathTile blueTile = genPath(index, Textures.BLUE_TILE_TEXTURE);
                blueTile.setStart(true);
                map.setTile(index.x, index.y, blueTile);
                break;
            case YELLOW_STATE:
                PathTile yellowTile = genPath(index, Textures.ALT_PATH_TEXTURE);
                map.setTile(index.x, index.y, yellowTile);
                break;
        }
    }

    private void setTileGrass(ivec2 index){
        int i = index.x;
        int j = index.y;

        Tile tile;

        if ((i + j) % 2 == 0) { ///< Two shades of green are used to form a checkered pattern, this makes i easier to differentiate individual tiles
            tile = new Tile(new vec2(i * Textures.ALT_GRASS_DARK_TEXTURE.getWidth(), j * Textures.ALT_GRASS_DARK_TEXTURE.getHeight()), Textures.ALT_GRASS_DARK_TEXTURE, true);
        }else{
            tile = new Tile(new vec2(i * Textures.ALT_GRASS_DARK_TEXTURE.getWidth(), j * Textures.ALT_GRASS_DARK_TEXTURE.getHeight()), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
        }

        map.setTile(index.x, index.y, tile);
    }

    private PathTile genPath(ivec2 index, Texture texture){
        return new PathTile(new vec2(index.x * 64, index.y * 64), texture);
    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        if (mouseButton == Input.MOUSE_LEFT_BUTTON){
            switch (buttonID){
                case RED_BUTTON_ID:
                    if (downedButton != null) downedButton.up();
                    downedButton = (DoublePNGButton)buttons.get(buttonID);
                    downedButton.setDown();
                    tileState = RED_STATE;
                    break;
                case BLUE_BUTTON_ID:
                    if (downedButton != null) downedButton.up();
                    downedButton = (DoublePNGButton)buttons.get(buttonID);
                    downedButton.setDown();
                    tileState = BLUE_STATE;
                    break;
                case YELLOW_BUTTON_ID:
                    if (downedButton != null) downedButton.up();
                    downedButton = (DoublePNGButton)buttons.get(buttonID);
                    downedButton.setDown();
                    tileState = YELLOW_STATE;
                    break;
                case SAVE_BUTTON_ID:
                    editstate.saveMap();
                    break;
            }
        }
    }

}
