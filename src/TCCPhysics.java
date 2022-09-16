import beat_frequency.BeatFrequencyPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wave_addition.WaveAdditionPane;

public class TCCPhysics extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TCC Physics Simulations");
        // initialize simulations in their tabs
        TabPane tabPane = new TabPane();
        Tab beatFrequency = initializeTab(new BeatFrequencyPane(), "Beat Frequency");
        Tab waveAddition = initializeTab(new WaveAdditionPane(), "Wave Addition");
        tabPane.getTabs().addAll(beatFrequency, waveAddition);

        // add tabPane to application and display
        Scene scene = new Scene(tabPane, 900, 800);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab initializeTab(Pane pane, String name){
        Tab t = new Tab(name);
        t.setContent(pane);
        t.setClosable(false);
        return t;
    }

    // TODO: tabs for other simulations
}
