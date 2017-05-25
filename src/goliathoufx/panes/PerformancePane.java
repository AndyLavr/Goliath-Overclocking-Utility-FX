/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes;

import goliath.ou.interfaces.GPUController;
import goliath.ou.attribute.Attribute;
import goliath.ou.controller.CoreOffsetController;
import goliath.ou.controller.MemoryOffsetController;
import goliath.ou.controller.PowerLimitController;
import goliath.ou.controller.PowerMizerController;
import goliath.ou.controller.VoltageOffsetController;
import goliath.ou.performance.PerformanceLevel;
import goliathoufx.panes.performance.OverclockingTabPane;
import goliathoufx.panes.performance.PowerMizerPane;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**

 @author ty
 */
public class PerformancePane extends BorderPane
{
    private final BorderPane overclockingPane;
    private final BorderPane buttonPane;
    private final OverclockingTabPane overclockingTabPane;
    private final Button apply, cancel;
    private final ArrayList<PerformanceLevel> perfLevels;
    private final GPUController[] controllers;
    private GPUController<Integer> core, memory, voltage;
    private PowerLimitController power;
    private PowerMizerController powerMizer;

    public PerformancePane(ArrayList<PerformanceLevel> perfLevelList, ArrayList<Attribute> attributes, AppTabPane pane, Attribute[] requiredAttrs)
    {
        super();
        controllers = new GPUController[4];
        perfLevels = perfLevelList;

        core = new CoreOffsetController(requiredAttrs[0]);
        memory = new MemoryOffsetController(requiredAttrs[1]);
        
        for(int i = 0; i < attributes.size(); i++)
        {
            switch(attributes.get(i).cmdNameProperty().getValue())
            {   
                case "GPUOverVoltageOffset":
                    voltage = new VoltageOffsetController(attributes.get(i));
                    break;
                    
                case "GPUPowerMizerMode":
                    powerMizer = new PowerMizerController(attributes.get(i));
                    break;
            }
        }
        power = new PowerLimitController();
        
        controllers[0] = core;
        controllers[1] = memory;
        controllers[2] = voltage;
        controllers[3] = power;
        
        overclockingTabPane = new OverclockingTabPane(controllers, pane);
        
        apply = new Button("Apply All");
        apply.setMinWidth(100);
        apply.setMinHeight(57);

        cancel = new Button("Reset All");
        cancel.setMinWidth(100);
        cancel.setMaxHeight(56);

        buttonPane = new BorderPane();
        buttonPane.topProperty().set(apply);
        buttonPane.centerProperty().set(cancel);

        overclockingPane = new BorderPane();
        overclockingPane.leftProperty().set(overclockingTabPane);
        overclockingPane.rightProperty().set(buttonPane);

        apply.setOnMouseClicked(new ApplyButtonHandler(pane));
        cancel.setOnMouseClicked(new cancelButtonHandler());

        super.topProperty().set(new PowerMizerPane(perfLevels, powerMizer));
        super.centerProperty().set(overclockingPane);
    }
    public void applyOCAll(CharSequence password)
    {
            core.setValue(overclockingTabPane.getCoreSpinner().getValue());
            memory.setValue(overclockingTabPane.getMemorySpinner().getValue());
            voltage.setValue(overclockingTabPane.getVoltageSpinner().getValue()*1000);
            
            power.setPassword(password);
            power.setValue(overclockingTabPane.getPowerSpinner().getValue());
            
            ConsolePane.addText("\nApplying all Overclocks...");
            ConsolePane.addText(core.getOutput());
            ConsolePane.addText(memory.getOutput());
            ConsolePane.addText(voltage.getOutput()); 
            ConsolePane.addText(power.getOutput());
            ConsolePane.addText("\nOverclocks Applied.");
    }
    public void applyPowerLimit(CharSequence password)
    {            
            power.setPassword(password);
            power.setValue(overclockingTabPane.getPowerSpinner().getValue());
            
            ConsolePane.addText(power.getOutput());
    }
    private class ApplyButtonHandler implements EventHandler
    {
        private final AppTabPane tabPane;
        
        public ApplyButtonHandler(AppTabPane pane)
        {
            tabPane = pane;
        }

        @Override
        public void handle(Event event)
        {   
            tabPane.promptPassword();
        }
    }

    private class cancelButtonHandler implements EventHandler
    {
        @Override
        public void handle(Event event)
        {
            core.setValue(0);
            memory.setValue(0);
            voltage.setValue(0);
            
            ConsolePane.addText("\nResetting Overclocks...");
            ConsolePane.addText(core.getOutput());
            ConsolePane.addText(memory.getOutput());
            ConsolePane.addText(voltage.getOutput()); 
            ConsolePane.addText("\nOverclocks reset. Power Limit must be reset manually.");
        }
    }
}
