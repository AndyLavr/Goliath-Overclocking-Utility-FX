package goliathoufx.panes;

import goliathoufx.AppSettings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AboutPane extends VBox
{
    public AboutPane()
    {
        super();
        super.getStyleClass().add("vbox");
        super.setPadding(new Insets(10,10,10,10));
        super.setSpacing(25);
        
        super.getChildren().add(new Label("Goliath Overclocking Utility FX V" + AppSettings.getVersion()));
        
        super.getChildren().add(new Label("An easy to use, Open Source overclocking utility for Nvidia graphics cards in Linux using Java 8 & JavaFX."));
        
        super.getChildren().add(new Label("You can view the source code at:\nhttps://github.com/BlueGoliath/Goliath-Overclocking-Utility-FX"));
        
        super.getChildren().add(new Label("Close and minimize buttons provided by:\nhttps://icons8.com"));
    }
}
