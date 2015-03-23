package entities.towers;


import map.TileMap;

import java.util.ArrayList;


/**
 * Created by Francis O'Brien - 3/2/2015 - 6:26 AM
 */

public class TowerManager {

    private ArrayList<Tower> towers;
    private ArrayList<Tower> toBeAdded;
    private ArrayList<Tower> toBeRemoved;

    private TileMap map;

    public TowerManager(TileMap map){
        this.map = map;

        towers = new ArrayList<Tower>();
        toBeAdded = new ArrayList<Tower>();
        toBeRemoved = new ArrayList<Tower>();
    }

    public void update(int delta){
        addTowers();

        for (Tower t : towers){
            t.update(delta);
        }

        removeTowers();
    }

    private void addTowers() {
        for (Tower t : toBeAdded){
            towers.add(t);
        }

        toBeAdded.clear();
    }

    private void removeTowers() {
        for (Tower t : toBeRemoved){
            towers.remove(t);
        }

        toBeRemoved.clear();
    }

    public void render(){
        for (Tower t : towers){
            t.draw();
        }
    }

    public void addTower(Tower tower){
        System.out.println(tower.getPos().x + " ; " + tower.getPos().y);
        toBeAdded.add(tower);
    }

    public void removeTower(Tower tower){
        toBeRemoved.add(tower);
    }

}
