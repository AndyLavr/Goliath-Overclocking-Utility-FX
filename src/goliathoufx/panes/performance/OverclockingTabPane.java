/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance;

import goliath.ou.interfaces.GPUController;
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
    private final PowerLimitPane powerPane;
    
    public OverclockingTabPane(GPUController<Integer>[] controllers, AppTabPane pane)
    {
        super();
        
        super.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        super.setMinWidth(650);
        super.setMaxWidth(650);
        
        corePane = new CoreOffsetPane(controllers[0]);
        memoryPane = new MemoryOffsetPane(controllers[1]);
        voltagePane = new VoltageOffsetPane(controllers[2]);
        powerPane = new PowerLimitPane(controllers[3], pane);
        
        tabs = new Tab[4];
        
        tabs[0] = new Tab("Core Offset(MHz)");
        tabs[0].setContent(corePane);
        
        tabs[1] = new Tab("Memory Offset(MHz)");
        tabs[1].setContent(memoryPane);
        
        tabs[2] = new Tab("Voltage Offset(mV)");
        tabs[2].setContent(voltagePane);
                
        tabs[3] = new Tab("Power Limit(W)");
        tabs[3].setContent(powerPane);
        
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
