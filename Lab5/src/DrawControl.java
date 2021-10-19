import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The controller class for the drawing program which also setups the necessary arguments for startup.
 * The constructor also setups {@link DrawView} and {@link DrawModel} along with starting the {@link JFrame} windows which this class also extends.
 * @see JFrame
 */
public class DrawControl extends JFrame {

    /**
     * This constructor setups the {@link JFrame} window and sets upp event handlers that bridge between {@link DrawView} and {@link DrawModel}
     * along with instantiating the respective classes.
     * @see ActionListener
     * @see MouseListener
     * @see MouseMotionListener
     */
    public DrawControl(){
        // Set the main windows properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(670,400);
        setLocation(50,50);
        setTitle("Drawing Program");

        // Initialize the main classes
        DrawView dv = new DrawView();
        DrawModel dm = new DrawModel();
        dv.addActionListeners(
                e -> { dm.setColor(Color.BLACK); dv.setStatus(dm.generateStatus()); },   // "Black" button
                e -> { dm.setColor(Color.RED); dv.setStatus(dm.generateStatus()); },     // "Red" button
                e -> { dm.setColor(Color.GREEN); dv.setStatus(dm.generateStatus()); },   // "Green" button
                e -> { dm.setMode("dot"); dv.setStatus(dm.generateStatus()); },          // "Dot" button
                e -> { dm.setMode("oval"); dv.setStatus(dm.generateStatus()); },         // "Oval" button
                e -> { dm.setMode("rectangle"); dv.setStatus(dm.generateStatus()); },    // "Rect" button
                e -> dv.removeLastShape(),                                               // "Undo" button
                e -> dm.saveShapes(dv.getDrawHistory()),                                 // "Save" button
                e -> {                                                                   // "Load" button:
                    ArrayList<Shape> list = dm.loadShapes();
                    if(list == null){
                        System.out.println("Load Failed");
                    }else{
                        dv.clearCanvas();
                        for (Shape shape : list) {
                            dv.drawNewShape(shape);
                        }
                    }
                }
        );

        dv.setStatus(dm.generateStatus());

        /**
         * This is an inline class that implements MouseListener and MouseMotionListener for use with generating previews trough DrawModel and rendering them in DrawView
         */
        class MouseHandler implements MouseListener, MouseMotionListener{
            @Override
            public void mousePressed(MouseEvent arg0) {
                dm.setMouseAnchor(arg0.getX(), arg0.getY());
                Shape s = dm.generateShape(arg0.getX() + 10, arg0.getY() + 10);
                dv.drawNewShape(s);
            }

            
            @Override
            public void mouseDragged(MouseEvent arg0) {
                dv.removeLastShape();
                Shape s = dm.generateShape(arg0.getX(), arg0.getY());
                dv.drawNewShape(s);
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mouseClicked(MouseEvent arg0) {}
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            @Override
            public void mouseMoved(MouseEvent arg0) {}
        }
        MouseHandler mh = new MouseHandler();
        dv.addMouseListener(mh);
        dv.addMouseMotionListener(mh);

        add(dv);
        setVisible(true);
    }

    /** Starts a {@link DrawControl} instance which in turn starts {@link DrawView} and {@link DrawModel}. */
    public static void main(String[] args) {
        DrawControl dc = new DrawControl();
    }
}