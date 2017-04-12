package goliathoufx.panes.performance.overclocking;

import goliath.ou.api.GPUController;

/**
 *
 * @author ty
 */
public class PowerLimitPane extends OverclockingPaneTemplate
{
    public PowerLimitPane(GPUController<Integer> power)
    {
        super();
        
        super.setSpinnerModel(power.getMinValue(), power.getMaxVelue(), power.getMaxVelue());
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(power.getMaxVelue());
        super.setCurrentMinValue(String.valueOf(power.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(power.getMaxVelue()));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
}
