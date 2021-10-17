import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawView extends JPanel {
    private class CanvasPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            for(int i = 0; i < toDraw.size(); i++){
                toDraw.get(i).fillShape(g);
            }
        }
    }

    private final JTextArea status;
    private final  JButton[] buttons;
    private final CanvasPanel canvas;
    private final ArrayList<Shape> toDraw = new ArrayList<Shape>();

    public DrawView(ActionListener ... handlers){
        super(new BorderLayout());

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

        status = new JTextArea("  Test");
        status.setBackground(Color.LIGHT_GRAY);
        add(status, BorderLayout.SOUTH);

        canvas = new CanvasPanel();
        canvas.setBackground(Color.WHITE);
        add(canvas);
    }

    public void setStatus(String msg){
        status.setText("  " + msg);
        repaint();
    }

    public void addActionListeners(ActionListener ... handlers){
        for(int i = 0; i < buttons.length && i < handlers.length; i++){
            buttons[i].addActionListener(handlers[i]);
        }
    }

    public void drawNewShape(Shape e){
        toDraw.add(e);
        repaint();
    }

    public void removeLastShape(){
        if(toDraw.size() > 0){
            toDraw.remove(toDraw.size() - 1);
            repaint();
        }
    }
}
