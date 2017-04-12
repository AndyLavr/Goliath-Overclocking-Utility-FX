/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.fan;

import goliath.ou.fan.FanProfile;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 *
 * @author ty
 */
public class FanProfileTempPane extends VBox
{
    private final TableView<FanProfile> tempTable;
    private final TableColumn<FanProfile, String> tempCol;
    private final TableColumn<FanProfile, Integer> speedCol;
    private final Button saveButton, deleteButton;
    
    public FanProfileTempPane()
    {
        super();
        
        tempCol = new TableColumn<>("Tempature(C)");
        speedCol = new TableColumn<>("Speed(%)");
        
        tempTable = new TableView();
        tempTable.setPrefWidth(225);
        tempTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tempTable.getColumns().addAll(tempCol, speedCol);
        
        saveButton = new Button("Save");
        deleteButton = new Button("Delete");
        
        deleteButton.setPrefWidth(225);
        saveButton.setPrefWidth(225);
        
        super.getChildren().addAll(tempTable, saveButton, deleteButton);
                
    }
}
