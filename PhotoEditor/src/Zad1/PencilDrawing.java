package Zad1;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PencilDrawing implements Drawable, setDrawingColor {

    private List<Point> points = new ArrayList<>();
    private Color color;

    void draw(Graphics2D g2d, int x, int y) {
        points.add(new Point(x, y));
        if (points.size() > 1) {
            Point p1 = points.get(points.size() - 2);
            Point p2 = points.get(points.size() - 1);

            if (p1 != null && p2 != null) {
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    void startDrawing(int x, int y) {
        // Don't clear points, retain the drawn lines
        points.add(new Point(x, y));
    }

    void startDrawing() {
        points.add(null);
    }

    public void clearPoints() {
        points.clear();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(color);

        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);

            if (p1 != null && p2 != null) {
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    @Override
    public void setDrawingColor(Color drawingColor) {
        color = drawingColor;
    }
}
