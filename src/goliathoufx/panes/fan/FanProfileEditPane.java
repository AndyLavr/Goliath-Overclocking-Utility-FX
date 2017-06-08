package goliathoufx.panes.fan;

import goliath.ou.fan.FanNode;
import goliath.ou.fan.FanProfile;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FanProfileEditPane extends VBox
{
    private final TableView<FanNode> tempTable;
    private final TableColumn<FanNode, Integer> tempCol;
    private final TableColumn<FanNode, Integer> speedCol;
    private final ContextMenu menu;
    private final HBox tempEditOpts;
    private final Label tempLabel, speedLabel;
    private final Spinner<Integer> tempSpinner, speedSpinner;
    private final Button apply;
    private final HBox tempBox, speedBox;
    private FanProfile profile;
    
    public FanProfileEditPane(FanProfile file)
    {
        super();

        profile = file;
        tempCol = new TableColumn<>("Tempature(C)");
        tempCol.setCellValueFactory(new PropertyValueFactory("temp"));
        tempCol.setSortable(true);
        tempCol.setSortType(TableColumn.SortType.ASCENDING);
        
        speedCol = new TableColumn<>("Speed(%)");
        speedCol.setCellValueFactory(new PropertyValueFactory("speed"));
        
        tempTable = new TableView<>();
        tempTable.setMinWidth(625);
        tempTable.setMaxWidth(625);
        tempTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tempTable.setItems(FXCollections.observableArrayList(profile.getNodes()));
        tempTable.getSortOrder().add(tempCol);
        tempTable.getColumns().addAll(tempCol, speedCol);
        
        menu = new ContextMenu();
        menu.getItems().add(new DeleteItem());
        tempTable.setContextMenu(menu);
        
        tempBox = new HBox();
        tempBox.getStyleClass().add("hbox");
        tempBox.setSpacing(5);
        
        speedBox = new HBox();
        speedBox.getStyleClass().add("hbox");
        speedBox.setSpacing(5);
        
        tempLabel = new Label("Tempature(C):");
        speedLabel = new Label("Speed(%):");
        
        tempSpinner = new Spinner<>();
        tempSpinner.setPrefWidth(100);
        tempSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 5));
        
        speedSpinner = new Spinner<>();
        speedSpinner.setPrefWidth(100);
        speedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 5));
        
        tempBox.getChildren().addAll(tempLabel, tempSpinner);
        speedBox.getChildren().addAll(speedLabel, speedSpinner);
        
        apply = new Button("Apply");
        apply.setPrefWidth(90); 
        apply.setOnMouseClicked(new ApplyButtonHandler());
        
        tempEditOpts = new HBox();
        tempEditOpts.getStyleClass().add("hbox");
        tempEditOpts.setPadding(new Insets(10,10,10,10));
        tempEditOpts.setSpacing(16);
        tempEditOpts.getChildren().addAll(tempBox, speedBox, apply);
        
        super.getChildren().addAll(tempTable, tempEditOpts); 
    }
    private class DeleteItem extends MenuItem
    {
        public DeleteItem()
        {
            super();
            super.setText("Delete");
            super.setOnAction(new DeleteAction());
        }
        
        private class DeleteAction implements EventHandler
        {
            @Override
            public void handle(Event event)
            {
                profile.getNodes().remove(tempTable.getSelectionModel().getSelectedItem());
                tempTable.getItems().remove(tempTable.getSelectionModel().getSelectedItem());
                tempTable.sort();
            }
        }
    }
    private class ApplyButtonHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            FanNode newNode;
            for(int i = 0; i < profile.getNodes().size(); i++)
            {
                if(tempSpinner.getValue().equals(profile.getNodes().get(i).tempProperty().getValue()))
                {
                    tempTable.getItems().remove(profile.getNodes().get(i));
                    profile.getNodes().remove(i);
                    profile.reSortNodes();
                }
            }
            newNode = new FanNode(tempSpinner.getValue(), speedSpinner.getValue());
            profile.addNode(newNode);

            tempTable.getItems().add(newNode);
            tempTable.sort();
            
        }
    }
    public void setFanProfile(FanProfile file)
    {
        profile = file;
        
        tempTable.setItems(FXCollections.observableArrayList(profile.getNodes()));
        tempTable.sort();
    }
}
