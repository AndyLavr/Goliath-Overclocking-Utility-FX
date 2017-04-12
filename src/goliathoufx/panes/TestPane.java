
package goliathoufx.panes;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;

public class TestPane extends BorderPane
{
    private BorderPane layout = new BorderPane();
    private Button btn = new Button();
    private Button btn2 = new Button();
    private Button btn3 = new Button();
    private Button btn4 = new Button();
    private Button btn5 = new Button();
    
    public TestPane()
    {
        super();
        
        btn.setMinSize(10, 10);
        btn.setText("Center");
        btn.setVisible(true);
        
        btn.setOnMouseClicked(new btnHandler());
       
        btn2.setMinSize(10, 10);
        btn2.setText("Top");
        btn2.setVisible(true);
        btn3.setMinSize(10, 10);
        btn3.setText("Right");
        btn3.setVisible(true);
        
        btn4.setMinSize(10, 10);
        btn4.setText("Left");
        btn4.setVisible(true);
        
        btn5.setText("bottom");
        
        //layout.setMinSize(200, 200);
        super.setLeft(btn4);
        super.setCenter(btn);
        super.setTop(btn2);
        super.setRight(btn3);
        super.setBottom(btn5);
        
        super.getChildren().add(layout);
    }

    private class btnHandler implements EventHandler
    {
        private Button btn;
        
        public btnHandler()
        {
            btn = new Button();
        }
        
        @Override
        public void handle(Event event)
        {
            if((Button)event.getSource() == btn)
            {
                System.out.println("Clicked!");
            }
        }
    }
}
