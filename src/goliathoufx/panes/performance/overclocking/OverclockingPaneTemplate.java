package goliathoufx.panes.performance.overclocking;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class OverclockingPaneTemplate extends GridPane
{
    private final Label currentValueLabel, minValueLabel, maxValueLabel, currentMinValue, currentMaxValue;
    private final Button apply;
    private final Spinner<Integer> currentValueSpinner;
    private Button reset;
    
    public OverclockingPaneTemplate(boolean showReset)
    {
        super();
        super.getStyleClass().add("hbox");
        super.setPadding(new Insets(10,10,10,10));
      
        super.setVgap(5);
        super.setHgap(15);
        
        currentValueLabel = new Label();
        minValueLabel = new Label();
        maxValueLabel = new Label();
        currentValueSpinner = new Spinner<>();
        currentMinValue = new Label();
        currentMaxValue = new Label();
        
        apply = new Button("Apply");
        apply.setPrefWidth(100);
        
        if(showReset)
        {
            reset = new Button("Reset");
            reset.setPrefWidth(100);
        }
        
        currentValueSpinner.setPrefWidth(85);
        
        super.add(currentValueLabel, 0, 0);
        super.add(minValueLabel, 1, 0);
        super.add(maxValueLabel, 2, 0);
        super.add(apply, 3, 0);
        
        super.add(currentValueSpinner, 0, 1);
        super.add(currentMinValue, 1, 1);
        super.add(currentMaxValue, 2, 1);
        
        if(showReset)
            super.add(reset, 3, 1);
    }
    public Spinner<Integer> getSpinner()
    {
        return currentValueSpinner;
    }
    public Button getApplyButton()
    {
        return apply;
    }
    public Button getResetButton()
    {
        return reset;
    }
    public void setCurrentSpinnerValue(int value)
    {
        currentValueSpinner.getValueFactory().setValue(value);
    }
    public void setSpinnerModel(int min, int max, int defaultInt)
    {
        currentValueSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, 0));
    }
    public void setCurrentValueLabel(String str)
    {
        currentValueLabel.setText(str);
    }
    public void setMinValueLabel(String str)
    {
        minValueLabel.setText(str);
    }
    public void setMaxValueLabel(String str)
    {
        maxValueLabel.setText(str);
    }
    public void setCurrentMinValue(String str)
    {
        currentMinValue.setText(str);
    }
    public void setCurrentMaxValue(String str)
    {
        currentMaxValue.setText(str);
    }
}
