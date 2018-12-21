package graphEditor.model;


/**
 *
 * @author joseph
 */
public class GraphEdge {
    
    private GraphVertex vertex1;
    private GraphVertex vertex2;
    
    public GraphEdge(GraphVertex vertex1, GraphVertex vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    /**
     * gets vertex1
     */
    public GraphVertex getVertex1(){
        return this.vertex1;
    }
    /**
     * gets vertex2
     */
    public GraphVertex getVertex2(){
        return this.vertex2;
    }    
    
    /**
     * sets vertex1
     */
    public void setVertex1(GraphVertex newVertex){
        this.vertex1 =  newVertex;
    }
    /**
     * sets vertex2
     */
    public void setVertex2(GraphVertex newVertex){
        this.vertex2 =  newVertex;
    }    
    
}



