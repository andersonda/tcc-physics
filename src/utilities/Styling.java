package utilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Styling {
    public static void setDefaultStyling(VBox vb){
        vb.setPadding(new Insets(16));
        vb.setSpacing(16);
        vb.setAlignment(Pos.CENTER);
    }

    public static void setDefaultStyling(HBox hb){
        hb.setPadding(new Insets(16));
        hb.setSpacing(16);
        hb.setAlignment(Pos.CENTER);
    }
}
