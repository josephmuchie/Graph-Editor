/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphEditor.controller;

import graphEditor.model.GraphModel;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author joseph
 */
public class SaveGraph extends JButton {
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Saves graph, under a given filename");
    }
    public SaveGraph(GraphModel model, JTextArea fileName){
        super(new SaveGraphAction(model, fileName));
        setButtonProperties();
    }
}
