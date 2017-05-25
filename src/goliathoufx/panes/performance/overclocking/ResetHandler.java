/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**

 @author Ty Young
 */
public class ResetHandler implements EventHandler<MouseEvent>
{
    private final GPUController<Integer> cont;
    
    public ResetHandler(GPUController<Integer> controller)
    {
        cont = controller;
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        cont.reset();
    }
}
