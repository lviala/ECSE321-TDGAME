package map;

import data.Textures;
import map.path.PathTile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:13 PM
 */

public class MapGenerator {

    public static Color GRASS_GEN_COLOR = new Color(0, 255, 0);
    public static Color SAND_GEN_COLOR = new Color(255, 255, 0);
    public static Color PATH_START_COLOR = new Color(0, 0, 255);
    public static Color PATH_END_COLOR = new Color(255, 0, 0);

    public static Tile[][] generate(Image image) {

        Tile[][] tilemap = new Tile[image.getWidth()][image.getHeight()];
        int tileWidth = Textures.GRASS_TILE_TEXTURE.getWidth();
        int tileHeight = Textures.GRASS_TILE_TEXTURE.getHeight();

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                if (image.getColor(i, j).equals(GRASS_GEN_COLOR)) {
                    if ((i + j) % 2 == 0) {
                        tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_DARK_TEXTURE, true);
                    }else{
                        tilemap[i][j] = new Tile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_GRASS_LIGHT_TEXTURE, true);
                    }
                } else if (image.getColor(i, j).equals(SAND_GEN_COLOR)) {
                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);
                }else if (image.getColor(i, j).equals(PATH_START_COLOR)) {
                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);
                    ((PathTile) tilemap[i][j]).setStart(true);
                }else if (image.getColor(i, j).equals(PATH_END_COLOR)) {
                    tilemap[i][j] = new PathTile(new vec2(i * tileWidth, j * tileHeight), Textures.ALT_PATH_TEXTURE);
                    ((PathTile) tilemap[i][j]).setEnd(true);
                }

            }
        }
        return tilemap;
    }

    private MapGenerator(){}
}
