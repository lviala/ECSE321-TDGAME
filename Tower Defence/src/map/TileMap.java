package map;

import org.newdawn.slick.Image;
import util.vectors.ivec2;

import java.nio.file.Path;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:06 PM
 */

public class TileMap {

    private Tile [][] tiles;
    private PathTile start;


    public TileMap(Image png_map){
        tiles = MapGenerator.generate(png_map);
        setStart();
    }

    public void render(){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                tiles[i][j].draw();
            }
        }
    }

    private void setStart(){
        for (int j = 0; j < tiles[0].length; j++){
            if (tiles[0][j] instanceof PathTile){
                int count = 0;
                if (j > 0 &&tiles[0][j-1] instanceof PathTile) count++;
                if (tiles[1][j] instanceof PathTile) count++;
                if (j < tiles[0].length && tiles[0][j+1] instanceof PathTile) count++;
                if (count == 1){
                    start = (PathTile) tiles[0][j];
                    return;
                }
            }
        }
    }

    private void makePath(){
        PathTile current = start;
        do {
            ivec2 ij = current.get_ij();
            if(tiles[ij.x - 1][ij.y] instanceof PathTile && ij.x > 0 && tiles[ij.x - 1][ij.y] != current.getPrevious()){
                current.setNext((PathTile) tiles[ij.x - 1][ij.y]);
                current.getNext().setPrevious(current);

            }
            if(tiles[ij.x][ij.y - 1] instanceof PathTile && ij.y > 0 && tiles[ij.x][ij.y - 1] != current.getPrevious()){
                current.setNext((PathTile) tiles[ij.x][ij.y - 1]);
                current.getNext().setPrevious(current);

            }
            if(tiles[ij.x + 1][ij.y] instanceof PathTile && ij.x < tiles.length && tiles[ij.x + 1][ij.y] != current.getPrevious()){
                current.setNext((PathTile) tiles[ij.x + 1][ij.y]);
                current.getNext().setPrevious(current);

            }
            if(tiles[ij.x][ij.y + 1] instanceof PathTile && ij.y < tiles[0].length && tiles[ij.x][ij.y + 1] != current.getPrevious()){
                current.setNext((PathTile) tiles[ij.x][ij.y + 1]);
                current.getNext().setPrevious(current);

            }
        } while (current.getNext() != null);
    }

}
