package map;

import data.Textures;
import map.path.PathTile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import util.vectors.ivec2;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:13 PM
 *
 * Static class used to generate a new map from a png file
 */

public class MapGenerator {

    public static Color GRASS_GEN_COLOR = new Color(0, 255, 0); ///< Grass pixel color
    public static Color SAND_GEN_COLOR = new Color(255, 255, 0); ///< Basic path pixel color
    public static Color PATH_START_COLOR = new Color(0, 0, 255); ///< Path start pixel color
    public static Color PATH_END_COLOR = new Color(255, 0, 0); ///< Path end pixel color

    /// Returns the start tile in the map ///
    public static PathTile setStart(Tile[][] tiles){
        for (int j = 0; j < tiles[0].length; j++) {
            for (int i = 0; i < tiles.length; i++) {
                if (tiles[i][j] instanceof PathTile) {
                    if (((PathTile) tiles[i][j]).isStart()){
                        return (PathTile) tiles[i][j];
                    }
                }
            }
        }

        throw new RuntimeException("No start tile in map");

    }

    /// Returns the end tile in the map ///
    public static PathTile setEnd(Tile[][] tiles){
        for (int j = 0; j < tiles[0].length; j++) {
            for (int i = 0; i < tiles.length; i++) {
                if (tiles[i][j] instanceof PathTile) {
                    if (((PathTile) tiles[i][j]).isEnd()){
                        return (PathTile) tiles[i][j];
                    }
                }
            }
        }

        throw new RuntimeException("No end tile in map");

    }

    /// Generates a Tile[][] array from the selected png file ///
    public static Tile[][] generate(Image image) {

        boolean startFound = false; ///< Map validity verification boolean
        boolean endFound = false; ///< Map validity verification boolean

        Tile[][] tilemap = new Tile[image.getWidth()][image.getHeight()];

        int tileWidth = Textures.GRASS_TILE_TEXTURE.getWidth(); ///< Map width
        int tileHeight = Textures.GRASS_TILE_TEXTURE.getHeight(); ///< Map height

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {

                if (image.getColor(i, j).equals(GRASS_GEN_COLOR)) {

                    if ((i + j) % 2 == 0) { ///< Two shades of green are used to form a checkered pattern, this makes i easier to differentiate individual tiles
                        tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_DARK_TEXTURE, true);
                    }else{
                        tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
                    }

                } else if (image.getColor(i, j).equals(SAND_GEN_COLOR)) {

                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);

                }else if (image.getColor(i, j).equals(PATH_START_COLOR)) {

                    if  (startFound){
                        throw new RuntimeException("Multiple start tiles found in map file");
                    }

                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);
                    ((PathTile) tilemap[i][j]).setStart(true);

                    startFound = true;

                }else if (image.getColor(i, j).equals(PATH_END_COLOR)) {

                    if  (endFound){
                        throw new RuntimeException("Multiple end tiles found in map file");
                    }

                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);
                    ((PathTile) tilemap[i][j]).setEnd(true);

                    endFound = true;

                }

            }
        }
        return tilemap;
    }

    public static void linkPath(Tile[][] tiles, PathTile start_tile){
        PathTile current = start_tile;

        if  (!current.isStart()) throw new RuntimeException("Not valid start tile");

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
                if (!current.isEnd()){
                    throw new RuntimeException("Broken Path");
                }
            }

            firstLoop = false;
        } while (current.getNext() != null);
    }

    private MapGenerator(){} ///< private constructor to prevent instantiation of this class
}
