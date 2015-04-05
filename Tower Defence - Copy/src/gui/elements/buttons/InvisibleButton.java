package gui.elements.buttons;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/9/2015 - 4:17 AM
 */

public class InvisibleButton extends Button {
    public InvisibleButton(int id, vec2 pos, Texture texture) {
        super(id, pos, null);

    }

    @Override
    public void draw() {

    }

    @Override
    protected void internalClickedResponse(int mouseButton, int clickCount) {

    }
}
