package entities.critters;

import map.path.PathTile;
import util.fileIO.Reader;
import util.fileIO.StringUtilities;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Francis O'Brien - 1/27/2015 - 7:37 PM
 *
 * This class and the wave class are extremely convoluted and quite inefficient, they will need to be refactored at some point for readability
 *
 */

public class CritterManager {

    private Stack<Wave> waves = new Stack<Wave>();
    private LinkedList<Critter> liveCritters;
    private Stack<Critter> unspawnedCritters;
    private ArrayList<Critter> toBeRemoved;

    private Wave current_wave;
    private PathTile start_tile, end_tile;
    private boolean waveStarted = false;
    private boolean levelEnded = false;
    private int waveCritters = 0;

    public CritterManager(String level_wave_file, PathTile start_tile, PathTile end_tile){
        this.start_tile = start_tile;
        this.end_tile = end_tile;

        liveCritters = new LinkedList<Critter>();
        unspawnedCritters = new Stack<Critter>();
        toBeRemoved = new ArrayList<Critter>();

        constructLevelWaves(level_wave_file);
    }

    public void update(int delta){
        if (waveStarted) {
            if (current_wave.spawnReady()) {
                spawnNext();
            }

            for (Critter c : liveCritters) {
                c.update(delta);
            }

            if (liveCritters.isEmpty() && unspawnedCritters.isEmpty()){
                waveStarted = false;
                if (waves.isEmpty()){
                    levelEnded = true;
                }
            }
        }

        removeFlagged();
    }

    public void render(){
        if (waveStarted) {
            for (Critter c : liveCritters) {
                c.draw();
            }
        }
    }

    public void startNextWave(){
        if (!levelEnded && !waveStarted) {
            waveStarted = true;
            loadNextWave();
            current_wave.start();
        }
    }

    public void reachedEnd(Critter critter){
        // Remove life
        toBeRemoved.add(critter);
    }

    private void spawnNext(){
        if (!unspawnedCritters.isEmpty()) {
            Critter newCritter = unspawnedCritters.pop();
            liveCritters.add(newCritter);
            newCritter.spawn(start_tile);
        }
    }

    private void removeFlagged(){
        for (Critter c : toBeRemoved){
            liveCritters.remove(c);
            //waveCritters;
        }
    }

    private void loadNextWave(){
        liveCritters.clear();
        unspawnedCritters.clear();

        current_wave = waves.pop();

        System.out.print("loaded");
        waveCritters = current_wave.critters.size();
        for (int i = current_wave.critters.size() - 1; i >= 0; i--){
            unspawnedCritters.push(Critter.create(current_wave.critters.get(i), this));
        }
        spawnNext();
    }

    private void constructLevelWaves(String level_wave_file){
        Reader in = new Reader(level_wave_file);

        String[] lines = in.getStringLineArray();
        ArrayList<Wave> inOrderWaveList = new ArrayList<Wave>();

        int index = 0;

        while (!lines[index].equals("-end")){

            if (lines[index].equals("-wave")){
                index++;
                ArrayList<String> subArray = new ArrayList<String>();
                while (!lines[index].equals("-wave") && !lines[index].equals("-end")){
                    subArray.add(lines[index++]);
                }
                inOrderWaveList.add(new Wave(StringUtilities.toStringArray(subArray.toArray())));
            }

        }

        for (int i = inOrderWaveList.size() - 1; i >= 0; i--){
            waves.push(inOrderWaveList.get(i));
        }

    }

}
