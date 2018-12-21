
package graphEditor.controller;

import graphEditor.model.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import java.util.Observer;
import java.util.Observable;
/**
 *
 * Undoes previous actions
 */
public class UndoAction extends AbstractAction implements Observer{
    GraphModel model;
    public UndoAction(GraphModel model) {
        super("Undo");
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelUndo();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
