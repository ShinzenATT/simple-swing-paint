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
    // Is a pointer to a different list
    private final List<Shape> toDraw;

    /**
     * This constructor creates and sets the properties for needed elements. It also attaches any provided {@link ActionListener}s to buttons.
     * @param refList A pointer to another list of shapes that is to be drawn on the canvas
     * @param handlers It's a group or an array of {@link ActionListener} handlers (maybe inputted as a lambda) that is later attached to 9 buttons.
     *                 This argument is optional, and it only uses a max of 9 values so any additional handlers will not be attached.
     *                 The handlers will be attached in the following buttons in order: "Black", "Red", "Green", "Dot", "Oval", "Rect", "Undo", "Save" & "Load".
     *                 Where "Black" takes the value with index 0 and "Load" takes the value with index 8.
     */
    public DrawView(List<Shape> refList, ActionListener ... handlers){
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
        toDraw = refList;
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
}
