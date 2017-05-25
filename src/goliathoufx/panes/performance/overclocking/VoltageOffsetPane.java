/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;

/**
 *
 * @author ty
 */
public class VoltageOffsetPane extends OverclockingPaneTemplate
{
    public VoltageOffsetPane(GPUController<Integer> voltage)
    {
        super(true);
        
        super.setSpinnerModel(voltage.getMinValue(), voltage.getMaxVelue(), 0);
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(voltage.getCurrentValue()/1000);
        super.setCurrentMinValue(String.valueOf(voltage.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(voltage.getMaxVelue()));

        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), voltage));
        super.getResetButton().setOnMouseClicked(new ResetHandler(voltage));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
