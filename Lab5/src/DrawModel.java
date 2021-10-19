import javax.swing.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DrawModel {
    private Color currentColor = Color.BLACK;
    private String mode = "dot";
    private final int[] initialMousePos = { 0, 0 };

    public void setColor(Color color){
        currentColor = color;
        generateStatus();
    }

    public void setMode(String mode){
        if(!mode.equals("dot") && !mode.equals("oval") && !mode.equals("rectangle")){
            throw new IllegalArgumentException();
        }
        this.mode = mode;
    }

    public String generateStatus(){
        return "Mode: " + mode + " using " + currentColor;
    }

    public void setMouseAnchor(int x, int y){
        initialMousePos[0] = x;
        initialMousePos[1] = y;
    }

    public Shape generateShape(int x, int y){
        int rx = initialMousePos[0];
        int ry = initialMousePos[1];
        int width =  x - initialMousePos[0];
        int height = y - initialMousePos[1];
        if(width < 0 && mode != "dot"){
            width *= -1;
            rx  = x;
        }
        if(height < 0 && mode != "dot"){
            height *= -1;
            ry = y;
        }
        return new Shape(rx, ry - 40, width, height, currentColor, mode);
    }

    public void saveShapes(ArrayList<Shape> list){
        try{
            FileOutputStream output = new FileOutputStream("save.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            System.out.println(list.toString());
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch(Exception x){
            System.out.println("Save failed because of : " + x);
        }
    }

    public ArrayList<Shape> loadShapes(){
        try{
            FileInputStream input = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            ArrayList<Shape> dv = (ArrayList<Shape>) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(dv.toString());
            return dv;
        }catch(Exception x){
            System.out.println("Load failed because of : " + x);
            return null;
        }
    }

}
