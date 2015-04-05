package map;

import data.Textures;
import map.path.PathTile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import util.vectors.ivec2;
import util.vectors.vec2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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

    public static Image seletedLevel = null;
    public static Size randomSize = null;

    public enum Size{
        SMALL(16, 7),
        MEDIUM(18, 8),
        LARGE(20, 9);

        public int w, h;

        Size(int w, int h){
            this.w = w;
            this.h = h;
        }
    }

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

    public static Tile[][] generate(){
        if (seletedLevel == null && randomSize != null) return generate(randomSize.w, randomSize.h);
        else if (seletedLevel != null && randomSize == null) return generate(seletedLevel);
        else {
            throw new RuntimeException("Improper Map Specification");
        }
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

    public static Tile[][] generate(int pWidth, int pHeight){

        int tileSize = Textures.ALT_PATH_TEXTURE.getHeight();
        int width = pWidth;
        int height = pHeight;
        int currentTile = 0;
        int next;
        boolean lastAcross = false;
        Tile[][] tilemap = new Tile[width][height];


        for (int row = 0; row < width; row++) {
            if (row == 0) {
                currentTile = (int) (Math.random() * height);
                tilemap[row][currentTile] = new PathTile(new vec2(row*tileSize,currentTile* tileSize), Textures.ALT_PATH_TEXTURE);
                ((PathTile) tilemap[row][currentTile]).setStart(true);
            } else if (row == width - 1) {
                tilemap[row][currentTile] = new PathTile(new vec2(row*tileSize,currentTile* tileSize), Textures.ALT_PATH_TEXTURE);
                ((PathTile) tilemap[row][currentTile]).setEnd(true);
                for (int i = 0; i < height; i++) {

                    if (tilemap[row][i] == null) {
                        if ((i+row) % 2 == 0) { ///< Two shades of green are used to form a checkered pattern, this makes i easier to differentiate individual tiles
                            tilemap[row][i] = new Tile(new vec2(row*tileSize, i * tileSize), Textures.ALT_GRASS_DARK_TEXTURE, true);
                        }else{
                            tilemap[row][i] = new Tile(new vec2(row*tileSize, i* tileSize), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
                        }
                    }
                }
                break;
            }
            if (!lastAcross) {
                next = (int) (Math.random() * 3);
            } else {
                next = 1;
                lastAcross = false;
            }

            if (next != 1) {
                lastAcross = true;
            }

            //If This is not the first row, we immediately need to create a path tile that joins from the previous row

            if (row != 0) {
                tilemap[row][currentTile] = new PathTile(new vec2(row*tileSize,currentTile* tileSize), Textures.ALT_PATH_TEXTURE);
            }
            while (next != 1) {

                /*
                 * If up is selected, and we are not at the top, then we create a tile above the last tile by decreasing
                 * current tile by 1 and making a new tile. We then set next to be either up or right, as we can't have another a tile go up
                 * and then down again.
                 */
                if (next == 0 && currentTile != 0) {
                    currentTile -= 1;
                    tilemap[row][currentTile] = new PathTile(new vec2(row*tileSize,currentTile* tileSize), Textures.ALT_PATH_TEXTURE);
                    next = (int) ((Math.random() * 2));

                /*
                 * If down is selected, and we are not at the bottom, then we create a tile below the last tile by
                 * increasing current tile by 1 and making a new tile. We then set next to be either down or right, as we can't have another
                 * a tile go down and then up again.
                 */
                } else if (next == 2 && currentTile != height - 1) {
                    currentTile += 1;
                    tilemap[row][currentTile] = new PathTile(new vec2(row*tileSize,currentTile* tileSize), Textures.ALT_PATH_TEXTURE);
                    next = (int) ((Math.random() * 2) + 1);

                    //This is called if we are going out of boundaries. We simply set next to be 1;
                } else {
                    next = 1;
                }
            }
            //Once we have with a column, we want to set the remaining tiles as Terrain.
            for (int i = 0; i < height; i++) {
                if (tilemap[row][i] == null) {
                    if ((i+row) % 2 == 0) { ///< Two shades of green are used to form a checkered pattern, this makes i easier to differentiate individual tiles
                        tilemap[row][i] = new Tile(new vec2(row*tileSize, i * tileSize), Textures.ALT_GRASS_DARK_TEXTURE, true);
                    }else{
                        tilemap[row][i] = new Tile(new vec2(row*tileSize, i* tileSize), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
                    }
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

    public static HashMap<String, Image> loadMaps(){
        System.out.println("Method ran");

        File directory = new File("res/maps");
        HashMap<String, Image> mapData = new HashMap<String, Image>();

        if (directory.isDirectory()){
            File[] files = directory.listFiles();

            for (File file : files){
                String path = file.getPath();                    System.out.println("Is PNG");

                if (path.substring(path.length() - 3, path.length()).equals("png")){
                    try {
                        mapData.put(file.getName(), new Image(path));
                    }catch (SlickException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        return mapData;

    }

    public static TileMap generateEmpty(Size s){

        int tileWidth = Textures.GRASS_TILE_TEXTURE.getWidth(); ///< Map width
        int tileHeight = Textures.GRASS_TILE_TEXTURE.getHeight(); ///< Map height

        Tile[][] tilemap = new Tile[s.w][s.h];
        for (int i = 0; i < s.w; i++){
            for (int j = 0; j < s.h; j ++){
                if ((i + j) % 2 == 0) { ///< Two shades of green are used to form a checkered pattern, this makes i easier to differentiate individual tiles
                    tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_DARK_TEXTURE, true);
                }else{
                    tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
                }
            }
        }

        return new TileMap(tilemap);
    }

    private MapGenerator(){} ///< private constructor to prevent instantiation of this class
}
