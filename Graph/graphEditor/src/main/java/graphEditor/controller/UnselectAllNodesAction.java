
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
 * Unselects all currently highlighted nodes
 */
public class UnselectAllNodesAction extends AbstractAction implements Observer{
    GraphModel model;
    public UnselectAllNodesAction(GraphModel model) {
        super("Unselect all nodes");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        ArrayList<GraphVertex> newList = model.getVertexList();
        for(int i = 0; i < newList.size(); i++){
            newList.get(i).setColour(new Color(0xFF, 0xFF, 0xAA));
        }
        model.setVertexList(newList);
        model.modelBackup();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
