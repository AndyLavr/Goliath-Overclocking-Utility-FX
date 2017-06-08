package goliathoufx.panes.performance.powermizer;

import goliath.ou.controller.PowerMizerController;
import goliath.ou.interfaces.GPUController;
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
    
    public PerformanceModePane(PowerMizerController powerMizer)
    {
        super();
        super.getStyleClass().add("hbox");
        super.setPadding(new Insets(10,10,10,10));
        super.setSpacing(5);
        
        modes = new ComboBox<>(FXCollections.observableArrayList("Adaptive", "Max Performance", "Driver Controlled(Auto)"));
        modes.getSelectionModel().select(powerMizer.getCurrentValue());
        modes.getSelectionModel().selectedItemProperty().addListener(new ModeHandler(powerMizer));
        
        super.getChildren().addAll(new Label("Performance Mode:"), modes);
    }
    private class ModeHandler implements ChangeListener<String>
    {
        private final GPUController<Integer> cont;
        
        public ModeHandler(PowerMizerController powerMizer)
        {
            cont = powerMizer;
        }
        
        @Override
        public void changed(ObservableValue observable, String oldValue, String newValue)
        {
            switch(newValue)
            {
                case "Adaptive":
                    cont.setValue(0);
                    break;
                    
                case "Max Performance":
                    cont.setValue(1);
                    break;
                
                case "Driver Controlled(Auto)":
                    cont.setValue(2);
                    break;
            }
        } 
    }
}
