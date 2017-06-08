package goliathoufx.panes.fan;

import goliath.ou.fan.FanProfile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class ProfileContextMenu extends ContextMenu
{
    private ListView<FanProfile> list;
    
    public ProfileContextMenu(ListView<FanProfile> current)
    {
        super();
        
        list = current;
        
        super.getItems().add(new DeleteItem());
  
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
                list.getItems().remove(list.getSelectionModel().getSelectedIndex());
            }
        }
    }
    private class RenameItem extends MenuItem
    {
        public RenameItem()
        {
            super();
            super.setText("Rename");
            super.setOnAction(new DeleteAction());
        }
        
        private class DeleteAction implements EventHandler
        {
            @Override
            public void handle(Event event)
            {
                list.getItems().remove(list.getSelectionModel().getSelectedIndex());
            }
        }
    }
}
