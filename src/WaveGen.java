import java.util.Arrays;

/**
 * Class for square, triangle, and sine wave generation
 */
public class WaveGen {

    // number of samples per second
    public static final int SAMPLE_RATE = 16384;

    public static byte[] generateSineWave(double frequency, double amplitude, double phase, int seconds){
        byte[] sineWave = initializeArray(seconds);
        double angularFrequency = 2 * Math.PI * frequency / SAMPLE_RATE;
        for(int t = 0; t < sineWave.length; t++){
            // assume amplitude between 0 and 1.0, multiply by 127 to scale to byte array
            sineWave[t] = (byte) (127 * amplitude * Math.sin(angularFrequency * t + phase));
        }
        return sineWave;
    }

    // TODO: phase
    public static byte[] generateSquareWave(double frequency, double amplitude, double phase, int seconds){
        byte[] squareWave = initializeArray(seconds);
        for(int t = 0; t < squareWave.length; t++){
            squareWave[t] = (byte) (127 * amplitude * Math.pow(-1, Math.floor(2 * frequency * t / SAMPLE_RATE)));
        }
        return squareWave;
    }

    // TODO: implement
    public static byte[] generateTriangleWave(double frequency, double amplitude, double phase, int seconds){
        return null;
    }

    private static byte[] initializeArray(int seconds){
        return new byte[seconds * SAMPLE_RATE];
    }

    public static void main(String[] args) {
        byte[] d = generateSquareWave(400.0, 1.0, 0.0, 16);
        System.out.println(Arrays.toString(d));
    }
}
