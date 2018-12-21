package graphEditor.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Stack;


/**
 *
 * Implements the model, which contains all vertices and edges in the graph, and most methods for setting and modifying them
 * It also contains methods for saving and loading the graph, as well as undoing and redoing actions
 */
public class GraphModel extends Observable implements Observer{
    private ArrayList<GraphEdge> edgeList = new ArrayList<>();
    private ArrayList<GraphVertex> vertexList = new ArrayList<>();
    private GraphVertex nodeToDrawEdgeFrom = null;
    private Stack<GraphModel> modelStack = new Stack<>();
    private Stack<GraphModel> redoStack = new Stack<>();

    public GraphModel(ArrayList<GraphEdge> edgeList, ArrayList<GraphVertex> vertexList){
        this.edgeList = edgeList;
        this.vertexList = vertexList;
    }
    /**
     * Adds  a new edge between two vertices
     */
    public void addEdge(GraphVertex vertex1, GraphVertex vertex2){
        GraphEdge newEdge = new GraphEdge(vertex1, vertex2);
        edgeList.add(newEdge);
        setChanged();
        notifyObservers();
    }
    /**
     * Removes an edge between two specified vertices
     */
    public void removeEdge(GraphVertex vertex1, GraphVertex vertex2){
        for(int i = 0; i < edgeList.size(); i++){
            if((edgeList.get(i).getVertex1() == vertex1 && edgeList.get(i).getVertex2() == vertex2) || (edgeList.get(i).getVertex1() == vertex2 && edgeList.get(i).getVertex2() == vertex1) ){
                edgeList.remove(i);
                i = 0;
            }
        }
        setChanged();
        notifyObservers();
    }
    /**
     * adds a new vertex, with the default name to the default position
     */
    public void addNewVertex(){
        GraphVertex newVertex = new GraphVertex("test");
        newVertex.setVertexDefault();
        vertexList.add(newVertex);
        setChanged();
        notifyObservers();
    }
    /**
     * Removes a vertex, and its coresponding edges
     */
    public void removeVertex(GraphVertex vertex){
        vertexList.remove(vertex);
        for(int i = 0; i < edgeList.size(); i++){
            if(edgeList.get(i).getVertex1() == vertex || edgeList.get(i).getVertex2() == vertex ){
                edgeList.remove(i);
                i = -1;
            }
        }
        setChanged();
        notifyObservers();
    }
    /**
     * Adds a given vertex
     */
    public void addVertex(GraphVertex newVertex){
        GraphVertex vertexForAdding =  newVertex;
        vertexList.add(vertexForAdding);
        setChanged();
        notifyObservers();
    }
    /**
     * Saves the graph
     */
    public void saveGraph(String filename){
        try {
            File saveFilesFolder = new File("savefiles");
            saveFilesFolder.mkdir();
            PrintWriter out = new PrintWriter("savefiles/" + filename);
            String graphInformation = vertexList.size() + " " + edgeList.size() + System.lineSeparator();
            graphInformation = graphInformation + vertexStringForSaving() + edgeStringForSaving();
            out.println(graphInformation);
            out.close();
        } catch(IOException i) {
            System.out.println("There was an error \n");
            i.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }
    /**
     * Loads the graph
     */
    public void loadGraph(String filename){
        try {
            graphModelReset();
            Scanner scanner = new Scanner(new File("savefiles/" + filename));
            int numberOfVertices = scanner.nextInt();
            int numberOfEdges = scanner.nextInt();
            for(int i = 0; i < numberOfVertices; i++){
                GraphVertex newVertex = new GraphVertex("null");
                newVertex.setRectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                newVertex.setName(scanner.next());
                vertexList.add(newVertex);
            }
            for(int i = 0; i < numberOfEdges; i++){
                int vertex1 = scanner.nextInt();
                int vertex2 = scanner.nextInt();
                GraphEdge newEdge = new GraphEdge(vertexList.get(vertex1), vertexList.get(vertex2));
                edgeList.add(newEdge);
            }
            scanner.close();
        } catch(IOException i) {
            System.out.println("There was an error \n");
            i.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }
    /**
     * gets the edgeList
     */
    public ArrayList<GraphEdge> getEdgeList(){
        ArrayList<GraphEdge> returnList = this.edgeList;
        return returnList;
    }
    /**
     * gets the vertexList
     */
    public ArrayList<GraphVertex> getVertexList(){
        ArrayList<GraphVertex> returnList = this.vertexList;
        return returnList;
    }
    /**
     * sets the edgeList
     */
    public void setEdgeList(ArrayList<GraphEdge> newEdgeList){
        ArrayList<GraphEdge> newList = newEdgeList;
        this.edgeList = newList;
        setChanged();
        notifyObservers();
    }
    /**
     * sets the vertexList
     */
    public void setVertexList(ArrayList<GraphVertex> vertexList){
        ArrayList<GraphVertex> newList = vertexList;
        this.vertexList = newList;
        setChanged();
        notifyObservers();
    }
    /**
     * gets the string for saving the information on vertices 
     */
    private String vertexStringForSaving(){
        String output = "";
        for(int i = 0; i < vertexList.size(); i++){
            Rectangle currentVertexRectangle = vertexList.get(i).getRectangle();
            output = output + currentVertexRectangle.x + " " + currentVertexRectangle.y + " " + currentVertexRectangle.height + " " + currentVertexRectangle.width + " " + vertexList.get(i).getName() + System.lineSeparator();  
        }
        return output;
    }
    /**
     * gets the string for saving information on edges
     */
    private String edgeStringForSaving(){
        String output = "";
        for(int i = 0; i < edgeList.size(); i++){
            output = output + vertexList.lastIndexOf(edgeList.get(i).getVertex1()) + " " + vertexList.lastIndexOf(edgeList.get(i).getVertex2()) + System.lineSeparator();
        }
        return output;
    }
    /**
     * Returns the number of selected nodes in the graph
     */
    public int numberOfSelectedNodes(){
        int returnValue = 0;
        for(int i = 0; i < this.getVertexList().size(); i++){
            if(this.getVertexList().get(i).getColour() == Color.red){
                returnValue++;
            }
        }
        return returnValue;
    }
    /**
     * Gets the node to draw the edge from
     */
    public GraphVertex getEdgeDrawNode(){
        GraphVertex returnNode = this.nodeToDrawEdgeFrom;
        return returnNode;
    }
    /**
     * sets the node to draw the edge from
     */
    public void setEdgeDrawNode(GraphVertex vertex){
        GraphVertex newVertex = vertex;
        this.nodeToDrawEdgeFrom = newVertex;
    }
    /**
     * Adds the current state to the undo stack
     */
    public void modelBackup(){
        ArrayList<GraphEdge> edgeListForBackingUp = new ArrayList<>();
        ArrayList<GraphVertex> vertexListForBackingUp = new ArrayList<>();
        for(int i = 0; i < getVertexList().size(); i++){
            GraphVertex newVertexForAdding = getVertexList().get(i);
            vertexListForBackingUp.add(newVertexForAdding);
        }
        for(int i = 0; i < getEdgeList().size(); i++){
            GraphEdge newEdgeForAdding = getEdgeList().get(i);
            edgeListForBackingUp.add(newEdgeForAdding);
        }
        GraphModel modelState = new GraphModel(edgeListForBackingUp, vertexListForBackingUp);
        if(modelStack.isEmpty() || !modelStack.peek().getEdgeList().equals(edgeListForBackingUp) || !modelStack.peek().getVertexList().equals(vertexListForBackingUp)){
            modelStack.push(modelState);
        }
        redoStack.empty();
    }
    /**
     * undoes, by going back to a previous state
     */
    public void modelUndo(){
        GraphModel modelState = modelStack.pop();
        this.setEdgeList(modelState.getEdgeList());
        this.setVertexList(modelState.getVertexList());
        this.redoStack.push(modelState);
        setChanged();
        notifyObservers();
        
    }
    /**
     * Redoes, by going forwards to a future state
     */
    public void modelRedo(){
        if(!redoStack.isEmpty()){
            GraphModel newModel = redoStack.pop();
            modelStack.push(newModel);
            setEdgeList(newModel.getEdgeList());
            setVertexList(newModel.getVertexList());
        }
        setChanged();
        notifyObservers();
    }
    /**
     * Resets the graph model
     */
    public void graphModelReset(){
        this.edgeList = new ArrayList<GraphEdge>();
        this.vertexList = new ArrayList<GraphVertex>();
        this.modelStack.empty();
        this.redoStack.empty();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
    
}



