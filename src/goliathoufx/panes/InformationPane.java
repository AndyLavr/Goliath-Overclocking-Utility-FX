/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes;

import goliath.ou.attribute.Attribute;
import goliathoufx.AppSettings;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 *
 * @author ty
 */
public class InformationPane extends Pane
{
    private final TableView<Attribute> infoTable;
    private final TableColumn<Attribute, String> displayName;
    private final TableColumn<Attribute, String> displayValue;
    private TableColumn<Attribute, String> cmdName;
    private TableColumn<Attribute, String> cmdValue;
    
    public InformationPane(ArrayList<Attribute> attributes)
    {
        infoTable = new TableView<>(FXCollections.observableArrayList(attributes));
        
        infoTable.setItems(FXCollections.observableArrayList(attributes));
        
        if(AppSettings.getShowExtraAttributeInfo())
        {
            cmdName = new TableColumn<>("Cmd Name");
            cmdName.setCellValueFactory(new PropertyValueFactory("cmdName"));
        
            cmdValue = new TableColumn<>("Cmd Value");
            cmdValue.setCellValueFactory(new PropertyValueFactory("cmdValue"));
        }
        
        displayName = new TableColumn<>("Name");
        displayName.setCellValueFactory(new PropertyValueFactory("displayName"));
        
        displayValue = new TableColumn<>("Value");
        displayValue.setCellValueFactory(new PropertyValueFactory("displayValue"));
        
        if(!AppSettings.getShowExtraAttributeInfo())
            infoTable.getColumns().addAll(displayName, displayValue);
        else
            infoTable.getColumns().addAll(cmdName, displayName, cmdValue, displayValue);
        
        infoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        infoTable.setPrefWidth(750);
        infoTable.setPrefHeight(283);
        infoTable.setEditable(false);
        
        super.getChildren().add(infoTable);
    }    
}
