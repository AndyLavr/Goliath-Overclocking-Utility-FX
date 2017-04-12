package goliathoufx.panes.fan.template;

import goliathoufx.panes.fan.FanProfileTempPane;
import javafx.scene.layout.BorderPane;


public class FanProfileTemplate extends BorderPane
{
    private final FanProfileTempPane tempPane;
            
    public FanProfileTemplate()
    {
        super();
        
        tempPane = new FanProfileTempPane();
        
        super.leftProperty().set(tempPane);
    }
}
