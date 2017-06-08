package goliathoufx;

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributeUpdater;
import goliath.ou.performance.PerfLevelParser;
import goliath.ou.performance.PerformanceLevel;
import goliath.ou.utility.GoliathThread;
import goliathoufx.menu.AppMenu;
import goliathoufx.panes.AppTabPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFrame extends VBox
{
    private final AppMenu appMenu;
    private final AttributeUpdater updater;
    private final AppTabPane tabPanel;
    private final ArrayList<PerformanceLevel> perfLevels;
    private final ArrayList<Attribute> attrs, updateableAttrs;
    
    public AppFrame(ArrayList<Attribute> attributes, Stage appStage)
    {
        super();
        
        super.getChildren().add(new TitlePane("Goliath Overclocking Utility V" + AppSettings.getVersion(), appStage));
        
        Attribute[] requiredAttrs = new Attribute[5];
        
        updateableAttrs = new ArrayList<>();
        
        perfLevels = new PerfLevelParser().beginParse();
        attrs = attributes;
        
        for(int i = 0; i < attrs.size(); i++)
        {
            if(attrs.get(i).getShouldUpdate())
                updateableAttrs.add(attrs.get(i));
        }

        // Do any setup needed for the attributes such as setting the targeted performance level(highest level) or finding specific attributes
        for(int i = 0; i < attributes.size(); i++)
        {
            switch(attributes.get(i).cmdNameProperty().getValue())
            {
                case "GPUGraphicsClockOffset":
                    requiredAttrs[0] = attributes.get(i);
                    attributes.get(i).appendCmdName("[" + (perfLevels.size()-1) + "]");
                    break;
                    
                case "GPUMemoryTransferRateOffset":
                    requiredAttrs[1] = attributes.get(i);
                    attributes.get(i).appendCmdName("[" + (perfLevels.size()-1) + "]");
                    break;
                    
                case "GPUTargetFanSpeed":
                    requiredAttrs[2] = attributes.get(i);
                    break;
                    
                case "GPUFanControlState":
                    requiredAttrs[3] = attributes.get(i);
                    break;
                    
                case "GPUCoreTemp":
                    requiredAttrs[4] = attributes.get(i);
                    break;
            }
        }
        
        updater = new AttributeUpdater();
        updater.setUpdateDelay(350);
        
        //TabPanel - has to be set up first before Menu for the "Tabs" menu item.
        tabPanel = new AppTabPane(attributes, perfLevels, requiredAttrs);
        tabPanel.setMinHeight(super.getMinHeight());
        tabPanel.setMinWidth(super.getMinWidth());
        
        // Menu
        appMenu = new AppMenu(tabPanel);
        appMenu.useSystemMenuBarProperty();
        
        super.getChildren().addAll(appMenu, tabPanel);
        updater.updateAll(attrs, false);
        
        this.initAttributeUpdateThread();
    }
    public final void initAttributeUpdateThread()
    {
        try
        {
            new GoliathThread(this, this.getClass().getDeclaredMethod("updateAttributes")).start();
        }
        catch (NoSuchMethodException | SecurityException ex)
        {
            Logger.getLogger(AppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public final void updateAttributes()
    {
        while(true)
        {
            updater.updateAll(updateableAttrs, true);
        }
    }
}