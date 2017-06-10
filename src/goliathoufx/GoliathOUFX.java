package goliathoufx;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GoliathOUFX extends Application
{
    public static Scene scene;
    
    public static void main(String[] args) throws IOException
    {   
        InstanceProvider.init();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        scene = new Scene(new AppFrame(stage));
        
        scene.getStylesheets().add("skins/Goliath-Numix.css");
        
        stage.setTitle("Goliath Overclocking Utility V" + AppSettings.getVersion());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(372);
        stage.setWidth(750); 
        stage.setScene(scene);
        stage.show();
    }
}
