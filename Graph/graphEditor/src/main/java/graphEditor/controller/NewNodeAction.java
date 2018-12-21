
package graphEditor.controller;

import graphEditor.model.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import java.util.Observer;
import java.util.Observable;
/**
 *
 * Creates a new default node, in the default position
 */
public class NewNodeAction extends AbstractAction implements Observer{
    GraphModel model;
    public NewNodeAction(GraphModel model) {
        super("Add a new node");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        model.addNewVertex();
        model.modelBackup();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
