import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class BeatFrequencyApp extends Application {
    private ComboBox<String> cbFrequencyAPicker;
    private ComboBox<String> cbFrequencyBPicker;

    private Label lblSound;
    private ToggleButton btnStart;
    private ToggleButton btnStop;
    private ToggleGroup toggle;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Beat Frequency Prototype");
        initComponents();

        // set up GridPane for widgets
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // add widgets to GridPane
        gridPane.add(cbFrequencyAPicker, 0, 0);
        gridPane.add(lblSound, 1, 0);
        gridPane.add(cbFrequencyBPicker, 2, 0);
        gridPane.add(btnStart, 0, 1);
        gridPane.add(btnStop, 2, 1);

        // set up scene and show
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

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
        for(double frequency : BeatFrequency.FREQUENCIES){
            cb.getItems().add(frequency + "");
        }
        // add all unknown frequency var names to cb
        for(String var : BeatFrequency.VARS){
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
            return BeatFrequency.getVariableFrequency(selection);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}