package gui.elements.non_interfaceable;

import data.Textures;
import entities.Player;
import entities.towers.Tower;
import gui.elements.GUIElement;
import map.TileMap;
import org.newdawn.slick.Color;
import util.MouseWrapper;


/**
 * Created by Francis O'Brien - 3/9/2015 - 4:27 AM
 */

public class TowerAlpha extends GUIElement {

    boolean buildablePosition = true;

    private Tower.Type type;
    private TileMap map;
    private MouseWrapper mouse;
    private Player player;

    private static Color legal = new Color(0, 200, 0, 200);
    private static Color illegal = new Color(255, 0, 0, 200);
    private static Color lackingFunds = new Color(255, 0, 0, 200);

    public TowerAlpha (Tower.Type towerType, TileMap map, MouseWrapper mouse, Player player){
        super(mouse.getPosition(), null);

        this.player = player;

        this.type = towerType;
        switch (towerType){
            case SNIPER:
                super.texture = Textures.SNIPER_TOWER_TEXTURE;
                break;
            case RAPID:
                super.texture = Textures.RAPID_TOWER_TEXTURE;
                break;
            case SLOW:
                super.texture = Textures.SLOW_TOWER_TEXTURE;
                break;
        }


        this.map = map;
        this.mouse = mouse;

    }

    public void update(){

        position = mouse.getPosition();

        if (map.withinMap(position)){
            buildablePosition = map.getTile(position.x, position.y).isBuildable();
        }else{
            buildablePosition = false;
        }
    }

    @Override
    public void draw() {
        if (buildablePosition) {
            if (player.getCurrency() >= type.cost()) {
                texture.drawCentre(position.x, position.y, legal);
            }else{
                texture.drawCentre(position.x, position.y, lackingFunds);
            }
        }else{
            texture.drawCentre(position.x, position.y, illegal);
        }
    }

    public Tower.Type getType(){
        return type;
    }
}
