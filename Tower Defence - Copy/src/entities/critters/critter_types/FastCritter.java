package entities.critters.critter_types;

import core.rendering.Texture;
import entities.critters.Critter;
import entities.critters.CritterManager;

/**
 * Created by Francis O'Brien - 1/28/2015 - 3:41 PM
 */

public class FastCritter extends Critter {

    public FastCritter(Texture texture, CritterManager manager, int level) {
        super(texture, manager, level);
        reward = 50;
        DEFAULT_SPEED *= 1.6;
    }
}
