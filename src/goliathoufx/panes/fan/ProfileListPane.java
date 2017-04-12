/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.fan;

import goliath.ou.fan.FanProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ty
 */
public class ProfileListPane extends HBox
{
    private final ListView<FanProfile> profileList;
    private final VBox leftPane;
    private final Button applyButton, openButton, deleteButton, renameButton, newButton;
    
    public ProfileListPane()
    {
        super();
        
        leftPane = new VBox();
        
        profileList = new ListView();
        profileList.setEditable(false);
        profileList.setMinWidth(150);
        
        profileList.setPlaceholder(new Label("No Profiles"));
        
        applyButton = new Button("Apply");
        applyButton.setMinWidth(100);
        applyButton.setMinHeight(55);
        
        openButton = new Button("Open");
        openButton.setMinWidth(100);
        openButton.setMinHeight(55);
        
        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(100);
        deleteButton.setMinHeight(55);
        
        renameButton = new Button("Rename");
        renameButton.setMinWidth(100);
        renameButton.setMinHeight(55);
        
        newButton = new Button("New");
        newButton.setMinWidth(100);
        newButton.setMinHeight(54);
        
        leftPane.getChildren().addAll(applyButton, openButton, renameButton, deleteButton, newButton);
        
        super.getChildren().addAll(leftPane, profileList);
    }
    
    
}
