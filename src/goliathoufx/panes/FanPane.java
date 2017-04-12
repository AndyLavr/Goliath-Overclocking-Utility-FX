/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes;

import goliathoufx.panes.fan.ProfileListPane;
import goliathoufx.panes.fan.ProfileTabPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ty
 */
public class FanPane extends BorderPane
{
    private final ProfileListPane profilePane;
    private final ProfileTabPane profileTabPane;
    
    public FanPane()
    {
        super();
        
        profilePane = new ProfileListPane();
        profileTabPane = new ProfileTabPane();
        
        super.centerProperty().set(profileTabPane);
        super.leftProperty().set(profilePane);
    }
}
