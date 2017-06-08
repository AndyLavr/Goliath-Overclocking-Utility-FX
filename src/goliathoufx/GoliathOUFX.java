package goliathoufx;

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributeExporter;
import goliath.ou.attribute.AttributeImporter;
import goliath.ou.utility.CsvReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GoliathOUFX extends Application
{
    private static ArrayList<Attribute> attributes;
    public static AppSettingsDirectory APPDIR;
    public static Scene scene;
    
    public static void main(String[] args) throws IOException
    {
        AppSettingsDirectory appDir = new AppSettingsDirectory(System.getProperty("user.home") + "/GoliathOU");
        CsvReader settingsReader = new CsvReader(appDir.getAppConfig());
        AppSettings.loadSettings(settingsReader.getKeys(), settingsReader.getValues());
        APPDIR = appDir;
        
        initAttributes(appDir.getAttributesDirectory());
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        scene = new Scene(new AppFrame(attributes, stage));
        
        scene.getStylesheets().add("skins/Goliath-Numix.css");
        
        stage.setTitle("Goliath Overclocking Utility V" + AppSettings.getVersion());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(372);
        stage.setWidth(750); 
        stage.setScene(scene);
        stage.show();
    }

    private static void initAttributes(File attrDir)
    {
        attributes = new ArrayList<>();
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
            attributes.add(importer.getImportedObject());
        }
    }
}
