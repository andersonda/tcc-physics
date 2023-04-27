import beat_frequency.BeatFrequencyPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TCCPhysics extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TCC Beat Frequency Simulation");

        // add tabPane to application and display
        Scene scene = new Scene(new BeatFrequencyPane(), 900, 800);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
