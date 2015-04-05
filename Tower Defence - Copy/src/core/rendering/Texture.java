package core.rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Francis O'Brien - 1/7/2015 - 1:00 AM
 *
 * This class is a wrapper for Slick2D's Image class, it encapsulates image loading code to tidy up classes using images.
 *
 * FUTURE: this class will contain multiple draw methods that will allow for app scalability
 */

public class Texture {

    private Image image;

    /// Single file constructor
    public Texture(String path){
       image = loadImage(path);
    }

    /// Sprite sheet constructor (might never be used)
    public Texture(SpriteSheet ss, int i, int j){
        image = loadImage(ss, i ,j);
    }

    public Texture(Image image) {
        this.image = image;
    }

    /// Draw the image to the buffer
    public void draw(float x, float y){
        image.draw(x, y);
    }

    /// Apply color filter and draw the image to the buffer
    public void draw(float x, float y, Color filter){
        image.draw(x, y, filter);
    }

    /// Draw the image to the buffer offset by its centre
    public void drawCentre(float x, float y){
        image.draw(x - image.getWidth() / 2, y - image.getHeight() / 2);
    }

    /// Draw the image to the buffer offset by its centre and apply color filter
    public void drawCentre(float x, float y, Color color) {
        image.draw(x - image.getWidth() / 2, y - image.getHeight() / 2, color);
    }

    /// Getters ///
    public int getWidth(){ return image.getWidth(); }
    public int getHeight(){ return image.getHeight(); }
    public Image getImage(){ return image; }

    public void setRotation(float angle){
        image.setRotation(angle);
    }



    /// Private functions ///

    /// Load image from single file
    private Image loadImage(String path){
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

    /// Load image from sprite sheet
    private Image loadImage(SpriteSheet ss, int i, int j){
        return ss.getSprite(i, j);
    }


    public Texture deepCopy() {
        return new Texture(image.copy());
    }
}
