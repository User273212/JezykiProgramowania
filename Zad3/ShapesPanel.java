package Zad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapesPanel extends JPanel {

    private final Map<Shape, Long> shapesMap;
    private long lastClickTime;
    private Shape lastClickedShape;

    ShapesPanel() {
        shapesMap = new HashMap<>();

        // Dodaj trojkat, kwadrat, kolo i prostokat na start
        shapesMap.put(new Rectangle2D.Double(200, 100, 50, 50), System.currentTimeMillis());
        shapesMap.put(new Ellipse2D.Double(300, 100, 50, 50), System.currentTimeMillis());
        shapesMap.put(new Rectangle2D.Double(100, 200, 80, 40), System.currentTimeMillis());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Shape shape : shapesMap.keySet()) {
                    if (shape.contains(e.getPoint())) {
                        if (lastClickedShape == null || !shape.equals(lastClickedShape)) {
                            // pierwsze kliknięcie
                            lastClickedShape = shape;
                            lastClickTime = System.currentTimeMillis();
                            System.out.println("Pierwsze kliknięcie na " + getShapeType(shape) + "!");
                        } else {
                            // drugie kliknięcie
                            long elapsedTime = System.currentTimeMillis() - lastClickTime;
                            double elapsedTimeInSeconds = elapsedTime / 1000.0; // zamiana na sekundy
                            System.out.println("Czas między kliknięciami: " + elapsedTimeInSeconds + " s");
                            moveShape(shape);
                            repaint();
                            shapesMap.put(shape, System.currentTimeMillis());
                            lastClickedShape = null; //reset dla kolejnych kliknięć
                        }
                        return;
                    }
                }
            }
        });
    }

    private void moveShape(Shape shape) {
        //figury nie wychodza poza okno
        int randomX = new Random().nextInt(getWidth() - (int) shape.getBounds().getWidth());
        int randomY = new Random().nextInt(getHeight() - (int) shape.getBounds().getHeight());

        if (shape instanceof Rectangle2D) {
            ((Rectangle2D) shape).setRect(randomX, randomY, shape.getBounds().getWidth(), shape.getBounds().getHeight());
        } else if (shape instanceof Ellipse2D) {
            ((Ellipse2D) shape).setFrame(randomX, randomY, shape.getBounds().getWidth(), shape.getBounds().getHeight());
        }
    }

    private String getShapeType(Shape shape) {
        if (shape instanceof Rectangle2D) {
            return "prostokąt";
        } else if (shape instanceof Ellipse2D) {
            return "elipsa";
        } else {
            return "nieznany kształt";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape shape : shapesMap.keySet()) {
            g2d.draw(shape);
        }
    }
}