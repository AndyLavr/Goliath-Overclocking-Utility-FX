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
    private final Label modeDesc;
    
    public PerformanceModePane(PowerMizerController powerMizer)
    {
        super();
        super.setPadding(new Insets(10,10,10,10));
        super.setSpacing(5);
        
        modes = new ComboBox<>(FXCollections.observableArrayList("Adaptive", "Max Performance", "Driver Controlled(Auto)"));
        modes.getSelectionModel().select(powerMizer.getCurrentValue());
        modes.getSelectionModel().selectedItemProperty().addListener(new ModeHandler(powerMizer));
        
        modeDesc = new Label("Increases performance as needed.");
        
        super.getChildren().addAll(new Label("Performance Mode:"), modes, new Label("Description:"), modeDesc);
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
                    modeDesc.setText("Increases performance as needed.");
                    break;
                    
                case "Max Performance":
                    cont.setValue(1);
                    modeDesc.setText("Forces high GPU clock speeds.");
                    break;
                
                case "Driver Controlled(Auto)":
                    cont.setValue(2);
                    modeDesc.setText("Controlled by the Nvidia graphics driver.");
                    break;
            }
        } 
    }
}
