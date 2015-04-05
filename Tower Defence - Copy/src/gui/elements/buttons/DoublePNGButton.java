package gui.elements.buttons;

import core.rendering.Texture;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 3/10/2015 - 8:39 AM
 */

public class DoublePNGButton extends Button {

    private Texture idle;
    private Texture mouseover;
    private Texture downed;

    private boolean isDowned = false;

    public DoublePNGButton(int id, vec2 pos, Texture idle, Texture mouseover, Texture downed) {
        super(id, pos, idle);

        this.idle = idle;
        this.mouseover = mouseover;
        this.downed = downed;
    }

    @Override
    public void draw() {
        if (isDowned) downed.draw(position.x, position.y);
        else {
            if (isMouseover) mouseover.draw(position.x, position.y);
            else idle.draw(position.x, position.y);
        }
    }

    @Override
    protected void internalClickedResponse(int mouseButton, int clickCount) {

    }

    public void setDown() {
        isDowned = true;
    }

    public void up() {
        isDowned = false;
    }
}
