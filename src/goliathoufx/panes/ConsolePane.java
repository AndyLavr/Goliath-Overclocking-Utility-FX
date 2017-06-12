package goliathoufx.panes;

import goliathoufx.AppSettings;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ConsolePane extends Pane
{
    public static ListView<String> STATIC_LIST;
    public static ArrayList<String> OUTPUT = new ArrayList<>();
    private final ListView<String> list;
    
    
    public ConsolePane()
    {
        super();
        
        OUTPUT.add("Goliath Overclocking Utility V" + AppSettings.getVersion());
        OUTPUT.add("Theme: " + AppSettings.getTheme());
        OUTPUT.add("Show Extra Attribute Information: " + AppSettings.getShowExtraAttributeInfo());
        OUTPUT.add("Safety Enabled: " + AppSettings.getSafety());
        
        list = new ListView<>(FXCollections.observableArrayList(OUTPUT));
        list.setEditable(false);
        list.setPrefHeight(283);
        list.setPrefWidth(750);
        list.setPlaceholder(new Label("No Output"));
        list.setItems(FXCollections.observableArrayList(OUTPUT));
        
        super.getChildren().add(list);
    }
    public ListView<String> getList()
    {
        return list;
    }
    public static void init(ListView<String> listView)
    {
        STATIC_LIST = listView;
    }
    public static void addText(String text)
    {
        OUTPUT.add(text);
        STATIC_LIST.setItems(FXCollections.observableArrayList(OUTPUT));
    }
    public static void addText(ArrayList<String> text)
    {
        for(int i = 0; i < text.size(); i++)
            OUTPUT.add(text.get(i));
        
        STATIC_LIST.setItems(FXCollections.observableArrayList(OUTPUT));
    }
}