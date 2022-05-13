import javax.sound.sampled.*;


public class BeatFrequency{
    private double frequencyA;
    private double frequencyB;

    public static final int SAMPLE_RATE = 16384;

    public static final double[] FREQUENCIES = {400, 401, 402, 403};
    public static final double[] UNKNOWNS    = {398, 410, 410, 400.5, 0};
    public static final String[] VARS     = {"w", "x", "y", "z", "off"};

    public BeatFrequency(double frequencyA, double frequencyB){
        this.frequencyA = frequencyA;
        this.frequencyB = frequencyB;
    }

    // generates audio data for two simultaneous frequencies
    public byte[] generateBeatFrequency(int seconds) {
        byte[] outputA = generateAudioData(frequencyA, seconds);
        byte[] outputB = generateAudioData(frequencyB, seconds);
        byte[] beatFrequency = new byte[outputA.length];
        for (int i = 0; i < beatFrequency.length; i++) {
            beatFrequency[i] = (byte) ((outputA[i] + outputB[i]) / 2);
        }
        return beatFrequency;
    }

    // generates audio data for the given frequency and duration
    public static byte[] generateAudioData(double frequency, int seconds){
        int samples = seconds * SAMPLE_RATE;
        byte[] output = new byte[samples];
        double period = (double) SAMPLE_RATE / frequency;
        for(int i = 0; i < output.length; i++){
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte) (Math.sin(angle) * 127);
        }
        return output;
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
        AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
        BeatFrequency bf = new BeatFrequency(400, 400.5);
        byte[] buf = bf.generateBeatFrequency(16);

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