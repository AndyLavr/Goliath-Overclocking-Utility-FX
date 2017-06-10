package goliathoufx.panes.performance.overclocking;

import goliathoufx.InstanceProvider;

public class VoltageOffsetPane extends OverclockingPaneTemplate
{
    public VoltageOffsetPane()
    {
        super(true);
        
        super.setSpinnerModel(InstanceProvider.getVoltageOffsetController().getMinValue(), InstanceProvider.getVoltageOffsetController().getMaxVelue(), 0);
        super.setCurrentValueLabel("Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(InstanceProvider.getVoltageOffsetController().getCurrentValue()/1000);
        super.setCurrentMinValue(String.valueOf(InstanceProvider.getVoltageOffsetController().getMinValue()));
        super.setCurrentMaxValue(String.valueOf(InstanceProvider.getVoltageOffsetController().getMaxVelue()));

        super.getApplyButton().setOnMouseClicked(new ApplyHandler(super.getSpinner(), InstanceProvider.getVoltageOffsetController()));
        super.getResetButton().setOnMouseClicked(new ResetHandler(InstanceProvider.getVoltageOffsetController()));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
