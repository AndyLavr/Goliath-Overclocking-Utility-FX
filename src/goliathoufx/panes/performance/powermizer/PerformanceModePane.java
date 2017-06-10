package goliathoufx.panes.performance.powermizer;

import goliathoufx.InstanceProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PerformanceModePane extends HBox
{
    private final ComboBox<String> modes;
    
    public PerformanceModePane()
    {
        super();
        super.getStyleClass().add("hbox");
        super.setPadding(new Insets(10,10,10,10));
        super.setSpacing(5);
        
        modes = new ComboBox<>(FXCollections.observableArrayList("Adaptive", "Max Performance", "Driver Controlled(Auto)"));
        modes.getSelectionModel().select(InstanceProvider.getPowerMizerController().getCurrentValue());
        modes.getSelectionModel().selectedItemProperty().addListener(new ModeHandler());
        
        super.getChildren().addAll(new Label("Performance Mode:"), modes);
    }
    private class ModeHandler implements ChangeListener<String>
    {
        @Override
        public void changed(ObservableValue observable, String oldValue, String newValue)
        {
            switch(newValue)
            {
                case "Adaptive":
                    InstanceProvider.getPowerMizerController().setValue(0);
                    break;
                    
                case "Max Performance":
                    InstanceProvider.getPowerMizerController().setValue(1);
                    break;
                
                case "Driver Controlled(Auto)":
                    InstanceProvider.getPowerMizerController().setValue(2);
                    break;
            }
        } 
    }
}
