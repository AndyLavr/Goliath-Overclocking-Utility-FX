/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;
import javafx.scene.control.Spinner;

/**
 *
 * @author ty
 */
public class MemoryOffsetPane extends OverclockingPaneTemplate
{
    private final Spinner<Integer> valueSpin;
    
    public MemoryOffsetPane(GPUController<Integer> memory)
    {
        super(true);
        
        super.setSpinnerModel(memory.getMinValue(), memory.getMaxVelue(), 0);
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(memory.getCurrentValue());
        super.setCurrentMinValue(String.valueOf(memory.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(memory.getMaxVelue()));
        
        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), memory));
        super.getResetButton().setOnMouseClicked(new ResetHandler(memory));
        
        valueSpin = super.getSpinner();
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
