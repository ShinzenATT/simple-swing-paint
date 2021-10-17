import java.awt.*;
import java.util.Objects;

public class Shape {
    final int x, y, width, height;
    final Color color;
    final String type;

    public Shape(int x, int y, int width, int height, Color color, String type) {
        this.x = x;
        this.y = y;
        this.color = color;

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

    public Shape(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        width = 10;
        height = 0;
        type = "dot";
    }



    public void fillShape(Graphics g){
        g.setColor(color);
        switch (type) {
            case "rectangle" -> g.fillRect(x, y, width, height);
            case "oval" -> g.fillOval(x, y, width, height);
            case "dot" -> g.fillOval(x, y, 10, 10);
        }
    }
}
