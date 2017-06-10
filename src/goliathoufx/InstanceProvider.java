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
package goliathoufx;

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributeExporter;
import goliath.ou.attribute.AttributeImporter;
import goliath.ou.attribute.AttributeUpdater;
import goliath.ou.controller.CoreOffsetController;
import goliath.ou.controller.FanModeController;
import goliath.ou.controller.FanSpeedController;
import goliath.ou.controller.MemoryOffsetController;
import goliath.ou.controller.PowerLimitController;
import goliath.ou.controller.PowerMizerController;
import goliath.ou.controller.VoltageOffsetController;
import goliath.ou.interfaces.GPUController;
import goliath.ou.performance.PerfLevelParser;
import goliath.ou.performance.PerformanceLevel;
import goliath.ou.utility.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstanceProvider
{
    private static final ArrayList<Attribute> ATTRIBUTES = new ArrayList<>();
    private static final AppSettingsDirectory APPDIR = new AppSettingsDirectory(System.getProperty("user.home") + "/GoliathOU");
    private static Attribute core, memory, voltage, fanSpeed, fanMode, temp, powerMizer;
    private static GPUController<Integer>  coreController, memoryController, voltageController, powerLimitController, fanSpeedController, fanModeController, powerMizerController; 
    private static ArrayList<PerformanceLevel> perfLevels;
    
    public static void init()
    {
        // SINGLETON
        perfLevels = new PerfLevelParser().beginParse();
        initAttributes(APPDIR.getAttributesDirectory());
        CsvReader settingsReader;
        try
        {
            settingsReader = new CsvReader(APPDIR.getAppConfig());
            AppSettings.loadSettings(settingsReader.getKeys(), settingsReader.getValues());
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(InstanceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void initAttributes(File attrDir)
    {
        AttributeUpdater updater = new AttributeUpdater();
        ArrayList<Attribute> newAttributes = new ArrayList<>();
        File[] files;
        AttributeImporter importer = new AttributeImporter();
        AttributeExporter exporter = new AttributeExporter(attrDir);

        if (attrDir.listFiles().length == 0) // create and export
        {
            newAttributes.add(new Attribute("GPUCurrentCoreVoltage", "Current Voltage", "mV", true, true));
            newAttributes.add(new Attribute("NvidiaDriverVersion", "Driver Version", null, true, false));
            newAttributes.add(new Attribute("RefreshRate", "Display Refresh Rate", null, true, false));
            newAttributes.add(new Attribute("GpuUUID", "GPU ID", null, true, false));
            newAttributes.add(new Attribute("CUDACores", "GPU CUDA Cores", null, true, false));
            newAttributes.add(new Attribute("GPUCurrentClockFreqs", "GPU and Memory clocks", "MHz", true, true));
            newAttributes.add(new Attribute("TotalDedicatedGPUMemory", "Dedicated GPU Memory", "MB", true, false));
            newAttributes.add(new Attribute("UsedDedicatedGPUMemory", "Used GPU Memory", "MB", true, true));
            newAttributes.add(new Attribute("GPUAdaptiveClockState", "Adaptive Clocking", null, true, false));
            newAttributes.add(new Attribute("PCIEGen", "PCIE Gen", null, true, true));
            newAttributes.add(new Attribute("PCIECurrentLinkWidth", "PCIE Bus Width", "x", true, true));
            newAttributes.add(new Attribute("PCIECurrentLinkSpeed", "PCIE Bus Speed", "GT/s", true, true));
            newAttributes.add(new Attribute("GPUPowerMizerMode", "GPU Performance Mode", null, true, true));
            newAttributes.add(new Attribute("VideoDecoderUtilization", "Video Decoder Utililzation", "%", true, true));
            newAttributes.add(new Attribute("VideoEncoderUtilization", "Video Encoder Utililzation", "%", true, true));
            newAttributes.add(new Attribute("GPUCurrentPerfLevel", "Performance Level", null, true, true));
            newAttributes.add(new Attribute("GPUOverVoltageOffset", "Voltage Offset", "mV", true, true));
            newAttributes.add(new Attribute("GPUMemoryTransferRateOffset", "Memory Transfer Rate Offset", "MHz", true, true));
            newAttributes.add(new Attribute("GPUGraphicsClockOffset", "Graphics Clock Offset", "MHz", true, true));
            newAttributes.add(new Attribute("GPUTargetFanSpeed", "Target Fan Speed", "%", true, true));
            newAttributes.add(new Attribute("GPUCurrentFanSpeedRPM", "Current Fan Speed", "RPM", true, true));
            newAttributes.add(new Attribute("GPUFanControlState", "Current Fan Mode", null, false, true));
            newAttributes.add(new Attribute("GPUCoreTemp", "GPU Core Tempature", "C", true, true));

            for (int i = 0; i < newAttributes.size(); i++)
                exporter.exportObject(newAttributes.get(i));
        }
        
        files = attrDir.listFiles();

        for(int i = 0; i < files.length; i++)
        {
            importer.importObject(files[i]);
            ATTRIBUTES.add(importer.getImportedObject());
        }
        
        for(int i = 0; i < ATTRIBUTES.size(); i++)
        {
            switch(ATTRIBUTES.get(i).cmdNameProperty().getValue())
            {
                case "GPUGraphicsClockOffset":
                    ATTRIBUTES.get(i).appendCmdName("[" + (perfLevels.size()-1) + "]");
                    core = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUMemoryTransferRateOffset":
                    ATTRIBUTES.get(i).appendCmdName("[" + (perfLevels.size()-1) + "]");
                    memory = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUOverVoltageOffset":
                    voltage = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUTargetFanSpeed":
                    fanSpeed = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUFanControlState":
                    fanMode = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUCoreTemp":
                    temp = ATTRIBUTES.get(i);
                    break;
                    
                case "GPUPowerMizerMode":
                    powerMizer = ATTRIBUTES.get(i);
            }
        }

        updater.updateAll(ATTRIBUTES, false);
        
        coreController = new CoreOffsetController(core);
        memoryController = new MemoryOffsetController(memory);
        voltageController = new VoltageOffsetController(voltage);
        powerLimitController = new PowerLimitController();
        fanSpeedController = new FanSpeedController(fanSpeed);
        fanModeController = new FanModeController(fanMode);
        powerMizerController = new PowerMizerController(powerMizer);
    }
    
    public static ArrayList<Attribute> getAttributes()
    {
        return ATTRIBUTES;
    }
    public static AppSettingsDirectory getAppDir()
    {
        return APPDIR;
    }
    public static Attribute getCoreOffsetAttribute()
    {
        return core;
    }
    public static Attribute getMemoryOffsetAttribute()
    {
        return memory;
    }
    public static Attribute getVoltageOffsetAttribute()
    {
        return voltage;
    }
    public static Attribute getFanSpeedAttribute()
    {
        return fanSpeed;
    }
    public static Attribute getFanModeAttribute()
    {
        return fanMode;
    }
    public static Attribute getTempAttribute()
    {
        return temp;
    }
    public static ArrayList<PerformanceLevel> getPerformanceLevels()
    {
        return perfLevels;
    }
    public static GPUController<Integer> getCoreOffsetController()
    {
        return coreController;
    }
    public static GPUController<Integer> getMemoryOffsetController()
    {
        return memoryController;
    }
    public static GPUController<Integer> getVoltageOffsetController()
    {
        return voltageController;
    }
    public static GPUController<Integer> getPowerLimitController()
    {
        return powerLimitController;
    }
    public static GPUController<Integer> getFanSpeedController()
    {
        return fanSpeedController;
    }
    public static GPUController<Integer> getFanModeController()
    {
        return fanModeController;
    }
    public static GPUController<Integer> getPowerMizerController()
    {
        return powerMizerController;
    }
}
