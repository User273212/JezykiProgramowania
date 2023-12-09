package Zad1;

import java.awt.*;
import java.util.List;

public class PencilDrawing implements Drawable, setDrawingColor{

    private final List<Point> points;
    private Color color;

    public PencilDrawing(List<Point> points, Color color){
        this.points = points;
        this.color = color;
    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
            for (Point point : points) {
                g2d.fillRect(point.x, point.y, 10, 10);
        }
    }


    @Override
    public void setDrawingColor(Color drawingColor) {
        color = drawingColor;
    }
}