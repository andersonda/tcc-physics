package wave_addition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WaveAdditionPane extends VBox {

    private SineWaveSliderPane sineWaveSliderPaneA;
    private SineWaveSliderPane sineWaveSliderPaneB;

    public WaveAdditionPane(){
        initComponents();

        this.setAlignment(Pos.CENTER);
        this.setSpacing(16);
        this.setPadding(new Insets(16, 16, 16, 16));

        HBox hbSliderPanes = new HBox();
        hbSliderPanes.setAlignment(Pos.CENTER);
        hbSliderPanes.setSpacing(16);
        hbSliderPanes.setPadding(new Insets(16, 16, 16, 16));
        hbSliderPanes.getChildren().add(sineWaveSliderPaneA);
        hbSliderPanes.getChildren().add(sineWaveSliderPaneB);

        this.getChildren().add(hbSliderPanes);
    }

    private void initComponents(){
        sineWaveSliderPaneA = new SineWaveSliderPane("A");
        sineWaveSliderPaneB = new SineWaveSliderPane("B");
    }
}
