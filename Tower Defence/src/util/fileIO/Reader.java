package util.fileIO;

import entities.critters.Critter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Stream;

/**
 * Created by Francis O'Brien - 1/28/2015 - 2:42 AM
 */

public class Reader {

    private BufferedReader in;
    private String file;

    public Reader(String file){
        in = initReader(file);
    }

    public String nextLine(){
        try {
        return in.readLine();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(98);
        }
        return null;
    }

    public Object[] getFileStream(){
        return in.lines().toArray();
    }

    /// Return array of strings corresponding to lines in file (***BE VERY CAREFUL: this will crash the program if you aren't actually parsing strings)
    public String[] getStringLineArray(){
        Object[] lines = in.lines().toArray();
        String[] strings = new String[lines.length];

        try { ///This is to ensure we are parsing strings, the program will crash here rather than later
            ((String) lines[0]).concat("");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(97);
        }

        for (int i = 0; i < lines.length; i++){
            strings[i] = (String) lines[i];
        }

        return strings;
    }

    private BufferedReader initReader(String file){
        try{

            BufferedReader in = new BufferedReader(new FileReader(new File(file)));
            return in;

        }catch (Exception e){
            e.printStackTrace();
            System.exit(99);
        }

        return null;
    }
}
