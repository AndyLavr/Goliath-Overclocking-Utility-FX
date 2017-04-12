/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.api.GPUController;

/**
 *
 * @author ty
 */
public class MemoryOffsetPane extends OverclockingPaneTemplate
{
    public MemoryOffsetPane(GPUController<Integer> memory)
    {
        super();
        
        super.setSpinnerModel(memory.getMinValue(), memory.getMaxVelue(), 0);
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(0);
        super.setCurrentMinValue(String.valueOf(memory.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(memory.getMaxVelue()));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
