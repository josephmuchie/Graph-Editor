package graphEditor.controller;

import graphEditor.model.GraphModel;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * Implements the menu, which contains, amongst other things, the buttons for performing actions
 */
public class GraphMenu extends JMenuBar {
    GraphModel model;
    public GraphMenu(GraphModel model){
        this.model = model;
        initialiseGraphMenu();
        model.update(model, this);
    }
    
    /**
     * Initialises the menu
     */
    private void initialiseGraphMenu(){
        JMenu menuVertex = new JMenu("Edit Vertices");
        menuVertex.setMnemonic(KeyEvent.VK_A);
        menuVertex.getAccessibleContext().setAccessibleDescription("Menu for editing vertices");
        menuVertex.add(new NewNode(model));
        menuVertex.add(new RemoveNode(model));
        menuVertex.add(new UnselectAllNodes(model));
        menuVertex.add(new SelectAllNodes(model));
        JTextArea textRename = new JTextArea("Enter name");
        textRename.setAlignmentX(JTextField.CENTER);
        menuVertex.add(new RenameNode(model, textRename));
        menuVertex.add(textRename);
        this.add(menuVertex);
        JMenu menuEdge = new JMenu("Edit Edges");
        menuEdge.setMnemonic(KeyEvent.VK_A);
        menuEdge.getAccessibleContext().setAccessibleDescription("Menu for editing edges");
        menuEdge.add(new NewEdge(model));
        menuEdge.add(new RemoveEdge(model));
        this.add(menuEdge);
        JMenu menuOther = new JMenu("Functions");
        JTextArea textSave = new JTextArea("Enter filename to save");
        textSave.setAlignmentX(JTextField.CENTER);
        menuOther.add(new SaveGraph(model, textSave));
        menuOther.add(textSave);
        JTextArea textLoad = new JTextArea("Enter filename to load");
        textLoad.setAlignmentX(JTextField.CENTER);
        menuOther.add(new LoadGraph(model, textLoad));
        menuOther.add(textLoad);
        this.add(menuOther);
        this.add(new Undo(model));
        this.add(new Redo(model));
        this.add( new JLabel(model.toString()));
    }
}
