/*
 * The MIT License
 *
 * Copyright 2017 Ty Young.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package goliathoufx.panes.fan;

import goliathoufx.InstanceProvider;
import goliathoufx.panes.performance.powermizer.PerformanceModePane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FanInfoPane extends HBox
{
    private final ComboBox<String> modes;
    private final HBox modeBox, profileBox;
    public FanInfoPane()
    {
        super();
        super.getStyleClass().add("hbox");
        super.setPadding(new Insets(8,8,8,8));
        super.setSpacing(10);
        
        modes = new ComboBox<>(FXCollections.observableArrayList("Auto", "Manual"));
        modes.getSelectionModel().selectedItemProperty().addListener(new ModeHandler());
        modes.getSelectionModel().select(InstanceProvider.getFanModeController().getCurrentValue());
        
        modeBox = new HBox();
        //modeBox.setPadding(new Insets(5,5,5,5));
        modeBox.setSpacing(8);
        modeBox.getChildren().addAll(new Label("Fan Mode:"), modes);
        
        profileBox = new HBox();
        //profileBox.setPadding(new Insets(5,5,5,5));
        profileBox.setSpacing(8);
        profileBox.getChildren().add(new Label("Active Fan profile:"));
        
        super.getChildren().addAll(modeBox, profileBox);
        
    }
    private class ModeHandler implements ChangeListener<String>
    {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
        {
            switch(newValue)
            {
                case "Auto":
                    InstanceProvider.getFanModeController().setValue(0);
                    break;
                    
                case "Manual":
                    InstanceProvider.getFanModeController().setValue(1);
                    break;
            }
        }
        
    }
}
