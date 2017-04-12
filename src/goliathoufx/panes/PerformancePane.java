/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes;

import goliath.ou.api.GPUController;
import goliath.ou.attribute.Attribute;
import goliath.ou.controller.CoreController;
import goliath.ou.controller.MemoryController;
import goliath.ou.controller.PowerController;
import goliath.ou.controller.VoltageController;
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
    private PowerController power;

    public PerformancePane(ArrayList<PerformanceLevel> perfLevelList, ArrayList<Attribute> attributes, AppTabPane pane)
    {
        super();
        controllers = new GPUController[4];
        perfLevels = perfLevelList;

        for(int i = 0; i < attributes.size(); i++)
        {
            switch(attributes.get(i).displayNameProperty().getValue())
            {
                case "Graphics Clock Offset":
                    core = new CoreController(attributes.get(i));
                    break;
                    
                case "Voltage Offset":
                    voltage = new VoltageController(attributes.get(i));
                    break;
                    
                case "Memory Transfer Rate Offset":
                    memory = new MemoryController(attributes.get(i));
                    break;   
            }
        }
        power = new PowerController();
        
        controllers[0] = core;
        controllers[1] = memory;
        controllers[2] = voltage;
        controllers[3] = power;
        
        overclockingTabPane = new OverclockingTabPane(controllers);
        
        apply = new Button("Apply");
        apply.setMinWidth(100);
        apply.setMinHeight(52);

        cancel = new Button("Reset");
        cancel.setMinWidth(100);
        cancel.setMaxHeight(51);

        buttonPane = new BorderPane();
        buttonPane.topProperty().set(apply);
        buttonPane.centerProperty().set(cancel);

        overclockingPane = new BorderPane();
        overclockingPane.leftProperty().set(overclockingTabPane);
        overclockingPane.rightProperty().set(buttonPane);

        apply.setOnMouseClicked(new ApplyButtonHandler(pane));
        cancel.setOnMouseClicked(new cancelButtonHandler());

        super.topProperty().set(new PowerMizerPane(perfLevels));
        super.centerProperty().set(overclockingPane);
    }
    public void applyOC(CharSequence password)
    {
            core.setValue(overclockingTabPane.getCoreSpinner().getValue());
            memory.setValue(overclockingTabPane.getMemorySpinner().getValue());
            voltage.setValue(overclockingTabPane.getVoltageSpinner().getValue()*1000);
            
            power.setPassword(password);
            power.setValue(overclockingTabPane.getPowerSpinner().getValue());
            
            ConsolePane.addText("\nApplying Overclocks...");
            ConsolePane.addText(core.getOutput());
            ConsolePane.addText(memory.getOutput());
            ConsolePane.addText(voltage.getOutput()); 
            ConsolePane.addText(power.getOutput());
            ConsolePane.addText("\nOverclocks Applied!");
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
        }
    }
}
