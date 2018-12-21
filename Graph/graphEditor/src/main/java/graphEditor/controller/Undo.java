/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphEditor.controller;


import graphEditor.model.*;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;


/**
 *
 * @author joseph
 */
public class Undo extends JButton {
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Undoes the last operation");
    }
    public Undo(GraphModel model){
        super(new UndoAction(model));
        setButtonProperties();
    }
}
