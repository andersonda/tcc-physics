package beat_frequency;

import javax.sound.sampled.*;

public class SoundPlayer implements Runnable {
    private Thread t;
    private Clip clip;

    public SoundPlayer() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start(double frequencyA, double frequencyB) {
        if (t == null) {
            t = new Thread(this, "beat_frequency.SoundPlayer");
            t.start();
        }
        byte[] waveA = WaveGen.generateSineWaveBytes(1.0, frequencyA, 0.0, 16);
        byte[] waveB = WaveGen.generateSineWaveBytes(1.0, frequencyB, 0.0, 16);
        byte[] buf = WaveGen.sumByteWaves(waveA, waveB);
        AudioFormat af = new AudioFormat(beat_frequency.WaveGen.SAMPLE_RATE, 8, 1, true, true);
        try {
            clip.open(af, buf, 0, buf.length);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY); // playback loop until stopped
        clip.start(); // start playing audio
    }

    public void stop() {
        clip.stop();  // stop playing audio
        clip.close(); // free up clip for next frequency
    }

    @Override
    public void run() {}
}