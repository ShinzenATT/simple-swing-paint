import javax.swing.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DrawModel {
    private final DrawView drawView;
    private Color currentColor = Color.BLACK;
    private String mode = "dot";
    private final int[] initialMousePos = { 0, 0 };

    public DrawModel(DrawView drawView){
        this.drawView = drawView;
        updateStatus();
    }

    public void setColor(Color color){
        currentColor = color;
        updateStatus();
    }

    public void setMode(String mode){
        if(!mode.equals("dot") && !mode.equals("oval") && !mode.equals("rectangle")){
            throw new IllegalArgumentException();
        }
        this.mode = mode;
        updateStatus();
    }

    private void updateStatus(){
        drawView.setStatus("Mode: " + mode + " using " + currentColor);
    }

    public void undoDraw(){
        drawView.removeLastShape();
    }

    public void startPreview(int x, int y){
        initialMousePos[0] = x;
        initialMousePos[1] = y;
        drawView.drawNewShape(new Shape(x, y - 40, 10, 10, currentColor, mode));
    }

    public void updatePreview(int x, int y){
        drawView.removeLastShape();
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
        drawView.drawNewShape(new Shape(rx, ry - 40, width, height, currentColor, mode));
    }

    public void savePanel(DrawView dv){
        try{
            FileOutputStream output = new FileOutputStream("save.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(dv);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch(Exception x){
            System.out.println("Save failed because of : " + x);
        }
    }

    public DrawView loadPanel(){
        try{
            FileInputStream input = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            DrawView dv = (DrawView) objectInputStream.readObject();
            objectInputStream.close();
            return dv;
        }catch(Exception x){
            System.out.println("Load failed because of : " + x);
            return null;
        }
    }

}
