package beat_frequency;

public class Frequencies {
    public static final double[] FREQUENCIES = {500, 501, 502, 503, 504};
    public static final double[] UNKNOWNS    = {499, 501.5, 506, 509, 0};
    public static final String[] VARS     = {"A", "B", "C", "D", "off"};

    // converts from a variable name to its frequency
    public static double getVariableFrequency(String v){
        // find variable position
        for(int i = 0; i < VARS.length; i++){
            if(VARS[i].equals(v)){
                // return the frequency for that unknown
                return UNKNOWNS[i];
            }
        }
        // return default frequency of 0 for invalid var names
        return 0;
    }
}
