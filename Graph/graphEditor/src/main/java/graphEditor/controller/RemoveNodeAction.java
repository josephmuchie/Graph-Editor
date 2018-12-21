
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
 *
 * Implements the action fro removing nodes
 */
public class RemoveNodeAction extends AbstractAction implements Observer{
    GraphModel model;
    public RemoveNodeAction(GraphModel model) {
        super("Remove all highlighted nodes, and associated edges");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        ArrayList<GraphVertex> checkList = model.getVertexList();
        for(int i = 0; i < checkList.size(); i++){
            if(checkList.get(i).getColour() == Color.red){
                model.removeVertex(checkList.get(i));
                i = -1;
            }
        }
        model.modelBackup();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
