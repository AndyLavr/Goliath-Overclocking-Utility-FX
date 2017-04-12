package goliathoufx;

import java.util.ArrayList;
import java.util.HashMap;

public class AppSettings
{
    private static HashMap<String, String> settings;
    
    public static void loadSettings(ArrayList<String> keys, ArrayList<String> vals)
    {
        settings = new HashMap<>();
        
        for(int i = 0; i < keys.size(); i++)
            settings.put(keys.get(i), vals.get(i));   
    }
    public static String getVersion()
    {
        return settings.get("version");
    }
    public static Boolean getSafety()
    {
        return Boolean.parseBoolean(settings.get("safety"));
    }
    public static String getTheme()
    {
        return settings.get("theme");
    }
    public static Boolean getShowExtraAttributeInfo()
    {
        return Boolean.parseBoolean(settings.get("show_extra_attribute_data"));
    }
}
