package util.color;

import org.lwjgl.util.Color;

/**
 * Created by Francis O'Brien - 1/7/2015 - 1:51 PM
 */

public class ColorManipulation {

    private ColorManipulation(){} ///< static class, no instance

    /// this function will lighten or darken a color using a redistributed multiply ***INCOMPLETE***
    public Color multiply(Color color, float factor){
        int[] values = {(int)(color.getRed() * factor), (int)(color.getGreen() * factor), (int)(color.getBlue() * factor)};

        int max = 0;
        for (int i = 0; i < 3; i++){
            if (values[i] > max) max = values[i];
        }

        if (max <= 255) return new Color(values[0], values[1], values[2]);
        return null;

    }

}
