import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

class Circles extends JPanel {

    private Shape selectedShape;
    private final Ellipse2D ellipse;
    private int size = 50;

    Circles() {

        ellipse = new Ellipse2D.Double(300, 100, size, size);
        addShape(ellipse);

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

    public void setSize(int size){
        this.size = size;
    }

    private void addShape(Shape shape) {
        if (selectedShape == null) {
            selectedShape = shape;
        }
        repaint();
    }

    private void selectShape(Point clickPoint) {
        if (ellipse.contains(clickPoint)) {
            selectedShape = ellipse;
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
       if (shape instanceof Ellipse2D ellipse) {
            double newX = ellipse.getX() + deltaX;
            double newY = ellipse.getY() + deltaY;

            if (isWithinBounds(newX, newY, ellipse.getWidth(), ellipse.getHeight())) {
                ellipse.setFrame(newX, newY, ellipse.getWidth(), ellipse.getHeight());
            }
        }
    }

    private boolean isWithinBounds(double x, double y, double width, double height) {
        return x >= 0 && x + width <= getWidth() && y >= 0 && y + height <= getHeight();
    }

    private void toggleShape() {
         if (selectedShape == null) {
            selectedShape = ellipse;
        } else {
            selectedShape = ellipse;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.draw(ellipse);

        // Podświetl zaznaczony kształt
        g2d.setColor(Color.RED);
        if (selectedShape != null) {
            g2d.draw(selectedShape);
        }
    }
}

