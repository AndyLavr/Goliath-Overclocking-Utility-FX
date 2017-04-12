/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliathoufx.panes.fan;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

/**

 @author ty
 */
public class FanProfileChartTemplate extends LineChart<Number, Number>
{
    private Series<Number, Number> nodes;
    private XYChart.Data<Number, Number> selectedNode;
    private final NumberAxis x, y;
    
    public FanProfileChartTemplate(String profile)
    {
        super(new NumberAxis(), new NumberAxis());
        //super.setTitle(profile);
        
        x = (NumberAxis)super.getXAxis();
        y = (NumberAxis)super.getYAxis();
        
        x.setAutoRanging(false);
        x.setLowerBound(35);
        x.setUpperBound(75);
        
        y.setAutoRanging(false);
        y.setLowerBound(5);
        y.setUpperBound(100);
        
        nodes = new Series<>();
        nodes.setName("Node");
        nodes.getData().add(new XYChart.Data<>(50, 10));
        nodes.getData().add(new XYChart.Data<>(60, 20));
        nodes.getData().add(new XYChart.Data<>(70, 30));
        
        super.getData().addAll(nodes);

        for(int i = 0; i < nodes.getData().size(); i++)
        {
            nodes.getData().get(i).getNode().setOnMousePressed(new mousePressEvent(nodes.getData().get(i)));
            nodes.getData().get(i).getNode().setOnMouseReleased(new mouseReleasedEvent());
        }
    }
    private class mousePressEvent implements EventHandler<MouseEvent>
    {
        private final XYChart.Data<Number, Number> node;
        
        public mousePressEvent(XYChart.Data<Number, Number> point)
        {
            node = point;
        }
        @Override
        public void handle(MouseEvent event)
        {
            selectedNode = node;
        }
    }
    private class mouseReleasedEvent implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {   
            Point2D mouseSceneCoords = new Point2D(event.getSceneX(), event.getSceneY());
            System.out.println(selectedNode.getYValue());
            System.out.println(event.getY());
            selectedNode.setYValue(y.getDisplayPosition(event.getY()));
            selectedNode = null;
        }
    }
}
