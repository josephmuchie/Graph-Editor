
package graphEditor.model;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

/**
 *
 * Models the vertices for the graph
 */
public class GraphVertex {
    
    private String name;
    private Rectangle rectangle = new Rectangle(0, 0, 50, 50);
    private Color colour = new Color(0xFF, 0xFF, 0xAA);
    public GraphVertex(String name){
        this.name = name;
    }

    /**
     * gets the name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Sets the rectangle
     */
    public void setRectangle(int x, int y, int height, int width){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.height = height;
        rectangle.width = width;
    }
    /**
     * gets the rectangla
     */
    public Rectangle getRectangle(){
        Rectangle returnRectangle = this.rectangle;
        return returnRectangle;
        
    }
    /**
     * sets the name
     */
    public void setName(String newName){
        this.name = newName;
    }
    /**
     * Sets the name and the rectangle to defaults
     */
    public void setVertexDefault(){
        this.setName("New Default Vertex");
        rectangle.setRect(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width/2, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height/2 , 50, 20);
        this.colour = new Color(0xFF, 0xFF, 0xAA);
    }
    /**
     * Sets the colour
     */
   public void setColour(Color newColour){
       this.colour = newColour;
   }
   /**
    * Gets the colour
    */
   public Color getColour(){
       return this.colour;
   }
}
