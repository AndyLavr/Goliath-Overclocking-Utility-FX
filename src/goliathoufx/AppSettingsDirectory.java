/*
 * The MIT License
 *
 * Copyright 2017 ty.
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
package goliathoufx;

import goliath.ou.performance.PerfLevelParser;
import goliath.ou.performance.PerformanceLevel;
import goliath.ou.utility.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ty
 */
public class AppSettingsDirectory extends File
{
    private final File appConfig;
    private final File fanProfilesDir;
    private final File defaultFanProfile;
    private final File gpuInfo;
    
    public AppSettingsDirectory(String dir) throws IOException
    {
        super(dir);
        
        if(!super.exists())
        {
            this.createAppDirectory();
            this.createDefaultConfig();
            this.createFanProfileDirectory();
            this.createDefaultFanProfile();
        }
        
        appConfig = new File(super.getAbsolutePath() + "/app.csv");
        
        if(appConfig == null)
            this.createDefaultConfig();
        
        gpuInfo = new File(super.getAbsolutePath() + "/gpuinfo.csv");
        
        if(!gpuInfo.exists())
            this.createGPUInfo();
        
        fanProfilesDir = new File(super.getAbsolutePath() + "/Fan Profiles");
        
        if(!fanProfilesDir.exists())
            this.createFanProfileDirectory();
        
        defaultFanProfile = new File(fanProfilesDir.getAbsolutePath() + "/default.csv");
        
        if(!defaultFanProfile.exists())
            this.createDefaultFanProfile();
    }
    
    private void createAppDirectory() throws IOException
    {
        super.createNewFile();
    }
    private void createFanProfileDirectory()
    {
        fanProfilesDir.mkdir();
    }
    private void createDefaultConfig() throws IOException
    {
        CsvWriter writer;
        
        appConfig.createNewFile();

        writer = new CsvWriter(appConfig.getAbsoluteFile());
            
        writer.addKeyValue("version", "1.0");
        writer.addKeyValue("theme", "default");
        writer.addKeyValue("safety", "true");
        writer.addKeyValue("show_extra_attribute_data", "false");
    }
    private void createDefaultFanProfile() throws IOException
    {
        CsvWriter writer;
        
        defaultFanProfile.createNewFile();
            
        writer = new CsvWriter(defaultFanProfile);
            
        writer.addKeyValue("display_name", "Default");
        writer.addKeyValue("update_speed", "1000");
        writer.addKeyValue("smooth", "true");
                
        writer.addKeyValue("node", "15");
        writer.addKeyValue("node", "20");
        writer.addKeyValue("node", "25");
        writer.addKeyValue("node", "30");
        writer.addKeyValue("node", "35");
        writer.addKeyValue("node", "40");
        writer.addKeyValue("node", "45");
        writer.addKeyValue("node", "50");
        writer.addKeyValue("node", "55");
        writer.addKeyValue("node", "60");
    }

    private void createGPUInfo() throws IOException
    {
        CsvWriter writer;
        PerfLevelParser parser = new PerfLevelParser();
        ArrayList<PerformanceLevel> perfs = new ArrayList<>();
        
        gpuInfo.createNewFile();
        
        perfs = parser.beginParse();
        
        writer = new CsvWriter(gpuInfo);
    }
    public File getAppConfig()
    {
        return appConfig;
    }
    public File getFanProfileDirectory()
    {
        return fanProfilesDir;
    }
}
