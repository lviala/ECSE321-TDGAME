package gui.control.states;

/**
 * Created by Francis O'Brien - 1/7/2015 - 9:29 AM
 */

public enum GUIStateIDs {

    /// Menu States ///
    MAIN_MENU(0),
    SETTINGS(1),
    CONTROLS(2),

    /// Play States ///
    PLAY_MAIN(3),

    ///Map Select///
    MAP_TYPE(4),
    RANDOM_SELECT(5),
    MAP_SELECT(6),
    EDITOR_MAIN(7),
    EDITOR_SELECT(8);

    public final int ID;

    GUIStateIDs(int id){
        ID = id;
    }
}
