package graphEditor.controller;
import graphEditor.model.*;
import graphEditor.view.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author joseph
 */
public class MouseActions extends MouseInputAdapter {
    private GraphModel model;
    private GraphPanel panel;
    
    private boolean selected;
    private int startX;
    private int startY;
    private int location = 0;
    
    public MouseActions(GraphModel model, GraphPanel panel){
        this.model = model;
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        selected = false;
    }
    public void mousePressed(MouseEvent event) {
        Point point = new Point(event.getX(), event.getY());
        location = panel.inNode(point);
        if(location != -1){
            selected = true;
            startX = event.getX();
            startY = event.getY();
        }
        if(model.getEdgeDrawNode() != null){
            if(location != -1){
                GraphVertex selectedNode = null;
                for(int i = 0; i < model.getVertexList().size(); i++){
                    if(model.getVertexList().get(i).getColour() == Color.red){
                        selectedNode = model.getVertexList().get(i);
                    }
                }
                ArrayList<GraphEdge> newEdgeList = model.getEdgeList();
                for(int i = 0; i < newEdgeList.size(); i++){
                    if(model.getEdgeList().get(i).getVertex1().equals(model.getEdgeDrawNode()) || model.getEdgeList().get(i).getVertex2().equals(model.getEdgeDrawNode())){
                        newEdgeList.remove(i);
                        i = -1;
                    }
                }
                model.addEdge(selectedNode, model.getVertexList().get(location));
                model.addEdge(model.getVertexList().get(location), selectedNode);
                model.setEdgeList(newEdgeList);
                model.setEdgeDrawNode(null);
            } else {
                model.removeVertex(model.getEdgeDrawNode());
                model.setEdgeDrawNode(null);
            }
        }
        model.update(model, this);
    }
    public void mouseReleased(MouseEvent event){
        ArrayList<GraphVertex> newList = model.getVertexList();
        if(selected && location < newList.size() && location > -1){
            if(startX > event.getX() - 10 && startX < event.getX() + 10 && startY > event.getY() - 10 && startY < event.getY() + 10){
                Point point = new Point(startX, startY);
                panel.nodeHighlight(panel.inNode(point));
                selected = false;
            } else {
                newList.get(location).setRectangle(event.getX()-20, event.getY()-10, 20, 50);
                selected = false;
            }
            model.setVertexList(newList);
            model.update(model, this);
        }
        
        model.update(model, this);
    }
    public void mouseDragged(MouseEvent event){
        ArrayList<GraphVertex> newList = model.getVertexList();
        if(selected && location < newList.size() && location > -1 && (startX < event.getX() - 10 || startX > event.getX() + 10 || startY < event.getY() - 10 || startY > event.getY() + 10)){
            newList.get(location).setRectangle(event.getX()-20, event.getY()-10, 20, 50);
            model.setVertexList(newList);
            model.update(model, this);
        }
        if(model.getEdgeDrawNode() != null){
            GraphVertex newCursorNode = model.getEdgeDrawNode();
            newCursorNode.setRectangle(event.getX(), event.getY(), 0, 0);
            model.setEdgeDrawNode(newCursorNode);
            model.update(model, this);
        }
    }
    public void mouseMoved(MouseEvent event){
         if(model.getEdgeDrawNode() != null){
            GraphVertex newCursorNode = model.getEdgeDrawNode();
            newCursorNode.setRectangle(event.getX(), event.getY(), 0, 0);
            model.setEdgeDrawNode(newCursorNode);
            model.update(model, this);
        }
    }
}
