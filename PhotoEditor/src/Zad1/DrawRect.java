package Zad1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawRect implements Drawable, setDrawingColor {
    private int startX, startY, endX, endY;
    private Color color;

    public DrawRect(Color color) {
        this.color = color;
    }

    public void setStartPoint(int x, int y) {
        startX = x;
        startY = y;
    }

    public void setEndPoint(int x, int y) {
        endX = x;
        endY = y;
    }

    @Override
    public void draw(Graphics g) {
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x, y, width, height);
    }

    @Override
    public void setDrawingColor(Color drawingColor) {
        color = drawingColor;
    }
}