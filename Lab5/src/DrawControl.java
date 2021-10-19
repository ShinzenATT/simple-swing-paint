import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawControl extends JFrame {

    public DrawControl(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(670,400);
        setLocation(50,50);
        setTitle("Drawing Program");
        DrawView dv = new DrawView();
        DrawModel dm = new DrawModel();
        dv.addActionListeners(
                e -> { dm.setColor(Color.BLACK); dv.setStatus(dm.generateStatus()); },
                e -> { dm.setColor(Color.RED); dv.setStatus(dm.generateStatus()); },
                e -> { dm.setColor(Color.GREEN); dv.setStatus(dm.generateStatus()); },
                e -> { dm.setMode("dot"); dv.setStatus(dm.generateStatus()); },
                e -> { dm.setMode("oval"); dv.setStatus(dm.generateStatus()); },
                e -> { dm.setMode("rectangle"); dv.setStatus(dm.generateStatus()); },
                e -> dv.removeLastShape(),
                e -> dm.saveShapes(dv.getDrawHistory()),
                e -> {
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

    public static void main(String[] args) {
        DrawControl dc = new DrawControl();
    }
}