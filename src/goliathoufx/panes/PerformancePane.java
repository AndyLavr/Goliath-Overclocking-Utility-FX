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
import goliathoufx.InstanceProvider;
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

    public PerformancePane(AppTabPane pane)
    {
        super();
        
        overclockingTabPane = new OverclockingTabPane(pane);
        
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

        super.topProperty().set(new PowerMizerPane());
        super.centerProperty().set(overclockingPane);
    }
    public void applyOCAll(CharSequence password)
    {
            InstanceProvider.getCoreOffsetController().setValue(overclockingTabPane.getCoreSpinner().getValue());
            InstanceProvider.getMemoryOffsetController().setValue(overclockingTabPane.getMemorySpinner().getValue());
            InstanceProvider.getVoltageOffsetController().setValue(overclockingTabPane.getVoltageSpinner().getValue()*1000);
            
            if(InstanceProvider.getPowerLimitController().isWorking())
            {
                ((PowerLimitController)InstanceProvider.getPowerLimitController()).setPassword(password);
                InstanceProvider.getPowerLimitController().setValue(overclockingTabPane.getPowerSpinner().getValue());
            }
            
            ConsolePane.addText("\nApplying all Overclocks...");
            ConsolePane.addText(InstanceProvider.getCoreOffsetController().getOutput());
            ConsolePane.addText(InstanceProvider.getMemoryOffsetController().getOutput());
            ConsolePane.addText(InstanceProvider.getVoltageOffsetController().getOutput()); 
            
            if(InstanceProvider.getPowerLimitController().isWorking())
                ConsolePane.addText(InstanceProvider.getPowerLimitController().getOutput());
            
            ConsolePane.addText("\nOverclocks Applied.");
    }
    public void applyPowerLimit(CharSequence password)
    {            
            ((PowerLimitController)InstanceProvider.getPowerLimitController()).setPassword(password);
            InstanceProvider.getPowerLimitController().setValue(overclockingTabPane.getPowerSpinner().getValue());
            
            ConsolePane.addText(InstanceProvider.getPowerLimitController().getOutput());
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
            InstanceProvider.getCoreOffsetController().setValue(0);
            InstanceProvider.getMemoryOffsetController().setValue(0);
            InstanceProvider.getVoltageOffsetController().setValue(0);
            
            ConsolePane.addText("\nResetting Overclocks...");
            ConsolePane.addText(InstanceProvider.getCoreOffsetController().getOutput());
            ConsolePane.addText(InstanceProvider.getMemoryOffsetController().getOutput());
            ConsolePane.addText(InstanceProvider.getVoltageOffsetController().getOutput()); 
            ConsolePane.addText("\nOverclocks reset. Power Limit must be reset manually.");
        }
    }
}
