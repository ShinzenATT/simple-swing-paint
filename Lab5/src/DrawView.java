import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DrawView extends JPanel {
    private final JTextArea status;

    public DrawView(ActionListener ... handlers){
        super(new BorderLayout());
        if(handlers.length < 9){
            throw new IllegalArgumentException();
        }

        JPanel btns = new JPanel(new FlowLayout());

        JButton blackbtn = new JButton("Black");
        blackbtn.addActionListener(handlers[0]);
        btns.add(blackbtn);

        JButton redbtn = new JButton("Red");
        redbtn.addActionListener(handlers[1]);
        btns.add(redbtn);

        JButton greenbtn = new JButton("Green");
        greenbtn.addActionListener(handlers[2]);
        btns.add(greenbtn);

        JButton dotbtn = new JButton("Dot");
        dotbtn.addActionListener(handlers[3]);
        btns.add(dotbtn);

        JButton ovalbtn = new JButton("Oval");
        ovalbtn.addActionListener(handlers[4]);
        btns.add(ovalbtn);

        JButton rectbtn = new JButton("Rect");
        rectbtn.addActionListener(handlers[5]);
        btns.add(rectbtn);

        JButton undobtn = new JButton("Undo");
        undobtn.addActionListener(handlers[6]);
        btns.add(undobtn);

        JButton savebtn = new JButton("Save");
        savebtn.addActionListener(handlers[7]);
        btns.add(savebtn);

        JButton loadbtn = new JButton("Load");
        loadbtn.addActionListener(handlers[8]);
        btns.add(loadbtn);

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
}
