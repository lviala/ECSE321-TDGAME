package map;

import map.path.PathTile;
import org.newdawn.slick.Image;
import util.vectors.ivec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:06 PM
 */

public class TileMap {

    private Tile [][] tiles;
    private PathTile start_tile;
    private PathTile end_tile;


    public TileMap(Image png_map){
        tiles = MapGenerator.generate(png_map);
        setStart();
        makePath();
        System.out.println(end_tile.getPosition().x/32 + " : " + end_tile.getPosition().y/32);
    }

    public void render(){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                tiles[i][j].draw();
            }
        }
    }

    public PathTile getStartTile() { return start_tile; }
    public PathTile getEndTile() { return end_tile; }

    private void setStart(){
        for (int j = 0; j < tiles[0].length; j++) {
            for (int i = 0; i < tiles.length; i++) {
                if (tiles[i][j] instanceof PathTile) {
                    if (((PathTile) tiles[i][j]).isStart()){
                        start_tile = (PathTile) tiles[i][j];
                        return;
                    }
                }
            }
        }
    }

    private void makePath(){
        PathTile current = start_tile;
        boolean firstLoop = true;
        do {
            if (!firstLoop) current = current.getNext();
            int count = 0;
            ivec2 ij = current.get_ij();
            if (ij.x > 0) {
                if (tiles[ij.x - 1][ij.y] instanceof PathTile && ij.x > 0 && tiles[ij.x - 1][ij.y] != current.getPrevious()) {
                    current.setNext((PathTile) tiles[ij.x - 1][ij.y]);
                    current.getNext().setPrevious(current);
                    count++;
                }
            }
            if (ij.y > 0) {
                if (tiles[ij.x][ij.y - 1] instanceof PathTile && ij.y > 0 && tiles[ij.x][ij.y - 1] != current.getPrevious()) {
                    current.setNext((PathTile) tiles[ij.x][ij.y - 1]);
                    current.getNext().setPrevious(current);
                    count++;
                }
            }
            if (ij.x < tiles.length - 1) {
                if (tiles[ij.x + 1][ij.y] instanceof PathTile && ij.x < tiles.length && tiles[ij.x + 1][ij.y] != current.getPrevious()) {
                    current.setNext((PathTile) tiles[ij.x + 1][ij.y]);
                    current.getNext().setPrevious(current);
                    count++;
                }
            }
            if (ij.y < tiles[0].length - 1) {
                if (tiles[ij.x][ij.y + 1] instanceof PathTile && ij.y < tiles[0].length && tiles[ij.x][ij.y + 1] != current.getPrevious()) {
                    current.setNext((PathTile) tiles[ij.x][ij.y + 1]);
                    current.getNext().setPrevious(current);
                    count++;
                }
            }
            if (count > 1) throw new RuntimeException("Illegal Path");
            if (current.getNext() == null){
                if (current.isEnd()){
                    end_tile = current;
                }else {
                    throw new RuntimeException("Broken Path");
                }
            }

            firstLoop = false;
        } while (current.getNext() != null);
    }

}
