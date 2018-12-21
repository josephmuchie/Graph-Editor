
package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;

/**
 *
 *  Renames nodes
 */
public class RenameNodeAction extends AbstractAction implements Observer{
    GraphModel model;
    JTextArea area;
    public RenameNodeAction(GraphModel model, JTextArea area) {
        super("Rename all highlighted node");
        this.model = model;
        this.area = area;
        model.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.modelBackup();
        if(model.numberOfSelectedNodes() > 0){
            ArrayList<GraphVertex> newList = model.getVertexList();
            for(int i = 0; i < model.getVertexList().size(); i++){
               if(model.getVertexList().get(i).getColour() == Color.red){
                    newList.get(i).setName(area.getText());
                }
            }
            area.setText("Enter name");
            model.update(model, this);
            model.modelBackup();
            
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
    
}
