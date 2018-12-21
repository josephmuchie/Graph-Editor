
package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;

/**
 *@author joseph
 * Adds edges
 */
public class NewEdgeAction extends AbstractAction implements Observer{
    GraphModel model;
    public NewEdgeAction(GraphModel model) {
        super("Adds edges between the selected nodes");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        if(model.numberOfSelectedNodes() > 1){
            addMultipleEdges();
        }
        if(model.numberOfSelectedNodes() == 1){
            addSingleEdges();
        }
        model.modelBackup();
    }
    /**
     * Adds a single edge
     */
    private void addSingleEdges(){
        GraphVertex selectedNode = new GraphVertex("");
        int i = 0;
        int x = 0;
        int y = 0;
        for(i = 0; i < model.getVertexList().size(); i++){
            if(model.getVertexList().get(i).getColour() == Color.red){
                x = model.getVertexList().get(i).getRectangle().x;
                y = model.getVertexList().get(i).getRectangle().y;
                break;
            }
        }
        selectedNode.setRectangle(x, y, 0, 0);
        model.addEdge(selectedNode, model.getVertexList().get(i));
        model.setEdgeDrawNode(selectedNode); 
    }
    /**
     * adds edges in the case that multiple nodes are selected
     */
    private void addMultipleEdges(){
        ArrayList<GraphVertex> selectedNodes = new ArrayList<>();
        for(int i = 0; i < model.getVertexList().size(); i++){
            if(model.getVertexList().get(i).getColour() == Color.red){
                selectedNodes.add(model.getVertexList().get(i));
            }
        }
        for(int i = 0; i < selectedNodes.size(); i++){
            GraphVertex newVertex1 = selectedNodes.get(i);
            for(int k = 0; k < selectedNodes.size(); k++){
                GraphVertex newVertex2 = selectedNodes.get(k);
                model.addEdge(newVertex1, newVertex2);
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {
    }
}
