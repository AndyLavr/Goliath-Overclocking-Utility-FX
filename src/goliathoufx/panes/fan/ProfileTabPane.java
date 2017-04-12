package goliathoufx.panes.fan;

import goliathoufx.panes.fan.template.FanProfileTemplate;
import java.util.ArrayList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author ty
 */
public class ProfileTabPane extends TabPane
{
    private final ArrayList<Tab> tabs;
    
    public ProfileTabPane()
    {
        super();
        
        tabs = new ArrayList<>();
        tabs.add(new Tab("test"));
        
        tabs.get(0).setContent(new FanProfileChartTemplate("Test"));
        
        super.getTabs().addAll(tabs);
    }
}
