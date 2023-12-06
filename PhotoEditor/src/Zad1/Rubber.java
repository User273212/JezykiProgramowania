package Zad1;

import java.awt.*;
import java.util.List;

import static javax.swing.text.StyleConstants.getBackground;

public class Rubber implements Drawable{

//   private Point point;
    List<Point> points;
    private int rubberSize;
    Color color;
    public Rubber(List<Point> points, Color color){
       // this.point = point;
        this.points = points;
        this.color = color;
        setRubberSize(10);
    }



    public void setRubberSize(int size) {
        rubberSize = size;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
       // int eraserSize = 20;
        for (Point point : points)
            g2d.fillRect(point.x - rubberSize/2, point.y - rubberSize/2, rubberSize, rubberSize);
           // g2d.fillRect(point.x - eraserSize / 2, point.y - eraserSize / 2, eraserSize, eraserSize);
    }
}
