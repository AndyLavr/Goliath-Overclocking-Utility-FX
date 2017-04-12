/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ty
 */
public class OverclockingPaneTemplate extends GridPane
{
    private final Label currentValueLabel, minValueLabel, maxValueLabel, currentMinValue, currentMaxValue;
    private final Spinner<Integer> currentValueSpinner;
    private Integer initialValue;
    
    public OverclockingPaneTemplate()
    {
        super();
        super.setPadding(new Insets(10,10,10,10));
      
        super.setVgap(5);
        super.setHgap(15);
        
        currentValueLabel = new Label();
        minValueLabel = new Label();
        maxValueLabel = new Label();
        currentValueSpinner = new Spinner<>();
        currentMinValue = new Label();
        currentMaxValue = new Label();
        
        currentValueSpinner.setEditable(true);
        
        currentMinValue.setAlignment(Pos.TOP_RIGHT);
        currentMaxValue.setAlignment(Pos.TOP_RIGHT);
        
        currentValueSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-200, 1000, 0));
        
        super.add(currentValueLabel, 0, 0);
        super.add(minValueLabel, 1, 0);
        super.add(maxValueLabel, 2, 0);
        
        super.add(currentValueSpinner, 0, 1);
        super.add(currentMinValue, 1, 1);
        super.add(currentMaxValue, 2, 1);
        
        currentValueSpinner.setOnKeyTyped(new spinnerValidation());
        
    }
    public Spinner<Integer> getSpinner()
    {
        return currentValueSpinner;
    }
    public void setCurrentSpinnerValue(int value)
    {
        currentValueSpinner.getValueFactory().setValue(value);
    }
    public void setSpinnerModel(int min, int max, int defaultInt)
    {
        initialValue = defaultInt;
        currentValueSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, defaultInt));
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
    
    private class spinnerValidation implements EventHandler<KeyEvent> // unused. Need to fix JavaFX's autistic spinner UI component.
    {
        @Override
        public void handle(KeyEvent event)
        {
            try
            {
                Integer.parseInt(event.getCharacter());
            }
            catch(NumberFormatException e)
            {
                event.consume();
            }
        }
        
    }
}
