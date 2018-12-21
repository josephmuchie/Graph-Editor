
 
package graphEditor.view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import graphEditor.model.*;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author joseph
 */
public class GraphPanel extends JPanel implements Observer{
    
    private GraphModel model;
    
    public GraphPanel(GraphModel model){
        setBackground(new Color(0x88, 0x99, 0xFF));
        setVisible(true);
        setOpaque(true);
        model.addObserver(this);
        this.model = model;
        this.setLayout(null);
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
    /**
     * Checks if a node has been selected, returns the index of the most recent node at that position, i.e the topmost one
     */
    public int inNode(Point point) {
        for(int i = model.getVertexList().size() - 1; i > -1; i--){
            Rectangle nodeRectangle = model.getVertexList().get(i).getRectangle();
            if(point.getX() > nodeRectangle.x  && point.getX() < nodeRectangle.x + nodeRectangle.getWidth() && point.getY() > nodeRectangle.y  && point.getY() < nodeRectangle.y + nodeRectangle.getHeight()){
                return i;
            }
        }
        return -1;
    }
    /**
     * Highlights/unhighlights a specific node ( takes as it's argument the index of the node in the vertexList
     */
    public void nodeHighlight(int index){
        ArrayList<GraphVertex> returnList = this.model.getVertexList();
        if(returnList.get(index).getColour() != Color.red){
            returnList.get(index).setColour(Color.red);
        } else {
            returnList.get(index).setColour(new Color(0xFF, 0xFF, 0xAA));
        }
        this.model.setVertexList(returnList);
        repaint();
    }
    /**
     * Draws the nodes as rectangles
     */
    public void paintNodes(Graphics2D g2){
        double x;
        double y;
        double height;
        double width;
        JLabel nodeName;
        for(int i = 0; i < model.getVertexList().size(); i++){
            height = model.getVertexList().get(i).getRectangle().getHeight();
            width = model.getVertexList().get(i).getRectangle().getWidth();
            x = model.getVertexList().get(i).getRectangle().getX();
            y = model.getVertexList().get(i).getRectangle().getY();
            g2.setPaint(model.getVertexList().get(i).getColour());
            Rectangle2D nodeBack = new Rectangle2D.Double(x, y, width, height);
            g2.draw(nodeBack);
            g2.fill(nodeBack);
            nodeName = new JLabel(model.getVertexList().get(i).getName());
            nodeName.setLocation((int)x, (int)y); 
            nodeName.setSize(150,50);
            this.add(nodeName);
        }
    }
    /**
     * Draws the edges 
     */
    public void paintEdges(Graphics2D g2){
        for(int i = 0; i < model.getEdgeList().size(); i++){
            g2.draw(new Line2D.Double(model.getEdgeList().get(i).getVertex1().getRectangle().getCenterX(), model.getEdgeList().get(i).getVertex1().getRectangle().getCenterY(), model.getEdgeList().get(i).getVertex2().getRectangle().getCenterX(), model.getEdgeList().get(i).getVertex2().getRectangle().getCenterY()));
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        this.removeAll();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        paintEdges(g2);
        paintNodes(g2);
    }
    /**
     * Sets the model
     */
    public void setModel(GraphModel newModel){
        GraphModel modelForSetting = newModel;
        this.model = modelForSetting;
    }
}
