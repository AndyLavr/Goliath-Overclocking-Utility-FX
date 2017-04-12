/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ty
 */
public class AppFrame extends BorderPane
{
    private final AppMenu appMenu;
    private final AttributeUpdater updater;
    private final AppTabPane tabPanel;
    private final ArrayList<PerformanceLevel> perfLevels;
    
    public AppFrame(ArrayList<Attribute> attributes)
    {
        super();
        
        perfLevels = new PerfLevelParser().beginParse();
        
        // Do any setup needed for the attributes such as setting the targeted performance level(highest level) or finding specific attributes
        for(int i = 0; i < attributes.size(); i++)
        {
            switch(attributes.get(i).cmdNameProperty().getValue())
            {
                case "GPUGraphicsClockOffset":
                case "GPUMemoryTransferRateOffset":
                    attributes.get(i).appendCmdName("[" + (perfLevels.size()-1) + "]");
                    break;
            }
        }
        
        updater = new AttributeUpdater(attributes);
        updater.updateAll(true);
        
        //TabPanel - has to be set up first before Menu for the "Tabs" menu item.
        tabPanel = new AppTabPane(attributes, perfLevels);
        tabPanel.setMinHeight(super.getMinHeight());
        tabPanel.setMinWidth(super.getMinWidth());
        
        // Menu
        appMenu = new AppMenu(tabPanel);
        appMenu.useSystemMenuBarProperty();
        
        super.setTop(appMenu);
        super.setCenter(tabPanel);
        
        this.initAttributeUpdateThread();
    }
    public final void initAttributeUpdateThread()
    {
        try
        {
            new GoliathThread(this.getClass(), this, this.getClass().getDeclaredMethod("updateAttributes")).start();
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
            updater.updateAll(true);
            
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(AppFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
