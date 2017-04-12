/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.powermizer;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author ty
 */
public class PerformanceModePane extends HBox
{
    public PerformanceModePane()
    {
        super();
        super.setPadding(new Insets(10,10,10,10));
        super.setSpacing(5);
        
        super.getChildren().add(new Label("Performance Mode:"));
    }
}
