import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class contains the data for the drawing program such as mode, color and mouseAnchor. This class also can generate {@link Shape} objects.
 */
public class DrawModel {
    private Color currentColor = Color.BLACK;
    private String mode = "dot";
    private final int[] initialMousePos = { 0, 0 };

    /**
     * This method sets the current color mode which impacts future generated shapes.
     * @param color A color that the future shapes will be filled with
     */
    public void setColor(Color color){
        currentColor = color;
        generateStatus();
    }

    /**
     * Sets the mode for future shape types
     * @param mode A shape type that is either "dot", "oval" or "rectangle".
     * @throws IllegalArgumentException when the mode argument is not the same as the following values: "dot", "oval" or "rectangle".
     */
    public void setMode(String mode){
        if(!mode.equals("dot") && !mode.equals("oval") && !mode.equals("rectangle")){
            throw new IllegalArgumentException();
        }
        this.mode = mode;
    }

    /**
     * Generates a humanly readable string  that shows the current status of the mode and color variables
     * @return A generated string about the mode and color variable
     */
    public String generateStatus(){
        return "Mode: " + mode + " using " + currentColor;
    }

    /**
     * A setter for the initialMousePos variable which saves mouse coordinates as reference for generating future shape dimensions
     * @param x The horizontal coordinate
     * @param y The vertical coordinate which is inversed (starts at the top) compared traditional graphs
     */
    public void setMouseAnchor(int x, int y){
        initialMousePos[0] = x;
        initialMousePos[1] = y;
    }

    /**
     * Generates a shape with accordance to the current mode and color while calculating shape dimensions by comparing the mouse coordinates to the anchor
     * @param x The horizontal coordinate
     * @param y The vertical coordinate which is inversed (starts at the top) compared traditional graphs
     * @return A generated {@link Shape} object based on the mode, color and mouse coordinates
     */
    public Shape generateShape(int x, int y){
        int rx = initialMousePos[0];
        int ry = initialMousePos[1];
        int width =  x - initialMousePos[0];
        int height = y - initialMousePos[1];
        // Switch the values if they have negative length
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

    /**
     * Accepts a ArrayList that is saved to the "save.txt" file for later retrieval
     * @param list An {@link ArrayList} that contains {@link Shape} objects
     */
    public void saveShapes(ArrayList<Shape> list){
        try{
            FileOutputStream output = new FileOutputStream("save.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch(Exception x){
            System.out.println("Save failed because of : " + x);
        }
    }

    /**
     * Loads and parses a ArrayList from the "save.txt" file that gets returned
     * @return A {@link ArrayList} that contains {@link Shape} objects
     */
    public ArrayList<Shape> loadShapes(){
        try{
            FileInputStream input = new FileInputStream("save.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            ArrayList<Shape> list = (ArrayList<Shape>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        }catch(Exception x){
            System.out.println("Load failed because of : " + x);
            return null;
        }
    }

}
