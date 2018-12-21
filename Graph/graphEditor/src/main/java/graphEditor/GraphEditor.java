package graphEditor;
import graphEditor.model.*;
import graphEditor.view.*;
import graphEditor.controller.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
        
/**
 * The main class for the function. as well as initializing the GraphModel, GraphFrame, Graphpanel, and GraphMenu,
 * it also loads a graph given by command line arguments, if of course one is present 
 * 
 */




public class GraphEditor {
    
    public static void main(String[] args) {
        ArrayList<GraphEdge> edgeList = new ArrayList<GraphEdge>();
        ArrayList<GraphVertex> vertexList = new ArrayList<GraphVertex>();
        GraphModel graphModel =  new GraphModel(edgeList, vertexList);
        GraphFrame graphFrame = new GraphFrame();
        GraphPanel graphPanel = new GraphPanel(graphModel);
        GraphMenu graphMenu = new GraphMenu(graphModel);
        graphFrame.setJMenuBar(graphMenu);
        graphFrame.getContentPane().add(graphPanel);
        graphFrame.initialiseGraphFrame();
        MouseActions mouseActions = new MouseActions(graphModel, graphPanel);        
        if(args.length != 0){ /*This is the case in which there is a filename given as an argument */
            File file = new File(args[0]);
            System.out.println("loaded " + file.getName());
            Scanner scanner = new Scanner(System.in);
            graphModel.loadGraph(file.getName());
        }
        
    }
    
    
    
    
    
    
}