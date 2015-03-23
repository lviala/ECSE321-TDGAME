package particles;

import core.rendering.Texture;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import util.vectors.vec2;

import java.io.File;
import java.io.IOException;

/**
 * Created by Francis O'Brien - 3/8/2015 - 9:31 AM
 */

public class ParticleUtilities {

    public static ParticleSystem createSystem(Texture particle, int max){
        ParticleSystem system = new ParticleSystem(particle.getImage(), max);
        system.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);

        return system;
    }

    public static ParticleEmitter createEmitter(String filepath, vec2 pos){
        ParticleEmitter emitter;
        try {
            File xmlFile = new File(filepath);
            emitter = ParticleIO.loadEmitter(xmlFile);
            return emitter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
