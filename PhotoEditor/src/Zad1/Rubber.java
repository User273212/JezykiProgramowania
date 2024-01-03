package Zad1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rubber implements Drawable, setDrawingColor{

    private List<Point> points = new ArrayList<>();
    private Color color;

    void draw(Graphics2D g2d, int x, int y) {
        points.add(new Point(x, y));
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(color);
        if (points.size() > 1) {
            Point p1 = points.get(points.size() - 2);
            Point p2 = points.get(points.size() - 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    void startDrawing(int x, int y) {
        points.add(new Point(x, y));
    }


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(color);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public void setDrawingColor(Color drawingColor) {
          color = drawingColor;
    }
}
