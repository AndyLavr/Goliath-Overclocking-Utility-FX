/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.performance.overclocking;

import goliath.ou.interfaces.GPUController;
import goliathoufx.panes.ConsolePane;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

/**

 @author Ty Young
 */
public class ApplyHandler implements EventHandler<MouseEvent>
{
    private final Spinner<Integer> spin;
    private final GPUController<Integer> cont;
    
    public ApplyHandler(Spinner<Integer> valueSpin, GPUController<Integer> controller)
    {
        spin = valueSpin;
        cont = controller;
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        if(cont.getName().equals("GPUOverVoltageOffset"))
            cont.setValue(spin.getValue()*1000);
        else
            cont.setValue(spin.getValue());
        
        ConsolePane.addText(cont.getOutput());
    }
}
