package core;

import core.gamestates.Menu;
import core.gamestates.Play;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 *
 *Slick2D is a 2D game framework that wraps LWJGL and saves us the pain of setting up and managing OpenGL
 *LWJGL -> OpenGL for rendering, OpenAL for audio, OpenCL for hardware management (input, GPU management, etc...) and portability
 *While basic shaders are provided with Slick2D, we might implement our own in the future
 *
 * This class sets up Slick2D and our initial display options
 * Slick2D was selected as it is a rather lightweight framework that completely encapsulates OpenGL
 * and allows it to be passed as a graphics context object which is much more intuitive.
 * Moreover, Slick2D allows us to maintain a lot of control over the basic functionality of our game,
 * and except for the main game loop, all other game functionality can be completely customized.
 *
 * We will also be using Slick2D's input handling as it is quit neatly implemented.
 */

public class Core extends StateBasedGame{

    /// Title strings that will appear at the top of the window
    public static final String TITLE = "Tower Defence";
    public static final String VERSION = "v0.0.1";
    public static final String FULL_TITLE = TITLE + "  " + VERSION;

    /// Window dimensions
    public static final int WIDTH = 1280; ///< Default resolution, game will be scalable
    public static final int HEIGHT = WIDTH * 9 / 16; ///<Maintain a 16:9 aspect ratio

    /// Enumeration of game states
    public static final int MENU = 0;
    public static final int PLAY = 1;


    public static void main(String[] args) {
        try{
            /// Generate window and set default graphics options
            AppGameContainer appgc = new AppGameContainer(new ScalableGame(new Core(FULL_TITLE), WIDTH, HEIGHT, true)); ///< Give instance of this class (extension of StateBasedGame) to Slick2Ds game container.
                                                                                                                        /// Using ScalableGame allows us to resize and maintain aspect ratio
            appgc.setDisplayMode(WIDTH, HEIGHT, false); ///< Set up initial display (window) properties: width, height, fullscreen (initialized to false)
            appgc.setShowFPS(true); ///< Enable show FPS
            appgc.setVSync(false); ///< Disable Vsync for testing purposes (we can see how certain features affect relative FPS)
            appgc.setAlwaysRender(true); ///< Enables rendering when other windows are active
            appgc.start();
        } catch (SlickException e) { ///< Handle exceptions if game initialization fails
            e.printStackTrace();
            System.exit(0);
        }

    }

    public Core(String title) {

        /// StateBasedGame constructor
        super(title);

        /// Declare game states
        this.addState(new Menu(MENU));
        this.addState(new Play(PLAY));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

        /// Initialize game states
        this.getState(MENU).init(gameContainer, this);
        this.getState(PLAY).init(gameContainer, this);

        /// Tell the game loop which pair of Update and Render functions we will be using at first
        this.enterState(MENU); ///< Initialize to Menu
    }
}
