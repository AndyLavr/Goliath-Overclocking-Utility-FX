package goliathoufx.panes.performance.overclocking;

import goliathoufx.InstanceProvider;

public class MemoryOffsetPane extends OverclockingPaneTemplate
{ 
    public MemoryOffsetPane()
    {
        super(true);
        
        super.setSpinnerModel(InstanceProvider.getMemoryOffsetController().getMinValue(), InstanceProvider.getMemoryOffsetController().getMaxVelue(), 0);
        super.setCurrentValueLabel("Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(InstanceProvider.getMemoryOffsetController().getCurrentValue());
        super.setCurrentMinValue(String.valueOf(InstanceProvider.getMemoryOffsetController().getMinValue()));
        super.setCurrentMaxValue(String.valueOf(InstanceProvider.getMemoryOffsetController().getMaxVelue()));
        
        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), InstanceProvider.getMemoryOffsetController()));
        super.getResetButton().setOnMouseClicked(new ResetHandler(InstanceProvider.getMemoryOffsetController()));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
