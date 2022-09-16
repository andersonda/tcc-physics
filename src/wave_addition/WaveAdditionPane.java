package wave_addition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.Styling;

public class WaveAdditionPane extends VBox {

    private SineWaveSliderPane sineWaveSliderPaneA;
    private SineWaveSliderPane sineWaveSliderPaneB;

    public WaveAdditionPane(){
        initComponents();
        Styling.setDefaultStyling(this);

        HBox hbSliderPanes = new HBox();
        Styling.setDefaultStyling(hbSliderPanes);
        hbSliderPanes.getChildren().add(sineWaveSliderPaneA);
        hbSliderPanes.getChildren().add(sineWaveSliderPaneB);

        this.getChildren().add(hbSliderPanes);
    }

    private void initComponents(){
        sineWaveSliderPaneA = new SineWaveSliderPane("A");
        sineWaveSliderPaneB = new SineWaveSliderPane("B");
    }
}
