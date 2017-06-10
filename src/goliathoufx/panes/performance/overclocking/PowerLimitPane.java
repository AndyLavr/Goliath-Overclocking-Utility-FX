package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;
import goliathoufx.InstanceProvider;
import goliathoufx.panes.AppTabPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ty
 */
public class PowerLimitPane extends OverclockingPaneTemplate
{
    public PowerLimitPane(AppTabPane pane)
    {
        super(false);
        
        super.setSpinnerModel(InstanceProvider.getPowerLimitController().getMinValue(), InstanceProvider.getPowerLimitController().getMaxVelue(), InstanceProvider.getPowerLimitController().getMaxVelue());
        super.setCurrentValueLabel("Offset Value:");
        super.setMinValueLabel("Minimum Value:");
        super.setMaxValueLabel("Maximum Value:");
        super.setCurrentSpinnerValue(InstanceProvider.getPowerLimitController().getMaxVelue());
        super.setCurrentMinValue(String.valueOf(InstanceProvider.getPowerLimitController().getMinValue()));
        super.setCurrentMaxValue(String.valueOf(InstanceProvider.getPowerLimitController().getMaxVelue()));
        
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
