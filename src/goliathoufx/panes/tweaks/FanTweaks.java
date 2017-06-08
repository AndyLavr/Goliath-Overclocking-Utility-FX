package goliathoufx.panes.tweaks;

import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FanTweaks extends Pane
{
    public FanTweaks()
    {
        super();

        
        super.getChildren().add(new ModeGroup());
    }
    
    private class ModeGroup extends Group
    {
        private final ComboBox<String> modes;
        
        public ModeGroup()
        {
            super();
            
            modes = new ComboBox<>();
            modes.getItems().addAll("test1", "test2");
            
            super.getChildren().addAll(new Label("Performance Mode:"), modes);
        }
    }
}
