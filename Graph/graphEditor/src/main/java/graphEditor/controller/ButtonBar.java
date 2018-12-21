package graphEditor.controller;

import graphEditor.model.GraphModel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;

/**
 *
 * This is now legacy code, and has been replaced by GraphMenu
 */
public class ButtonBar extends JMenuBar {
    GraphModel model;
    public ButtonBar(GraphModel model){
        add(new NewNode(model));
        add(new RemoveNode(model));
        add(new NewEdge(model));
        add(new RemoveEdge(model));
        add(new UnselectAllNodes(model));
        add(new SelectAllNodes(model));
        JTextArea text = new JTextArea("Enter name");
        add(new RenameNode(model, text));
        add(text);
        this.model = model;
    }
}
