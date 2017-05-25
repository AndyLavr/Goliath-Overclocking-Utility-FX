package goliathoufx.panes;

import goliath.ou.attribute.Attribute;
import goliath.ou.performance.PerformanceLevel;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author ty
 */
public class AppTabPane extends TabPane
{   
    public static boolean POWER_ONLY = false;
    
    private final TabHandler handler;
    private final Tab[] tabs;
    private final PasswordPane passwordPane;
    private final PerformancePane performancePane;
    
    public static boolean BLOCK_TAB_CREATION;
    
    public AppTabPane(ArrayList<Attribute> attributes, ArrayList<PerformanceLevel> perfLevels, Attribute[] attrs)
    {
        super();
        super.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
        
        ConsolePane pane = new ConsolePane();
        
        BLOCK_TAB_CREATION = false;
        passwordPane = new PasswordPane(this);
        performancePane = new PerformancePane(perfLevels, attributes, this, attrs);
        handler = new TabHandler(this);
        
        tabs = new Tab[4];
        
        tabs[0] = new Tab("Information");
        tabs[0].setContent(new InformationPane(attributes));
        
        tabs[1] = new Tab("PowerMizer & Overclocking");
        tabs[1].setContent(performancePane);
        
        tabs[2] = new Tab("Fan Control");
        tabs[2].setContent(new FanProfilePane(attrs[2], attrs[3], attrs[4]));
        
        tabs[3] = new Tab("App Console");
        tabs[3].setContent(pane);
        
        ConsolePane.initPane(pane);
        
        for(int i = 0; i < tabs.length; i++)
            tabs[i].setOnCloseRequest(handler);
        
        super.getTabs().addAll(tabs);
    }
    public void promptPassword()
    {
        BLOCK_TAB_CREATION = true;
        
        Tab pswPane = new Tab("Root Access Required");
        pswPane.setContent(passwordPane);
        pswPane.setClosable(false);
        
        this.getTabs().removeAll(this.getTabs());
        this.getTabs().add(pswPane);
        this.getSelectionModel().select(pswPane);
    }
    public void cancelPassword()
    {
        BLOCK_TAB_CREATION = false;
        this.getTabs().removeAll(this.getTabs());
        
        for(int i = 0; i < tabs.length; i++)
            this.getTabs().add(tabs[i]);
        
        this.getSelectionModel().select(1);
    }
    public void gotPassword()
    {
        BLOCK_TAB_CREATION = false;
        this.getTabs().removeAll(this.getTabs());
        
        for(int i = 0; i < tabs.length; i++)
            this.getTabs().add(tabs[i]);
        
        this.getSelectionModel().select(1);
        
        if(POWER_ONLY)
            performancePane.applyPowerLimit(passwordPane.getPassword());
        else
            performancePane.applyOCAll(passwordPane.getPassword());
    }
    public Tab getInformationTab()
    {
        return tabs[0];
    }
    public Tab getPowerMizerTab()
    {
        return tabs[1];
    }
    public Tab getFanControlTab()
    {
        return tabs[2];
    }
    
    // Generic EventHandler class for taking care of special tab closing behavior
    private class TabHandler implements EventHandler
    {
        private final TabPane pane;
        
        public TabHandler(TabPane tabPane)
        {
            pane = tabPane;
        }
        @Override
        public void handle(Event event)
        {
            pane.getTabs().remove(((Tab)event.getSource()));
        } 
    }
}
