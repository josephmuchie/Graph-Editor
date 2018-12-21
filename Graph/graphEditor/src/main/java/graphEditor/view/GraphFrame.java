
package graphEditor.view;

import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * sets up the graphFrame
 */
public class GraphFrame extends JFrame implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Initialises the GraphFrame
     */
    public void initialiseGraphFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo (null); // Center on screen.
        this.setVisible(true);
        this.setDefaultLookAndFeelDecorated(true);
    }    
}