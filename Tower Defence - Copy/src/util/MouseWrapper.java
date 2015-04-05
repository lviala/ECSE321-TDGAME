package util;

import util.vectors.vec2;
import org.lwjgl.input.Mouse;

/**
 * Created by Francis O'Brien - 1/7/2015 - 3:32 AM
 */

public class MouseWrapper {

    private vec2 position;
    private int windowHeight;

    public MouseWrapper(int windowHeight){
        this.windowHeight = windowHeight;
        position = new vec2(Mouse.getX(), windowHeight - Mouse.getY());
    }

    public void update(){
        position.x = org.lwjgl.input.Mouse.getX();
        position.y = windowHeight - Mouse.getY();
    }

    public vec2 getPosition(){ return position; }

}
