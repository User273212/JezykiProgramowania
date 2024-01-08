import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Shape;

class Circles extends JPanel {
    private int size = 50;
    private Shape selectedShape;
    private int index;
   // private final Ellipse2D ellipse;

    private List<Shape> shapeList = new ArrayList<>();
    Circles(int size) {

     //   ellipse = new Ellipse2D.Double(300, 100, this.size, this.size);
      //  shapeList.add(ellipse);
      //  addShape(ellipse);

        addMouseListener(new MouseAdapter() {
            private int size = Circles.this.size;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(selectShape(e.getPoint())) {
                    repaint();
                } else {
                    shapeList.add(new Ellipse2D.Double(e.getX(), e.getY(), size, size));
                    repaint();
                }
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

    public List<Shape> getShapelist(){
        return shapeList;
    }

    private void addShape(Shape shape) {
        if (selectedShape == null) {
            selectedShape = shape;
        }
        repaint();
    }

    private boolean selectShape(Point clickPoint) {
//        if (ellipse.contains(clickPoint)) {
//            selectedShape = ellipse;
//            return true;
//        }
//        return false;
        for(Shape shape : shapeList)
            if (shape.contains(clickPoint)) {
                selectedShape = shapeList.get(index);
                index = shapeList.indexOf(shape);
                return true;
            }
            return false;
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
        int next = 0;
        while (selectedShape == null) {
            selectedShape = shapeList.get(0);
            if (selectedShape != null) {
                selectedShape = shapeList.get(next);
                next++;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);

        for(Shape shape : shapeList)
            g2d.draw(shape);

        // Podświetl zaznaczony kształt
        g2d.setColor(Color.RED);
        if (selectedShape != null) {
            g2d.draw(shapeList.get(index));
        }
    }
}

