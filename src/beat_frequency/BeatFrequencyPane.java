package beat_frequency;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.Styling;

import java.util.Collections;

public class BeatFrequencyPane extends VBox {

    private ImageView imageSpeaker;
    private ComboBox<String> cbFrequency1Picker;
    private ComboBox<String> cbFrequency2Picker;

    private ToggleButton btnStart;

    private Label lblFrequency1;
    private Label lblFrequency2;

    private SoundPlayer player;

    public  BeatFrequencyPane(){
        initComponents();
        Styling.setDefaultStyling(this);

        // hbox for controls under image
        HBox hbControls = new HBox();
        Styling.setDefaultStyling(hbControls);

        // vboxes for frequency pickers and their labels
        VBox vbFrequency1 = new VBox();
        vbFrequency1.getChildren().addAll(lblFrequency1, cbFrequency1Picker);
        Styling.setDefaultStyling(vbFrequency1);

        VBox vbFrequency2 = new VBox();
        vbFrequency2.getChildren().addAll(lblFrequency2, cbFrequency2Picker);
        Styling.setDefaultStyling(vbFrequency2);

        // combine frequency pickers
        hbControls.getChildren().addAll(vbFrequency1, vbFrequency2);

        // add all to vbox
        this.getChildren().addAll(imageSpeaker, hbControls, btnStart);
    }

    private void initComponents(){
        // load and initialize speaker image
        Image image = new Image("speaker.png");
        imageSpeaker = new ImageView(image);
        imageSpeaker.setPreserveRatio(true);
        imageSpeaker.setFitHeight(200);

        // initialize labels
        lblFrequency1 = new Label("Frequency 1");
        lblFrequency2 = new Label("Frequency 2");

        btnStart = new ToggleButton("Start");

        player = new SoundPlayer();
        cbFrequency1Picker = initCB();
        cbFrequency2Picker = initCB();

        addListenerToCB(cbFrequency1Picker, cbFrequency2Picker);
        addListenerToCB(cbFrequency2Picker, cbFrequency1Picker);
        // select index 0 in both cbs
        cbFrequency1Picker.getSelectionModel().select(0);
        cbFrequency2Picker.getSelectionModel().select(0);

        btnStart.selectedProperty().addListener(
                (observable, oldToggle, newToggle) ->{
                    if(!oldToggle){
                        // start playback
                        double frequencyA = createFrequencyFromCB(cbFrequency1Picker);
                        double frequencyB = createFrequencyFromCB(cbFrequency2Picker);
                        player.start(frequencyA, frequencyB);
                        btnStart.setText("Stop");
                    }
                    else{
                        // stop playback
                        player.stop();
                        btnStart.setText("Start");
                    }
                }
        );
    }

    private void addListenerToCB(ComboBox<String> cbFirst, ComboBox<String> cbSecond) {
        cbFirst.getSelectionModel().selectedItemProperty().addListener(
                (options, oldItem, newItem) -> {
                    // reset the toggle button and stop playback when new frequency is selected
                    btnStart.setSelected(false);
                    player.stop();
                    // remove 1's selected item from 2 (no duplicate frequencies)
                    cbSecond.getItems().remove(newItem);
                    // add 1's previously selected item to 2
                    if(oldItem != null) {
                        cbSecond.getItems().add(oldItem);
                    }
                    // sort 2
                    Collections.sort(cbSecond.getItems());
                }
        );
    }

    private static ComboBox<String> initCB(){
        // create empty CB
        ComboBox<String> cb = new ComboBox<>();
        // add all known frequencies to the cb as strings
        for(double frequency : Frequencies.FREQUENCIES){
            cb.getItems().add(frequency + "");
        }
        // add all unknown frequency var names to cb
        for(String var : Frequencies.VARS){
            cb.getItems().add(var);
        }
        return cb;
    }

    private static double createFrequencyFromCB(ComboBox<String> cb){
        String selection = cb.getValue();
        try{
            return Double.parseDouble(selection);
        }
        catch(NumberFormatException e){
            return Frequencies.getVariableFrequency(selection);
        }
    }
}