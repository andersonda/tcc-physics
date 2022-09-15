package beat_frequency;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Collections;

public class BeatFrequencyPane extends VBox {

    private ImageView imageSpeaker;
    private ComboBox<String> cbFrequency1Picker;
    private ComboBox<String> cbFrequency2Picker;

    private ToggleButton btnStart;

    private Label lblFrequency1;
    private Label lblFrequency2;

    public  BeatFrequencyPane(){
        initComponents();

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.getChildren().add(imageSpeaker);

        // hbox for controls under image
        HBox hbControls = new HBox();
        hbControls.setAlignment(Pos.CENTER);
        hbControls.setPadding(new Insets(16, 16, 16, 16));
        hbControls.setSpacing(16);

        // vboxes for frequency pickers and their labels
        VBox vbFrequency1 = new VBox();
        vbFrequency1.getChildren().add(lblFrequency1);
        vbFrequency1.getChildren().add(cbFrequency1Picker);
        VBox vbFrequency2 = new VBox();
        vbFrequency2.getChildren().add(lblFrequency2);
        vbFrequency2.getChildren().add(cbFrequency2Picker);

        // combine frequency pickers and add to overall layout
        hbControls.getChildren().add(vbFrequency1);
        hbControls.getChildren().add(vbFrequency2);
        this.getChildren().add(hbControls);

        // add playback toggle
        this.getChildren().add(btnStart);
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

        SoundPlayer player = new SoundPlayer();
        cbFrequency1Picker = initCB();
        cbFrequency2Picker = initCB();
        cbFrequency1Picker.getSelectionModel().selectedItemProperty().addListener(
                (options, oldItem, newItem) -> {
                    // remove A's selected item from B (no duplicate frequencies)
                    cbFrequency2Picker.getItems().remove(newItem);
                    // add A's previously selected item to B
                    if(oldItem != null) {
                        cbFrequency2Picker.getItems().add(oldItem);
                    }
                    // sort B
                    Collections.sort(cbFrequency2Picker.getItems());
                }
        );

        // TODO: bug with duplicate frequencies
        addListenerToCB(player, cbFrequency1Picker, cbFrequency2Picker);
        addListenerToCB(player, cbFrequency2Picker, cbFrequency1Picker);
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

    private void addListenerToCB(SoundPlayer player, ComboBox<String> cbFrequency1Picker, ComboBox<String> cbFrequency2Picker) {
        cbFrequency1Picker.getSelectionModel().selectedItemProperty().addListener(
                (options, oldItem, newItem) -> {
                    // reset the toggle button and stop playback when new frequency is selected
                    btnStart.setSelected(false);
                    player.stop();
                    // remove 1's selected item from 2 (no duplicate frequencies)
                    cbFrequency2Picker.getItems().remove(newItem);
                    // add 1's previously selected item to 2
                    if(oldItem != null) {
                        cbFrequency2Picker.getItems().add(oldItem);
                    }
                    // sort 2
                    Collections.sort(cbFrequency2Picker.getItems());
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