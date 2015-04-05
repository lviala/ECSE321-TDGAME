package gui.control.states;

import core.Core;
import data.Textures;
import gui.control.GUIState;
import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.buttons.MenuButton;
import map.MapGenerator;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import util.vectors.vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class MapSelect_gui extends GUIState {
    private final int BACK_ID = 0;
    private final int RIGHT_ARROW_ID = 1;
    private final int LEFT_ARROW_ID = 2;
    private final int PLAY_ID = 3;

    boolean vsync;
    boolean fullscreen;

    private ArrayList<Image> mapPNGs;
    private ArrayList<String> mapNames;
    int mapIndex = 0;


    public MapSelect_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(BACK_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 500), Textures.BACK_BUTTON_TEXTURE));
        addElement(new DoublePNGButton(RIGHT_ARROW_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.RIGHT_ARROW_IDLE.getWidth() / 2) + 300, 200 - (Textures.RIGHT_ARROW_IDLE.getHeight() / 2)), Textures.RIGHT_ARROW_IDLE, Textures.RIGHT_ARROW_MO, null));
        addElement(new DoublePNGButton(LEFT_ARROW_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.LEFT_ARROW_IDLE.getWidth() / 2) - 300, 200  - (Textures.LEFT_ARROW_IDLE.getHeight() / 2)), Textures.LEFT_ARROW_IDLE, Textures.LEFT_ARROW_MO, null));
        addElement(new MenuButton(PLAY_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Textures.BACK_BUTTON_TEXTURE.getWidth() / 2), 325), Textures.PLAY_BUTTON_TEXTURE));


    }

    @Override
    public void draw(Graphics graphics){
        for (GUIElement e : elements){
            e.draw();
        }
        for (Button b : buttons) {
            b.draw();
        }

        if (currentOverlay != null){
            currentOverlay.draw();
        }

        Image currentMap = mapPNGs.get(mapIndex);
        currentMap.setFilter(Image.FILTER_NEAREST);
        float scaleFactor = 400 / currentMap.getWidth();
        currentMap.draw((controller.getGameContainer().getWidth() / 2) - ((currentMap.getWidth() * scaleFactor) / 2), 200 - ((currentMap.getHeight() * scaleFactor) / 2), scaleFactor);

        String name = mapNames.get(mapIndex);
        graphics.drawString(name, (controller.getGameContainer().getWidth() / 2) - (graphics.getFont().getWidth(name) / 2), 50);

    }

    @Override
    public void enter() {
        HashMap<String, Image> mapData = MapGenerator.loadMaps();

        mapPNGs = new ArrayList<Image>();
        mapNames = new ArrayList<String>();

        for (Map.Entry<String, Image> entry : mapData.entrySet()) {
            mapPNGs.add(entry.getValue());
            mapNames.add(entry.getKey().substring(0, entry.getKey().length() - 4));
        }
    }

    @Override
    public void exit() {

    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        switch (buttonID){
            case BACK_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.MAP_TYPE.ID);
                }
                break;
            case RIGHT_ARROW_ID:
                mapIndex++;
                mapIndex %= mapPNGs.size();
                break;
            case LEFT_ARROW_ID:
                if (--mapIndex < 0) mapIndex = mapPNGs.size() - 1;
                mapIndex %= mapPNGs.size();
                break;
            case PLAY_ID:
                MapGenerator.randomSize = null;
                MapGenerator.seletedLevel = mapPNGs.get(mapIndex);
                controller.getStateBasedGame().enterState(Core.PLAY);
                break;
        }
    }
}
