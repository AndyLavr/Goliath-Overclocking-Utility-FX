/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance;

import goliath.ou.controller.PowerMizerController;
import goliath.ou.performance.PerformanceLevel;
import goliathoufx.panes.performance.powermizer.PerformanceModePane;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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
    
    private final PerformanceModePane performancePane;
    
    public PowerMizerPane(ArrayList<PerformanceLevel> perfLevels, PowerMizerController powerMizer)
    {
        super();
        
        table = new TableView<>(FXCollections.observableList(perfLevels));
        table.setMinHeight(125);
        table.setMaxHeight(125);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);
        
        perfLevel = new TableColumn<>("Performance Level");
        perfLevel.setCellValueFactory(new PropertyValueFactory("name"));
        perfLevel.setSortable(false);
        
        minClock = new TableColumn<>("Min Core(MHz)");
        minClock.setCellValueFactory(new PropertyValueFactory("minCore"));
        minClock.setSortable(false);
        
        maxClock = new TableColumn<>("Max Core(MHz)");
        maxClock.setCellValueFactory(new PropertyValueFactory("maxCore"));
        maxClock.setSortable(false);
        
        minMemory = new TableColumn<>("Min Memory(MHz)");
        minMemory.setCellValueFactory(new PropertyValueFactory("minMemory"));
        minMemory.setSortable(false);
        
        maxMemory = new TableColumn<>("Max Memory(MHz)");
        maxMemory.setCellValueFactory(new PropertyValueFactory("maxMemory"));
        maxMemory.setSortable(false);
        
        table.getColumns().addAll(perfLevel, minClock, maxClock, minMemory, maxMemory);
        
        performancePane = new PerformanceModePane(powerMizer);
        
        super.topProperty().set(table);
        super.centerProperty().set(performancePane);
    }
}
