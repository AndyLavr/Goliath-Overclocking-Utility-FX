package goliathoufx.panes;

import goliathoufx.AppSettings;
import java.util.ArrayList;
import javafx.scene.control.TextArea;

public class ConsolePane extends TextArea
{
    public static ConsolePane pane;
    private static ArrayList<String> preInitMsgs;
    
    public ConsolePane()
    {
        super();
        super.setWrapText(true);
        
        super.appendText("Goliath Overclocking Utility V" + AppSettings.getVersion());
        super.appendText("\nTheme: " + AppSettings.getTheme());
        super.appendText("\nShow Extra Attribute Information: " + AppSettings.getShowExtraAttributeInfo());
        super.appendText("\nSafety Enabled: " + AppSettings.getSafety());
    }
    public static void initPane(ConsolePane cPane)
    {
        pane = cPane;
    }
    public static void addText(String text)
    {
        pane.appendText(text);
    }
    public static void addText(ArrayList<String> text)
    {
        for(int i = 0; i < text.size(); i++)
            pane.appendText("\n" + text.get(i));
    }
}
