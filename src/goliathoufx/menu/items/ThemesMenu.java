/*
 * The MIT License
 *
 * Copyright 2017 Ty Young.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package goliathoufx.menu.items;

import com.sun.javafx.css.StyleManager;
import goliathoufx.GoliathOUFX;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ThemesMenu extends Menu
{
    private final ArrayList<MenuItem> items;
    
    public ThemesMenu()
    {
        super("Themes");
        
        MenuHandler handler = new MenuHandler();
        
        items = new ArrayList<>();
        
        items.add(new MenuItem("Goliath Numix"));
        items.add(new MenuItem("Goliath Arc"));
        
        for(int i = 0; i < items.size(); i++)
            items.get(i).setOnAction(handler);
        
        super.getItems().addAll(items);
    }
    
    private class MenuHandler implements EventHandler
    {
    
        @Override
        public void handle(Event event)
        {
            GoliathOUFX.scene.getStylesheets().remove(0);
            
            switch(((MenuItem)event.getSource()).getText())
            {
                case "Goliath Numix":
                    GoliathOUFX.scene.getStylesheets().add("skins/Goliath-Numix.css");
                    break;
                    
                case "Goliath Arc":
                    GoliathOUFX.scene.getStylesheets().add("skins/Goliath-Arc.css");
                    break;   
            }
        } 
    }
}
