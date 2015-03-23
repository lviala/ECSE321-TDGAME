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
    public static final Texture BLUE_DOT_TEXTURE = new Texture("/res/textures/entities/blue_dot.png"); ///< Test critter
    public static final Texture UGLY_CREEP_TEXTURE = new Texture("/res/textures/entities/damn_ugly_creep.png"); ///< Test critter
    public static final Texture SHIT_TOWER_TEXTURE = new Texture("/res/textures/entities/shit_tower.png"); ///< Test critter

    public static final Texture SNIPER_TOWER_TEXTURE = new Texture("res/textures/entities/sniper_tower.png"); ///< Test tower
    public static final Texture SLOW_TOWER_TEXTURE = new Texture("res/textures/entities/slow_tower.png"); ///< Test tower
    public static final Texture RAPID_TOWER_TEXTURE = new Texture("res/textures/entities/rapid_tower.png"); ///< Test tower

    /// Tile textures
    public static final Texture GRASS_TILE_TEXTURE = new Texture("/res/textures/tiles/grass_tile_64.png");
    public static final Texture SAND_TILE_TEXTURE = new Texture("/res/textures/tiles/sand_tile_64.png");

    public static final Texture ALT_GRASS_DARK_TEXTURE = new Texture("/res/textures/tiles/alt_grass_dark_64.png");
    public static final Texture ALT_GRASS_LIGHT_TEXTURE = new Texture("/res/textures/tiles/alt_grass_light_64.png");
    public static final Texture ALT_PATH_TEXTURE = new Texture("/res/textures/tiles/alt_path_64.png");

    /// Button textures

        /// Main menu
    public static final Texture PLAY_BUTTON_TEXTURE = new Texture("/res/textures/gui/playbutton.png");
    public static final Texture SETTINGS_BUTTON_TEXTURE = new Texture("/res/textures/gui/settingsbutton.png");
    public static final Texture EXIT_BUTTON_TEXTURE = new Texture("/res/textures/gui/exitbutton.png");
    public static final Texture BACK_BUTTON_TEXTURE = new Texture("/res/textures/gui/backbutton.png");

        /// Play GUI
    public static final Texture SNIPER_BUTTON_IDLE = new Texture("/res/textures/gui/play_state/sniper_tower_button_id.png");
    public static final Texture SNIPER_BUTTON_MOUSEOVER = new Texture("/res/textures/gui/play_state/sniper_tower_button_mo.png");
    public static final Texture SNIPER_BUTTON_DOWN = new Texture("/res/textures/gui/play_state/sniper_tower_button_down.png");
    public static final Texture SNIPER_BUTTON_DOWNALT = new Texture("/res/textures/gui/play_state/sniper_tower_button_darker.png");

    /// Toggle textures
    public static final Texture TOGGLE_ON_TEXTURE = new Texture("/res/textures/gui/toggle_on.png");
    public static final Texture TOGGLE_OFF_TEXTURE = new Texture("/res/textures/gui/toggle_off.png");

    /// Tag textures
    public static final Texture VSYNC_TAG_TEXTURE = new Texture("/res/textures/gui/vsync_tag.png");
    public static final Texture FULLSCREEN_TAG_TEXTURE = new Texture("/res/textures/gui/fullscreen_tag.png");

    /// Particle textures
    public static final Texture SQAURE_PARTICLE_TEXTURE = new Texture("/res/textures/gui/fullscreen_tag.png");

    /// Background textures
    public static final Texture PLAY_MENU_BACKGROUND = new Texture("res/textures/gui/play_menu_back.png");

}
