
package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import java.util.Observer;
import java.util.Observable;
/**
 *
 * Selects all nodes
 */
public class SelectAllNodesAction extends AbstractAction implements Observer{
    GraphModel model;
    public SelectAllNodesAction(GraphModel model) {
        super("Select all nodes");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        ArrayList<GraphVertex> newList = model.getVertexList();
        for(int i = 0; i < newList.size(); i++){
            newList.get(i).setColour(Color.red);
        }
        model.setVertexList(newList);
        model.modelBackup();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
