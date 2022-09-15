package beat_frequency;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;

public class BeatFrequencyPane extends GridPane {
    private ComboBox<String> cbFrequencyAPicker;
    private ComboBox<String> cbFrequencyBPicker;

    private Label lblSound;
    private ToggleButton btnStart;
    private ToggleButton btnStop;
    private ToggleGroup toggle;

    public  BeatFrequencyPane(){
        initComponents();
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0, 10, 0, 10));

        // add widgets to GridPane
        add(cbFrequencyAPicker, 0, 0);
        add(lblSound, 1, 0);
        add(cbFrequencyBPicker, 2, 0);
        add(btnStart, 0, 1);
        add(btnStop, 2, 1);
    }

    private void initComponents(){
        SoundPlayer player = new SoundPlayer();
        cbFrequencyAPicker = initCB();
        cbFrequencyBPicker = initCB();
        cbFrequencyAPicker.getSelectionModel().selectedItemProperty().addListener(
                (options, oldItem, newItem) -> {
                    // remove A's selected item from B (no duplicate frequencies)
                    cbFrequencyBPicker.getItems().remove(newItem);
                    // add A's previously selected item to B
                    if(oldItem != null) {
                        cbFrequencyBPicker.getItems().add(oldItem);
                    }
                    // sort B
                    Collections.sort(cbFrequencyBPicker.getItems());
                }
        );
        // select index 0 in both cbs
        cbFrequencyAPicker.getSelectionModel().select(0);
        cbFrequencyBPicker.getSelectionModel().select(0);

        lblSound = new Label("\uD83D\uDD0A");

        toggle = new ToggleGroup();

        btnStart = new ToggleButton("Start");
        btnStart.setToggleGroup(toggle);

        btnStop = new ToggleButton("Stop");
        btnStop.setToggleGroup(toggle);

        toggle.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) ->{
                    if(newToggle == btnStart){
                        // start playback
                        double frequencyA = createFrequencyFromCB(cbFrequencyAPicker);
                        double frequencyB = createFrequencyFromCB(cbFrequencyBPicker);
                        player.start(frequencyA, frequencyB);
                    }
                    else{
                        // stop playback
                        player.stop();
                    }
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