/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance;

import goliath.ou.attribute.AttributePusher;
import goliath.ou.performance.PerformanceLevel;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author ty
 */
public class PowerMizerPane extends BorderPane
{
    private final TableView<PerformanceLevel> table;
    private final TableColumn<PerformanceLevel, String> perfLevel;
    private final TableColumn<PerformanceLevel, Integer> minClock;
    private final TableColumn<PerformanceLevel, Integer> maxClock;
    private final TableColumn<PerformanceLevel, Integer> minMemory;
    private final TableColumn<PerformanceLevel, Integer> maxMemory;
    private final ComboBox<String> modes;
    
    private final HBox performancePane;
    
    public PowerMizerPane(ArrayList<PerformanceLevel> perfLevels)
    {
        super();
        
        table = new TableView(FXCollections.observableList(perfLevels));
        table.setMinHeight(125);
        table.setMaxHeight(125);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);
        
        perfLevel = new TableColumn<>("Performance Level");
        perfLevel.setCellValueFactory(new PropertyValueFactory("name"));
        
        minClock = new TableColumn<>("Min Core(MHz)");
        minClock.setCellValueFactory(new PropertyValueFactory("minClock"));
        
        maxClock = new TableColumn<>("Max Core(MHz)");
        maxClock.setCellValueFactory(new PropertyValueFactory("maxClock"));
        
        minMemory = new TableColumn<>("Min Memory(MHz)");
        minMemory.setCellValueFactory(new PropertyValueFactory("minMemory"));
        
        maxMemory = new TableColumn<>("Max Memory(MHz)");
        maxMemory.setCellValueFactory(new PropertyValueFactory("maxMemory"));
        
        table.getColumns().addAll(perfLevel, minClock, maxClock, minMemory, maxMemory);
        
        performancePane = new HBox();
        
        performancePane.setPadding(new Insets(10,10,10,10));
        performancePane.setSpacing(5);
        
        performancePane.getChildren().add(new Label("Performance Mode:"));
        
        modes = new ComboBox(FXCollections.observableArrayList("Adaptive", "Max Performance", "Driver Controlled(Auto)"));
        modes.getSelectionModel().select(0);
        modes.getSelectionModel().selectedItemProperty().addListener(new modeHandler());
        performancePane.getChildren().add(modes);
        
        super.topProperty().set(table);
        super.centerProperty().set(performancePane);
    }
    private class modeHandler implements ChangeListener
    {
        private AttributePusher pusher;

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue)
        {
            pusher = new AttributePusher();
            switch((String)newValue)
            {
                case "Adaptive":
                    pusher.pushAttribute("GPUPowerMizerMode", "0");
                    break;
                    
                case "Max Performance":
                    pusher.pushAttribute("GPUPowerMizerMode", "1");
                    break;
                
                case "Driver Controlled(Auto)":
                    pusher.pushAttribute("GPUPowerMizerMode", "2");
                    break;
            }
            pusher.terminate();
        }
        
    }
}
