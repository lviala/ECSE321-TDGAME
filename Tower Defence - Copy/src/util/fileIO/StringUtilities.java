package util.fileIO;

/**
 * Created by Francis O'Brien - 1/28/2015 - 5:29 PM
 */

public class StringUtilities {

    private StringUtilities(){} ///< This is a static class

    /// Return array of strings corresponding to lines in file (***BE VERY CAREFUL: this will crash the program if you aren't actually parsing strings)
    public static String[] toStringArray(Object[] lines){
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

}
