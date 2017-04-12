
package goliathoufx.panes;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

/**
    For getting user password.
    @author ty
 */
public class PasswordPane extends GridPane
{
    private final PasswordField password;
    private final Button okButton;
    
    public PasswordPane(AppTabPane pane)
    {
        super();
        super.setPadding(new Insets(15,15,15,15));
        super.setVgap(10);
        
        Label promptText = new Label("Root required.");
        
        okButton = new Button("Ok");
        password = new PasswordField();
        
        okButton.setPrefWidth(60);
        okButton.setOnMouseClicked(new OkButtonHandler(pane));
        password.setPromptText("Root Password");
        
        super.add(promptText, 0, 0);
        super.add(password, 0, 1);
        super.add(okButton, 0, 2);
    }
    public CharSequence getPassword()
    {
        return password.getCharacters();
    }
    private class OkButtonHandler implements EventHandler
    {
        private final AppTabPane tabPane;
        
        public OkButtonHandler(AppTabPane pane)
        {
            tabPane = pane;
        }
        @Override
        public void handle(Event event)
        {
            tabPane.gotPassword();
            password.setText(null);
        }
    }
}
