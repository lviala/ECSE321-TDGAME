package data;

import core.rendering.Texture;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:02 AM
 *
 * This is a static class that will hold the single instance of the textures used throughout the application
 */

public final class Textures {

    private Textures() {} ///< This is a static class, a private constructor prevents any instance being created

    /// Entity textures
    public static final Texture PLAYER_TEXTURE = new Texture("/res/textures/entities/player.png"); ///< Test player
    public static final Texture RED_DOT_TEXTURE = new Texture("/res/textures/entities/red_dot.png"); ///< Test critter
    public static final Texture UGLY_CREEP_TEXTURE = new Texture("/res/textures/entities/damn_ugly_creep.png"); ///< Test critter

    /// Tile textures
    public static final Texture GRASS_TILE_TEXTURE = new Texture("/res/textures/tiles/grass_tile_64.png");
    public static final Texture SAND_TILE_TEXTURE = new Texture("/res/textures/tiles/sand_tile_64.png");

    public static final Texture ALT_GRASS_DARK_TEXTURE = new Texture("/res/textures/tiles/alt_grass_dark_64.png");
    public static final Texture ALT_GRASS_LIGHT_TEXTURE = new Texture("/res/textures/tiles/alt_grass_light_64.png");
    public static final Texture ALT_PATH_TEXTURE = new Texture("/res/textures/tiles/alt_path_64.png");

    /// Button textures
    public static final Texture PLAY_BUTTON_TEXTURE = new Texture("/res/textures/gui/playbutton.png");
    public static final Texture SETTINGS_BUTTON_TEXTURE = new Texture("/res/textures/gui/settingsbutton.png");
    public static final Texture EXIT_BUTTON_TEXTURE = new Texture("/res/textures/gui/exitbutton.png");
    public static final Texture BACK_BUTTON_TEXTURE = new Texture("/res/textures/gui/backbutton.png");

    /// Toggle textures
    public static final Texture TOGGLE_ON_TEXTURE = new Texture("/res/textures/gui/toggle_on.png");
    public static final Texture TOGGLE_OFF_TEXTURE = new Texture("/res/textures/gui/toggle_off.png");

    /// Tag textures
    public static final Texture VSYNC_TAG_TEXTURE = new Texture("/res/textures/gui/vsync_tag.png");
    public static final Texture FULLSCREEN_TAG_TEXTURE = new Texture("/res/textures/gui/fullscreen_tag.png");

}
