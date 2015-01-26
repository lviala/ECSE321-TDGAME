package data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:21 PM
 */

public class PNGMaps {

    public static final Image testmap_1 = loadImage("/res/maps/testmap_1.png");

    /// Load image from single file
    private static Image loadImage(String path){
        Image image = null;
        try {
            image = new Image(path);
        } catch (SlickException e) { ///< Handle exceptions for failing to load the file i.e it doesn't exist or is corrupted
            e.printStackTrace();
            System.out.println("Image at: " + path + " failed to load");
            System.exit(1);
        }
        return image;
    }

}
