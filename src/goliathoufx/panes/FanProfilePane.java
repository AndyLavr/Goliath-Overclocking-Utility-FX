package goliathoufx.panes;

import goliath.ou.attribute.Attribute;
import goliath.ou.controller.FanModeController;
import goliath.ou.controller.FanSpeedController;
import goliath.ou.fan.FanManager;
import goliath.ou.fan.FanProfile;
import goliath.ou.fan.FanProfileImporter;
import goliath.ou.utility.GoliathThread;
import goliathoufx.GoliathOUFX;
import goliathoufx.InstanceProvider;
import goliathoufx.panes.fan.FanProfileEditPane;
import goliathoufx.panes.fan.ProfileContextMenu;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FanProfilePane extends HBox
{
    private final FanProfileEditPane editPane;
    private final ListView<FanProfile> profileList;
    private final VBox leftPane;
    private final Button applyButton;
    private final FanManager manager;
    private final ArrayList<FanProfile> profiles;
    
    public FanProfilePane()
    {
        super();
        
        FanProfileImporter profilesImporter = new FanProfileImporter();
        
        File[] fanProfiles = InstanceProvider.getAppDir().getFanProfileDirectory().listFiles();
        
        profiles = new ArrayList<>();
        
        for(int i = 0; i < fanProfiles.length; i++)
        {
            profilesImporter.importObject(fanProfiles[i]);
            profiles.add(profilesImporter.getImportedObject());
        }
        
        editPane = new FanProfileEditPane(profiles.get(0));
        manager = new FanManager((FanSpeedController)InstanceProvider.getFanSpeedController(), (FanModeController)InstanceProvider.getFanModeController(), InstanceProvider.getTempAttribute());
        
        leftPane = new VBox();
        
        profileList = new ListView<>();
        profileList.setEditable(false);
        profileList.setPrefWidth(125);
        profileList.setItems(FXCollections.observableArrayList(profiles));
        profileList.getSelectionModel().selectFirst();
        profileList.setOnMouseClicked(new ListHandler());
        profileList.setContextMenu(new ProfileContextMenu(profileList));
        
        profileList.setPlaceholder(new Label("No Profiles"));
        
        applyButton = new Button("Apply");
        applyButton.setPrefWidth(125);
        applyButton.setMinHeight(47);
        applyButton.setOnMouseClicked(new ApplyButtonHandler(this));
        
        leftPane.getChildren().addAll(profileList, applyButton);
        
        super.getChildren().addAll(leftPane, editPane);
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
