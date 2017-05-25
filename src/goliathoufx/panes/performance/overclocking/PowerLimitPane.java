package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;
import goliathoufx.panes.AppTabPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ty
 */
public class PowerLimitPane extends OverclockingPaneTemplate
{
    public PowerLimitPane(GPUController<Integer> power, AppTabPane pane)
    {
        super(false);
        
        super.setSpinnerModel(power.getMinValue(), power.getMaxVelue(), power.getMaxVelue());
        super.setCurrentValueLabel("New Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(power.getMaxVelue());
        super.setCurrentMinValue(String.valueOf(power.getMinValue()));
        super.setCurrentMaxValue(String.valueOf(power.getMaxVelue()));
        
        super.getApplyButton().setOnMouseClicked(new PowerApplyHandler(pane));
    }
    public Integer getSpinnerValue()
    {
        return super.getSpinner().getValue();
    }
    private class PowerApplyHandler implements EventHandler<MouseEvent>
    {
        private final AppTabPane appTabs;
        
        public PowerApplyHandler(AppTabPane pane)
        {
            appTabs = pane;
        }
        @Override
        public void handle(MouseEvent event)
        {
            AppTabPane.POWER_ONLY = true;
            appTabs.promptPassword();
            AppTabPane.POWER_ONLY = false;  
        }
        
    }
}
