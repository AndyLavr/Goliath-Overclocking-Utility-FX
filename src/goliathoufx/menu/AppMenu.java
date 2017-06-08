/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.menu;

import goliathoufx.menu.items.TabsMenu;
import goliathoufx.menu.items.ThemesMenu;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

/**
 *
 * @author ty
 */
public class AppMenu extends MenuBar
{
    private final Menu[] items;
    private final TabsMenu tabMenu;
    
    public AppMenu(TabPane tabPane)
    {
        super();
        super.getStyleClass().add("menubar");
        
        tabMenu = new TabsMenu(tabPane);

        items = new Menu[2];
        items[0] = tabMenu;
        items[1] = new ThemesMenu();
        
        super.getMenus().addAll(items);
    }
}
