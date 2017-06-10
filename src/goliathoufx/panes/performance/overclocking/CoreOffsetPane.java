/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliathoufx.InstanceProvider;

public class CoreOffsetPane extends OverclockingPaneTemplate
{
    public CoreOffsetPane()
    {
        super(true);
        
        super.setSpinnerModel(InstanceProvider.getCoreOffsetController().getMinValue(), InstanceProvider.getCoreOffsetController().getMaxVelue(), 0);
        super.setCurrentValueLabel("Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(InstanceProvider.getCoreOffsetController().getCurrentValue());
        super.setCurrentMinValue(String.valueOf(InstanceProvider.getCoreOffsetController().getMinValue()));
        super.setCurrentMaxValue(String.valueOf(InstanceProvider.getCoreOffsetController().getMaxVelue()));
        
        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), InstanceProvider.getCoreOffsetController()));
        super.getResetButton().setOnMouseClicked(new ResetHandler(InstanceProvider.getCoreOffsetController()));
        
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
