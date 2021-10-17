import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class DrawControl extends JFrame {

    public DrawControl(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(670,400);
        setLocation(50,50);
        setTitle("Drawing Program");
        DrawView dv = new DrawView();
        DrawModel dm = new DrawModel(dv);
        dv.addActionListeners(
                e -> dm.setColor(Color.BLACK),
                e -> dm.setColor(Color.RED),
                e -> dm.setColor(Color.GREEN),
                e -> dm.setMode("dot"),
                e -> dm.setMode("oval"),
                e -> dm.setMode("rectangle"),
                e -> dm.undoDraw(),
                e -> System.out.println("save"),
                e -> System.out.println("load")
        );

        class MouseHandler implements MouseListener, MouseMotionListener{
            @Override
            public void mousePressed(MouseEvent arg0) {
                dm.startPreview(arg0.getX(), arg0.getY());
                
            }

            
            @Override
            public void mouseDragged(MouseEvent arg0) {
                dm.updatePreview(arg0.getX(), arg0.getY());
                
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

    public static void main(String[] args) {
        DrawControl dc = new DrawControl();
    }
}