import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An object that represents a shape such an oval and a rectangle. This class also keeps track of size, position and color as well as handles the rendering.
 */
public class Shape implements Serializable {
    final int x, y, width, height;
    final Color color;
    final String type;

    /**
     * Create a shape object
     * @param x The horizontal position
     * @param y The vertical position relative to the top
     * @param width The width of the shape, is ignored when the shape is a dot
     * @param height The height of the shape, is ignored when the shape is a dot
     * @param color The color that the shape shall be filled with
     * @param type The type of geometry that the shape shall be rendered as. Only accepts the values "dot", "oval" and "rectangle".
     */
    public Shape(int x, int y, int width, int height, Color color, String type) {
        this.x = x;
        this.y = y;
        this.color = color;

        // The dot shape always has the radius of 10
        if(type.equals("dot")){
            this.width = 10;
            this.height = 0;

        } else {
            this.width = width;
            this.height = height;
        }
        if(!Objects.equals(type, "rectangle") && !Objects.equals(type, "oval") && !Objects.equals(type, "dot")){
            throw new IllegalArgumentException();
        }
        this.type = type;
    }

    /**
     * This constructor creates a dot shape with a radius of 10 pixels.
     * @param x The horizontal position
     * @param y The vertical position relative to the top
     * @param color The color that the dot shall be filled with
     * @see #Shape(int, int, int, int, Color, String) The general shape constructor
     */
    public Shape(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        width = 10;
        height = 0;
        type = "dot";
    }

    /**
     * Renders the shape in accordance to its parameters to an external canvas/panel
     * @param g The renderer that the shape shall be rendered to
     */
    public void fillShape(Graphics g){
        g.setColor(color);
        switch (type) {
            case "rectangle" -> g.fillRect(x, y, width, height);
            case "oval" -> g.fillOval(x, y, width, height);
            case "dot" -> g.fillOval(x, y, 10, 10);
        }
    }
}
