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


/**
 *
 * @author joseph
 */
public class RenameNode extends JButton {
    JTextArea area;
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Renames a node");
    }
    public RenameNode(GraphModel model, JTextArea area){
        super(new RenameNodeAction(model, area));
        this.area = area;
        setButtonProperties();
    }
}
