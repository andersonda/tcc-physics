import javax.sound.sampled.*;
import java.util.Arrays;


public class BeatFrequency{
    private double frequencyA;
    private double frequencyB;

    public static final double[] FREQUENCIES = {500, 501, 502, 503, 504};
    public static final double[] UNKNOWNS    = {499, 501.5, 506, 509, 0};
    public static final String[] VARS     = {"A", "B", "C", "D", "off"};

    public BeatFrequency(double frequencyA, double frequencyB){
        this.frequencyA = frequencyA;
        this.frequencyB = frequencyB;
    }

    // generates audio data for two simultaneous frequencies
    public byte[] generateBeatFrequency(int seconds) {
        byte[] outputA = WaveGen.generateSineWave(frequencyA, 1.0, 0.0, seconds);
        byte[] outputB = WaveGen.generateSineWave(frequencyB, 1.0, 0.0, seconds);
        byte[] beatFrequency = new byte[outputA.length];
        for (int i = 0; i < beatFrequency.length; i++) {
            beatFrequency[i] = (byte) ((outputA[i] + outputB[i]) / 2);
        }
        return beatFrequency;
    }

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

    // command line for testing
    public static void main(String[] args) throws LineUnavailableException{
        AudioFormat af = new AudioFormat(WaveGen.SAMPLE_RATE, 8, 1, true, true);
        BeatFrequency bf = new BeatFrequency(500, 501);
        byte[] buf = bf.generateBeatFrequency(16);
        System.out.println(Arrays.toString(buf));
        Clip clip = AudioSystem.getClip();
        clip.open(af, buf, 0, buf.length);
        clip.start();
        try {
            Thread.sleep(16 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}