/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;

public class CoreOffsetPane extends OverclockingPaneTemplate
{
    public CoreOffsetPane(GPUController<Integer> core)
    {
        super(true);
        
        super.setSpinnerModel(core.getMinValue(), core.getMaxVelue(), 0);
        super.setCurrentValueLabel("Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(core.getCurrentValue());
        super.setCurrentMinValue(String.valueOf(core.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(core.getMaxVelue()));
        
        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), core));
        super.getResetButton().setOnMouseClicked(new ResetHandler(core));
        
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
