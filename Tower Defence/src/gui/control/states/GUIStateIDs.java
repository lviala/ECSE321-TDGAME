package gui.control.states;

/**
 * Created by Francis O'Brien - 1/7/2015 - 9:29 AM
 */

public enum GUIStateIDs {

    /// Menu States ///
    MAIN_MENU(0),
    SETTINGS(1),

    /// Play States ///
    PLAY_MAIN(2);

    public final int ID;

    GUIStateIDs(int id){
        ID = id;
    }
}
