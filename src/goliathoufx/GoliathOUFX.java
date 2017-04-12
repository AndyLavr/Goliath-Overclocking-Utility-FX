
package goliathoufx;

import goliath.ou.attribute.Attribute;
import goliath.ou.utility.CsvReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoliathOUFX extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        AppFrame root;
        ArrayList<Attribute> attributes = new ArrayList<>();
        
        CsvReader settingsReader = new CsvReader(new AppSettingsDirectory(System.getProperty("user.home") + "/GoliathOU").getAppConfig());
        AppSettings.loadSettings(settingsReader.getKeys(), settingsReader.getValues());
        
        attributes.add(new Attribute("GPUCurrentCoreVoltage", "Current Voltage", "mV", true, true));
        attributes.add(new Attribute("NvidiaDriverVersion", "Driver Version", null, true, false));
        attributes.add(new Attribute("RefreshRate", "Display Refresh Rate", null, true, false));
        attributes.add(new Attribute("GpuUUID", "GPU ID", null, true, false));
        attributes.add(new Attribute("CUDACores", "GPU CUDA Cores", null, true, false));
        attributes.add(new Attribute("GPUCurrentClockFreqs", "Current Clocks(CPU,Memory)", "MHz", true, true));
        attributes.add(new Attribute("TotalDedicatedGPUMemory", "Dedicated GPU Memory", "MB", true, false));
        attributes.add(new Attribute("UsedDedicatedGPUMemory", "Used GPU Memory", "MB", true, true));
        attributes.add(new Attribute("GPUAdaptiveClockState", "Adaptive Clocking", null, true, false));
        attributes.add(new Attribute("PCIEGen", "PCIE Gen", null, true, true));
        attributes.add(new Attribute("PCIECurrentLinkWidth", "PCIE Bus Width", "x", true, true));
        attributes.add(new Attribute("PCIECurrentLinkSpeed", "PCIE Bus Speed", "GT/s", true, true));
        attributes.add(new Attribute("GPUPowerMizerMode", "GPU Performance Mode", null, true, true));
        attributes.add(new Attribute("VideoDecoderUtilization", "Video Decoder Utililzation", "%", true, true));
        attributes.add(new Attribute("VideoEncoderUtilization", "Video Encoder Utililzation", "%", true, true));
        attributes.add(new Attribute("GPUCurrentPerfLevel", "Performance Level", null, true, true));
        attributes.add(new Attribute("GPUOverVoltageOffset", "Voltage Offset", "mV", true, true));
        attributes.add(new Attribute("GPUMemoryTransferRateOffset", "Memory Transfer Rate Offset", "MHz", true, true));
        attributes.add(new Attribute("GPUGraphicsClockOffset", "Graphics Clock Offset", "MHz", true, true));
        attributes.add(new Attribute("GPUTargetFanSpeed", "Target Fan Speed", "%", true, true));
        attributes.add(new Attribute("GPUCurrentFanSpeedRPM", "Current Fan Speed", "RPM", true, true));
        
        root = new AppFrame(attributes);
        
        Scene appScene = new Scene(root);
        stage.setTitle("Goliath Overclocking Utility V" + AppSettings.getVersion());
        stage.setResizable(false);
        stage.setHeight(360);
        stage.setWidth(750);
        stage.setOnCloseRequest(new exitHandler());
        stage.setScene(appScene);
        stage.show();
    }
    private static class exitHandler implements EventHandler
    {
        @Override
        public void handle(Event event)
        {
            Platform.exit();
            System.exit(0);
        }
    }
}
