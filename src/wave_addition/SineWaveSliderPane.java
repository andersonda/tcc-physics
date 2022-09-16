package wave_addition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SineWaveSliderPane extends VBox {

    /*
        Inner class representing a slider and its label
     */
    private static final class LabeledSlider extends HBox{
        private Label lblWaveProperty;
        private Slider sliderWaveProperty;

        // TODO: constructor should take a range of valid options (min and max?) for the slider
        public LabeledSlider(String name){
            lblWaveProperty = new Label(name);
            sliderWaveProperty = new Slider();
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(lblWaveProperty);
            this.getChildren().add(sliderWaveProperty);
        }
    }

    private Label lblWaveName;

    private LabeledSlider sliderAmplitude;
    private LabeledSlider sliderPeriod;
    private LabeledSlider sliderPhaseShift;

    public SineWaveSliderPane(String waveName){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(16);
        this.setPadding(new Insets(16, 16, 16, 16));
        lblWaveName = new Label(waveName);
        lblWaveName.setFont(new Font("Courier New", 32));

        initComponents();

        this.getChildren().add(lblWaveName);
        this.getChildren().add(sliderAmplitude);
        this.getChildren().add(sliderPeriod);
        this.getChildren().add(sliderPhaseShift);
    }

    private void initComponents(){
        sliderAmplitude = new LabeledSlider("Amplitude (m)");
        sliderPeriod = new LabeledSlider("Period (s)");
        sliderPhaseShift = new LabeledSlider("Shift (rad)");
    }
}
