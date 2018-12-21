
package graphEditor.controller;

import graphEditor.model.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import java.util.Observer;
import java.util.Observable;
/**
 *
 * redoes the last action
 */
public class RedoAction extends AbstractAction implements Observer{
    GraphModel model;
    public RedoAction(GraphModel model) {
        super("Redo");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelRedo();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
