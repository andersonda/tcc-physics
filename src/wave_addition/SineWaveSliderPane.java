package wave_addition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import utilities.Styling;

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
            Styling.setDefaultStyling(this);
            this.getChildren().addAll(lblWaveProperty, sliderWaveProperty);
        }
    }

    private Label lblWaveName;

    private LabeledSlider sliderAmplitude;
    private LabeledSlider sliderPeriod;
    private LabeledSlider sliderPhaseShift;

    public SineWaveSliderPane(String waveName){
        Styling.setDefaultStyling(this);
        lblWaveName = new Label(waveName);
        lblWaveName.setFont(new Font("Courier New", 32));

        initComponents();

        this.getChildren().addAll(lblWaveName, sliderAmplitude, sliderPeriod, sliderPhaseShift);
    }

    private void initComponents(){
        sliderAmplitude = new LabeledSlider("Amplitude (m)");
        sliderPeriod = new LabeledSlider("Period (s)");
        sliderPhaseShift = new LabeledSlider("Shift (rad)");
    }
}
