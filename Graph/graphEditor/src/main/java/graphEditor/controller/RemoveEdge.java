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



/**
 *
 * @author joseph
 */
public class RemoveEdge extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Remove edges from the selected nodes");
    }
    public RemoveEdge(GraphModel model){
        super(new RemoveEdgeAction(model));
        setButtonProperties();
    }
    
}
