
package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;

/**
 *
 * Implements the action for removing edges
 */
public class RemoveEdgeAction extends AbstractAction implements Observer{
    GraphModel model;
    public RemoveEdgeAction(GraphModel model) {
        super("Remove edges between selected nodes, or all edges connected to one node");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        if(model.numberOfSelectedNodes() > 1){
            removeMultipleNodeEdges();
        }
        if(model.numberOfSelectedNodes() == 1){
            removeSingleNodeEdges();
        }        
        model.modelBackup();
    }
    /**
     * Removes all the edges between a selection of nodes
     */
    private void removeMultipleNodeEdges(){
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
                model.removeEdge(newVertex1, newVertex2);
                model.removeEdge(newVertex2, newVertex1);
            }
        }
    }
    /**
     * For the case in which only a signle node is selected
     */
    private void removeSingleNodeEdges(){
        GraphVertex selectedNode = null;
        for(int i = 0; i < model.getVertexList().size(); i++){
            if(model.getVertexList().get(i).getColour() == Color.red){
                selectedNode = model.getVertexList().get(i);
                break;
            }
        }
        ArrayList<GraphEdge> newEdgeList = model.getEdgeList();
        for(int i = 0; i < newEdgeList.size(); i++){
            if(model.getEdgeList().get(i).getVertex1().equals(selectedNode) || model.getEdgeList().get(i).getVertex2().equals(selectedNode)){
                newEdgeList.remove(i);
                i = -1;
            }
        }
        model.setEdgeList(newEdgeList);
    }
    
    
    
    
    
    
    
    @Override
    public void update(Observable o, Object arg) {
    }
}
