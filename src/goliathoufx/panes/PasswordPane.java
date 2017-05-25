
package goliathoufx.panes;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
    For getting user password.
    @author ty
 */
public class PasswordPane extends GridPane
{
    private final PasswordField password;
    private final Button okButton, cancelButton;
    private final HBox buttons;
    
    public PasswordPane(AppTabPane pane)
    {
        super();
        super.setPadding(new Insets(15,15,15,15));
        super.setVgap(10);
        
        buttons = new HBox();
        buttons.setSpacing(10);
        
        Label promptText = new Label("Root required.");
        
        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        
        buttons.getChildren().addAll(okButton, cancelButton);
        
        password = new PasswordField();
        
        okButton.setPrefWidth(60);
        okButton.setOnMouseClicked(new OkButtonHandler(pane));
        
        cancelButton.setOnMouseClicked(new CancelButtonHandler(pane));
        
        password.setPromptText("Root Password");
        
        super.add(promptText, 0, 0);
        super.add(password, 0, 1);
        super.add(buttons, 0, 2);

    }
    public CharSequence getPassword()
    {
        return password.getCharacters();
    }
    private class OkButtonHandler implements EventHandler<MouseEvent>
    {
        private final AppTabPane tabPane;
        
        public OkButtonHandler(AppTabPane pane)
        {
            tabPane = pane;
        }
        @Override
        public void handle(MouseEvent event)
        {
            if(password.getCharacters().length() == 0)
                event.consume();
            else
                tabPane.gotPassword();
            
            password.setText(null);
        }
    }
    private class CancelButtonHandler implements EventHandler<MouseEvent>
    {
        private final AppTabPane tabPane;
        
        public CancelButtonHandler(AppTabPane pane)
        {
            tabPane = pane;
        }
        @Override
        public void handle(MouseEvent event)
        {
            tabPane.cancelPassword();
        }
    }
}
