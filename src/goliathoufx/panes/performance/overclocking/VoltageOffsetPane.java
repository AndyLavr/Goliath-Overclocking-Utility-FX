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
public class VoltageOffsetPane extends OverclockingPaneTemplate
{
    public VoltageOffsetPane(GPUController<Integer> voltage)
    {
        super();
        
        super.setSpinnerModel(voltage.getMinValue(), voltage.getMaxVelue(), 0);
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(0);
        super.setCurrentMinValue(String.valueOf(voltage.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(voltage.getMaxVelue()));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
