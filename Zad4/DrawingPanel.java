package Zad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Polygon;

class DrawingPanel extends JPanel {

    private Shape selectedShape;
    private final Rectangle2D rectangle;
    private final Ellipse2D ellipse;
    private final Polygon triangle;

    DrawingPanel() {

        rectangle = new Rectangle2D.Double(200, 100, 50, 50);
        ellipse = new Ellipse2D.Double(300, 100, 50, 50);

        int[] xPoints = {50, 100, 150};
        int[] yPoints = {150, 80, 150};
        int nPoints = 3;

        triangle = new Polygon(xPoints, yPoints, nPoints);

        addShape(rectangle);
        addShape(ellipse);
        addShape(triangle);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectShape(e.getPoint());
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
                repaint();
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void addShape(Shape shape) {
        if (selectedShape == null) {
            selectedShape = shape;
        }
        repaint();
    }

    private void selectShape(Point clickPoint) {
        if (rectangle.contains(clickPoint)) {
            selectedShape = rectangle;
        } else if (ellipse.contains(clickPoint)) {
            selectedShape = ellipse;
        } else if (triangle.contains(clickPoint.x, clickPoint.y)) {
            selectedShape = triangle;
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int step = 10;

        switch (keyCode) {
            case KeyEvent.VK_UP -> moveShape(selectedShape, 0, -step);
            case KeyEvent.VK_DOWN -> moveShape(selectedShape, 0, step);
            case KeyEvent.VK_LEFT -> moveShape(selectedShape, -step, 0);
            case KeyEvent.VK_RIGHT -> moveShape(selectedShape, step, 0);
            case KeyEvent.VK_SPACE -> toggleShape();
        }

        repaint();
    }

    private void moveShape(Shape shape, int deltaX, int deltaY) {
        if (shape instanceof Rectangle2D rectangle) {
            double newX = rectangle.getX() + deltaX;
            double newY = rectangle.getY() + deltaY;

            if (isWithinBounds(newX, newY, rectangle.getWidth(), rectangle.getHeight())) {
                rectangle.setRect(newX, newY, rectangle.getWidth(), rectangle.getHeight());
            }
        } else if (shape instanceof Ellipse2D ellipse) {
            double newX = ellipse.getX() + deltaX;
            double newY = ellipse.getY() + deltaY;

            if (isWithinBounds(newX, newY, ellipse.getWidth(), ellipse.getHeight())) {
                ellipse.setFrame(newX, newY, ellipse.getWidth(), ellipse.getHeight());
            }
        } else if (shape instanceof Polygon polygon) {

            //Tworzy kopie oryginalnego trojkata
            Polygon newTriangle = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);

            //przesuniecie do skopiowanego trójkąta
            for (int i = 0; i < newTriangle.npoints; i++) {
                newTriangle.xpoints[i] += deltaX;
                newTriangle.ypoints[i] += deltaY;
            }

            //sprawdzenie, czy nowa pozycja mieści się w granicach panelu
            if (isWithinBounds(newTriangle.getBounds())) {
                for (int i = 0; i < polygon.npoints; i++) {
                    polygon.xpoints[i] += deltaX;
                    polygon.ypoints[i] += deltaY;
                }
            }
        }
    }

    private boolean isWithinBounds(double x, double y, double width, double height) {
        return x >= 0 && x + width <= getWidth() && y >= 0 && y + height <= getHeight();
    }


    private boolean isWithinBounds(Rectangle2D bounds) {
        return bounds.getMinX() >= 0 && bounds.getMaxX() <= getWidth() && bounds.getMinY() >= 0 && bounds.getMaxY() <= getHeight();
    }

    private void toggleShape() {
        if (selectedShape == rectangle) {
            selectedShape = ellipse;
        } else if (selectedShape == ellipse) {
            selectedShape = triangle;
        } else {
            selectedShape = rectangle;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);

        g2d.setColor(Color.BLUE);
        g2d.draw(ellipse);

        g2d.setColor(Color.GREEN);
        g2d.drawPolygon(triangle);

        // Podświetl zaznaczony kształt
        g2d.setColor(Color.RED);
        if (selectedShape != null) {
            g2d.draw(selectedShape);
        }
    }
}


