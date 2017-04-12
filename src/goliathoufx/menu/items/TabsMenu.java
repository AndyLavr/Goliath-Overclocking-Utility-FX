/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.menu.items;

import goliathoufx.panes.AppTabPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author ty
 */
public class TabsMenu extends Menu
{
    private final MenuHandler handler;
    private final TabPane tabPaneToAddTo;
    private Tab[] tabs;
    
    
    public TabsMenu(TabPane tabPane)
    {
        super("Tabs");
        
        tabPaneToAddTo = tabPane;
        tabs = tabPane.getTabs().toArray(new Tab[tabPane.getTabs().size()]);
        
        handler = new MenuHandler(tabs);
        
        for(int i = 0; i < tabs.length; i++)
        {
            MenuItem tmp = new MenuItem(tabs[i].getText());
            tmp.setOnAction(handler);
            super.getItems().add(tmp);
        }
    }
    
    // Generic MenuHancler for TabsMenu since they all do the same thing.
    private class MenuHandler implements EventHandler
    {   
        public MenuHandler(Tab[] tabArray)
        {
            tabs = tabArray;
        }
        @Override
        public void handle(Event event)
        {
            if(AppTabPane.BLOCK_TAB_CREATION)
                event.consume();
            else
            {
                for(int i = 0; i < tabs.length; i++)
                {
                    if(tabs[i].getText().equals(((MenuItem)event.getSource()).getText()))
                    {
                        if(!tabPaneToAddTo.getTabs().contains(tabs[i]))
                            tabPaneToAddTo.getTabs().add(tabs[i]);
                    }
                }
            }
        }
    }
}
