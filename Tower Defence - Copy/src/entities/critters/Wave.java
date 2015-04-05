package entities.critters;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 1/27/2015 - 7:37 PM
 *
 * This wave class is slightly un-intuitive, the TimingInfo class holds the data regarding the timing between consequent
 * Critter spawns. It is optimized to minimize repeated values. The timings list holds the time between consequent spawns, however,
 * the indices list holds the index number at which this timing will change; i.e. if the second element of indices is 4, the timing
 * between critters 0 to 3 will be timings[0] and the timing between critters 4+ will be timings[1]. Therefore optimizing for repeated
 * timing values. So a wave with constant timings will have an indices array of size 1, and a timings array of size 1 as well.
 *
 */

public class Wave {

    private TimingInfo timing;

    private long lastSpawnTime = 0;
    private boolean waveStarted = false;

    public ArrayList<Critter.Type> critters;



    public Wave(String[] file_lines){
        constructWave(file_lines);
    }

    public void start(){
        lastSpawnTime = System.currentTimeMillis();
        waveStarted = true;
    }

    public boolean spawnReady(){
        if (waveStarted) {
            if (timing.spawnReady(System.currentTimeMillis() - lastSpawnTime)) {
                lastSpawnTime = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    private void constructWave(String[] file_lines){

        String[] lines = file_lines;
        String[] line_one_info = getLineInfo(lines[0]);
        critters = new ArrayList<Critter.Type>(lines.length);
        critters.add(Critter.Type.valueOf(line_one_info[0]));
        timing = new TimingInfo(Integer.parseInt(line_one_info[1]));

        int previous_delta = Integer.parseInt(line_one_info[1]);

        for (int i = 1; i < lines.length; i++){
            String[] info = getLineInfo(lines[i]);

            critters.add(Critter.Type.valueOf(info[0]));
            int delta = Integer.parseInt(info[1]);
            if (delta != previous_delta){
                timing.addTiming(i, delta);
            }

            previous_delta = delta;
        }

    }

    private String[] getLineInfo(String line){
        int index = line.indexOf(" ");
        String[] info = new String[2];

        info[0] = line.substring(0, index);
        info[1] = line.substring(index + 1, line.length());

        return info;
    }

    /// TimingInfo Subclass ///
    private class TimingInfo{

        private ArrayList<Integer> indices;
        private ArrayList<Integer> timings;

        private int size = 0;
        private int timing_index = 0;
        private int critter_index = 0;

        public TimingInfo(int delta_zero){

            indices = new ArrayList<Integer>();
            timings = new ArrayList<Integer>();
            indices.add(0);
            timings.add(delta_zero);
            size++;
        }

        public void start(){
        }

        public boolean spawnReady(long delta){
            if (!waveStarted){
                throw new RuntimeException("Wave not started");
            }

            if (timing_index == size - 1){}
            else if (this.critter_index == indices.get(timing_index + 1)){
                timing_index++;
            }

            boolean isReady = delta >= timings.get(timing_index);
            if (isReady){
                critter_index++;
            }

            return isReady;
        }

        public Integer[] getValues(int index){
            return new Integer[]{indices.get(index), timings.get(index)};
        }

        private void addTiming(int index, int delta){
            indices.add(index);
            timings.add(delta);
            size++;
        }
    }

}
