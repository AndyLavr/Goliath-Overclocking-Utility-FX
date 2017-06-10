/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance;

import goliathoufx.InstanceProvider;
import goliathoufx.panes.AppTabPane;
import goliathoufx.panes.performance.overclocking.CoreOffsetPane;
import goliathoufx.panes.performance.overclocking.MemoryOffsetPane;
import goliathoufx.panes.performance.overclocking.PowerLimitPane;
import goliathoufx.panes.performance.overclocking.VoltageOffsetPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author ty
 */
public class OverclockingTabPane extends TabPane
{
    
    private final Tab[] tabs;
    private final CoreOffsetPane corePane;
    private final MemoryOffsetPane memoryPane;
    private final VoltageOffsetPane voltagePane;
    private PowerLimitPane powerPane;
    
    public OverclockingTabPane(AppTabPane pane)
    {
        super();
        
        super.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        super.setMinWidth(650);
        super.setMaxWidth(650);
        
        corePane = new CoreOffsetPane();
        memoryPane = new MemoryOffsetPane();
        voltagePane = new VoltageOffsetPane();
        
        if(InstanceProvider.getPowerLimitController().isWorking())
        {
            powerPane = new PowerLimitPane(pane);
            tabs = new Tab[4];
        }
        else
            tabs = new Tab[3];
        
        tabs[0] = new Tab("Core Offset(MHz)");
        tabs[0].setContent(corePane);
        
        tabs[1] = new Tab("Memory Offset(MHz)");
        tabs[1].setContent(memoryPane);
        
        tabs[2] = new Tab("Voltage Offset(mV)");
        tabs[2].setContent(voltagePane);
                
        if(InstanceProvider.getPowerLimitController().isWorking())
        {
            tabs[3] = new Tab("Power Limit(W)");
            tabs[3].setContent(powerPane);
        }
        
        super.getTabs().addAll(tabs);
    }
    public Spinner<Integer> getCoreSpinner()
    {
        return corePane.getSpinner();
    }
    public Spinner<Integer> getMemorySpinner()
    {
        return memoryPane.getSpinner();
    }
    public Spinner<Integer> getVoltageSpinner()
    {
        return voltagePane.getSpinner();
    }
    public Spinner<Integer> getPowerSpinner()
    {
        return powerPane.getSpinner();
    }
}
