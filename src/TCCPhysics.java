import beat_frequency.BeatFrequencyPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TCCPhysics extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TCC Physics Simulations");
        // initialize simulations in their tabs
        TabPane tabPane = new TabPane();
        initializeBeatFrequency(tabPane);
        initializeWaveAddition(tabPane);
        // add tabPane to application and display
        Scene scene = new Scene(tabPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeBeatFrequency(TabPane tabPane){
        Tab t = new Tab("Beat Frequency");
        BeatFrequencyPane  beatFrequencyPane = new BeatFrequencyPane();
        t.setContent(beatFrequencyPane);
        tabPane.getTabs().add(t);
    }

    private void initializeWaveAddition(TabPane tabPane){
        Tab t = new Tab("Wave Addition");
        Label label = new Label("Hi, I'm the Wave Addition tab!");
        t.setContent(label);
        tabPane.getTabs().add(t);
    }

    // TODO: other tabs for other simulations
}
