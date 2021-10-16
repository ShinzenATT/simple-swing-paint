import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.*;

public class DrawControl extends JFrame {

    public DrawControl(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocation(50,50);
        setTitle("Drawing Program");
        DrawView dv = new DrawView(
                e -> System.out.println("black"),
                e -> System.out.println("red"),
                e -> System.out.println("green"),
                e -> System.out.println("dot"),
                e -> System.out.println("oval"),
                e -> System.out.println("rect"),
                e -> System.out.println("undo"),
                e -> System.out.println("save"),
                e -> System.out.println("load")
        );
        DrawModel dm = new DrawModel(dv);
        add(dv);
        setVisible(true);
    }

    public static void main(String[] args) {
        DrawControl dc = new DrawControl();
    }
}