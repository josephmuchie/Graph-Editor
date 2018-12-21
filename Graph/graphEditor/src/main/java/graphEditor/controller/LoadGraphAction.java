/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphEditor.controller;

import graphEditor.model.GraphModel;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;

/**
 *
 * @author joseph
 */
public class LoadGraphAction extends AbstractAction implements Observer{
    GraphModel model;
    JTextArea fileName;
    public LoadGraphAction(GraphModel model, JTextArea fileName) {
        super("Load graph");
        this.model = model;
        this.fileName = fileName;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.loadGraph(fileName.getText());
        fileName.setText("Enter filename");
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }
}
