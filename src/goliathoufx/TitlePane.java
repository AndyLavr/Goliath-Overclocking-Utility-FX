package goliathoufx;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TitlePane extends HBox
{
    private final Stage stage;
    private final Label titleLabel;
    private final Button exit, minimize;
    
    private double x,y;
    
    public TitlePane(String name, Stage appStage)
    {
        super();
        super.getStyleClass().add("title-pane");
        super.setPadding(new Insets(0,0,0,0));
        super.setOnMousePressed(new AppMovementHandler());
        super.setOnMouseDragged(new AppDragHandler());
        
        stage = appStage;
        
        titleLabel = new Label(name);
        
        HBox buttonBox = new HBox();
        buttonBox.setPrefWidth(538);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        exit = new Button("Exit");
        exit.getStyleClass().add("close");
        //exit.setGraphic(new ImageView(new Image(new File("src/images/delete.png").toURI().toString())));
        exit.setOnMouseClicked(new ExitHandler());
        
        minimize = new Button("Minimize");
        minimize.getStyleClass().add("minimize");
        //minimize.setGraphic(new ImageView(new Image(new File("src/images/min.png").toURI().toString())));
        minimize.setOnMouseClicked(new MinimizeHandler());
        
        buttonBox.getChildren().addAll(minimize, exit);
        
        super.getChildren().addAll(titleLabel, buttonBox);
    }

    private class MinimizeHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            stage.setIconified(true);
        }
    }
    private class AppMovementHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        }
        
    }
    private class AppDragHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        }
        
    }
    private class ExitHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            Platform.exit();
            System.exit(0);
        }
    }
}
