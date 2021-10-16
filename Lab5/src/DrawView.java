import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DrawView extends JPanel {
    private final JTextArea status;
    private final  JButton[] buttons;

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
    }

    public void setStatus(String msg){
        status.setText(msg);
        repaint();
    }

    public void addActionListeners(ActionListener ... handlers){
        for(int i = 0; i < buttons.length && i < handlers.length; i++){
            buttons[i].addActionListener(handlers[i]);
        }
    }
}
