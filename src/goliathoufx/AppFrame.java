package goliathoufx;

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributeUpdater;
import goliath.ou.performance.PerfLevelParser;
import goliath.ou.utility.GoliathThread;
import goliathoufx.menu.AppMenu;
import goliathoufx.panes.AppTabPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFrame extends VBox
{
    private final AppMenu appMenu;
    private final AttributeUpdater updater;
    private final AppTabPane tabPanel;
    private final ArrayList<Attribute> attrs, updateableAttrs;
    
    public AppFrame(Stage appStage)
    {
        super();
        
        super.getChildren().add(new TitlePane("Goliath Overclocking Utility V" + AppSettings.getVersion(), appStage));
        
        updateableAttrs = new ArrayList<>();
        updater = new AttributeUpdater();
        updater.setUpdateDelay(350);
        
        attrs = InstanceProvider.getAttributes();
        
        for(int i = 0; i < attrs.size(); i++)
        {
            if(attrs.get(i).getShouldUpdate())
                updateableAttrs.add(attrs.get(i));
        }
        
        //TabPanel - has to be set up first before Menu for the "Tabs" menu item.
        tabPanel = new AppTabPane();
        tabPanel.setMinHeight(super.getMinHeight());
        tabPanel.setMinWidth(super.getMinWidth());
        
        // Menu
        appMenu = new AppMenu(tabPanel);
        appMenu.useSystemMenuBarProperty();
        
        super.getChildren().addAll(appMenu, tabPanel);
        
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