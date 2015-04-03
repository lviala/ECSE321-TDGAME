package particles;

import core.rendering.Texture;
import org.newdawn.slick.particles.ConfigurableEmitter;
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

    public static ConfigurableEmitter createEmitter(String filepath, vec2 pos){
        ConfigurableEmitter emitter;
        try {
            File xmlFile = new File(filepath);
            emitter = ParticleIO.loadEmitter(xmlFile);
            emitter.setPosition(pos.x, pos.y, false);
            return emitter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConfigurableEmitter createSniperEmitter(vec2 pos){
        return createEmitter("data/sniper_emitter.xml", pos);
    }

    public static ConfigurableEmitter createRapidEmitter(vec2 pos){
        return createEmitter("data/rapid_emitter.xml", pos);
    }
    public static ConfigurableEmitter createSlowEmitter(vec2 pos){
        return createEmitter("data/slow_emitter.xml", pos);
    }
}
