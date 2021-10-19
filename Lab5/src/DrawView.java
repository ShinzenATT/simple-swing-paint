import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class for rendering all the elements in a window and saves any event listeners received. This class also extends JPanel.
 * @see JPanel
 */
public class DrawView extends JPanel implements Serializable {
    /**
     * This is an inline class that is used as a canvas and renders all the shapes from the toDraw list
     */
    private class CanvasPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            for (Shape shape : toDraw) {
                shape.fillShape(g);
            }
        }
    }

    private final JTextArea status;
    private final  JButton[] buttons;
    private final CanvasPanel canvas;
    private final ArrayList<Shape> toDraw = new ArrayList<Shape>();

    /**
     * This constructor creates and sets the properties for needed elements. It also attaches any provided {@link ActionListener}s to buttons.
     * @param handlers It's a group or an array of {@link ActionListener} handlers (maybe inputted as a lambda) that is later attached to 9 buttons.
     *                 This argument is optional, and it only uses a max of 9 values so any additional handlers will not be attached.
     *                 The handlers will be attached in the following buttons in order: "Black", "Red", "Green", "Dot", "Oval", "Rect", "Undo", "Save" & "Load".
     *                 Where "Black" takes the value with index 0 and "Load" takes the value with index 8.
     */
    public DrawView(ActionListener ... handlers){
        super(new BorderLayout());

        // Creates the 9 buttons
        JPanel btns = new JPanel(new FlowLayout());

        JButton blackbtn = new JButton("Black");
        btns.add(blackbtn);

        JButton redbtn = new JButton("Red");
        btns.add(redbtn);

        JButton greenbtn = new JButton("Green");
        btns.add(greenbtn);

        JButton dotbtn = new JButton("Dot");
        btns.add(dotbtn);

        JButton ovalbtn = new JButton("Oval");
        btns.add(ovalbtn);

        JButton rectbtn = new JButton("Rect");
        btns.add(rectbtn);

        JButton undobtn = new JButton("Undo");
        btns.add(undobtn);

        JButton savebtn = new JButton("Save");
        btns.add(savebtn);

        JButton loadbtn = new JButton("Load");
        btns.add(loadbtn);

        this.buttons = new JButton[]{ blackbtn, redbtn, greenbtn, dotbtn, ovalbtn, rectbtn, undobtn, savebtn, loadbtn };
        addActionListeners(handlers);

        btns.setBackground(Color.LIGHT_GRAY);
        add(btns, BorderLayout.NORTH);

        // Creates the status bar
        status = new JTextArea("  Test");
        status.setBackground(Color.LIGHT_GRAY);
        add(status, BorderLayout.SOUTH);

        // Creates the canvas area
        canvas = new CanvasPanel();
        canvas.setBackground(Color.WHITE);
        add(canvas);
    }

    /**
     * Updates the text in the status area
     * @param msg A messaged to be displayed in the status area
     */
    public void setStatus(String msg){
        status.setText("  " + msg);
        repaint();
    }

    /**
     * Attaches event handlers to the 9 buttons.
     * @param handlers It's a group or an array of {@link ActionListener} handlers (maybe inputted as a lambda) that is later attached to 9 buttons.
     *                 This argument is optional (which results in nothing), and it only uses a max of 9 values so any additional handlers will not be attached.
     *                 The handlers will be attached in the following buttons in order: "Black", "Red", "Green", "Dot", "Oval", "Rect", "Undo", "Save" & "Load".
     *                 Where "Black" takes the value with index 0 and "Load" takes the value with index 8.
     */
    public void addActionListeners(ActionListener ... handlers){
        for(int i = 0; i < buttons.length && i < handlers.length; i++){
            buttons[i].addActionListener(handlers[i]);
        }
    }

    /**
     * Adds a shape to the draw history and rerenders all shapes including the new one
     * @param e A shape to be rendered
     */
    public void drawNewShape(Shape e){
        toDraw.add(e);
        repaint();
    }

    /**
     * Removes the latest added shape from the drawing history and rerenders the canvas
     */
    public void removeLastShape(){
        if(toDraw.size() > 0){
            toDraw.remove(toDraw.size() - 1);
            repaint();
        }
    }

    /**
     * @return An {@link ArrayList} of {@link Shape} objects as a snapshot of the current drawing history
     */
    public ArrayList<Shape> getDrawHistory(){
        return (ArrayList<Shape>) toDraw.clone();
    }

    /**
     * Clears the canvas and the drawing history
     */
    public void clearCanvas(){
        toDraw.removeAll(toDraw);
        repaint();
    }
}
