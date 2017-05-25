/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes;

import goliath.ou.attribute.Attribute;
import goliath.ou.controller.FanModeController;
import goliath.ou.controller.FanSpeedController;
import goliath.ou.fan.FanManager;
import goliath.ou.fan.FanProfile;
import goliath.ou.fan.FanProfileLoader;
import goliath.ou.utility.GoliathThread;
import goliathoufx.GoliathOUFX;
import goliathoufx.panes.fan.FanProfileEditPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ty
 */
public class FanProfilePane extends HBox
{
    private final FanProfileEditPane editPane;
    private final ListView<FanProfile> profileList;
    private final VBox leftPane;
    private final Button applyButton, openButton, deleteButton, renameButton, newButton;
    private final FanManager manager;
    
    public FanProfilePane(Attribute fan, Attribute mode, Attribute temp)
    {
        super();
        
        FanProfileLoader profiles = new FanProfileLoader(GoliathOUFX.APPDIR.getFanProfileDirectory().listFiles());
        profiles.loadProfiles();
        
        editPane = new FanProfileEditPane(profiles.getLoadedProfiles().get(0));
        manager = new FanManager(new FanSpeedController(fan), new FanModeController(mode), temp);
        
        leftPane = new VBox();
        
        profileList = new ListView<>();
        profileList.setEditable(false);
        profileList.setPrefWidth(150);
        profileList.setItems(FXCollections.observableArrayList(profiles.getLoadedProfiles()));
        profileList.getSelectionModel().selectFirst();
        profileList.setOnMouseClicked(new ListHandler());
        
        profileList.setPlaceholder(new Label("No Profiles"));
        
        applyButton = new Button("Apply");
        applyButton.setMinWidth(100);
        applyButton.setMinHeight(55);
        applyButton.setOnMouseClicked(new ApplyButtonHandler(this));
        
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
        
        super.getChildren().addAll(leftPane, profileList, editPane);
    }
    public void updateFan()
    {
        while(true)
        {
            manager.updateFanSpeed();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(FanProfilePane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private class ApplyButtonHandler implements EventHandler<MouseEvent>
    {
        private final FanProfilePane pane;
        private GoliathThread thread;
        
        public ApplyButtonHandler(FanProfilePane fpp)
        {
            pane = fpp;
            try
            {
                thread = new GoliathThread(pane, pane.getClass().getMethod("updateFan"));
            }
            catch (NoSuchMethodException | SecurityException ex)
            {
                Logger.getLogger(FanProfilePane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void handle(MouseEvent event)
        {
            manager.setFanState(FanModeController.MANUALLY_CONTROLLED);
            manager.setActiveProfile(profileList.getSelectionModel().getSelectedItem());
            
            if(!thread.isAlive())
                thread.start();
        }
    }   
    private class ListHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            editPane.setFanProfile(profileList.getSelectionModel().getSelectedItem());
        }
    }
}
