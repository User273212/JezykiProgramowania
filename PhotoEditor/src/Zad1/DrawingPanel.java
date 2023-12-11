package Zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrawingPanel extends JPanel implements setDrawingColor, setBackgroundColor{

    private final List<Point> pencilPoints = new ArrayList<>();
    private Point currentPencilPoint;
    private List<Drawable> drawables = new ArrayList<>();
    private Map<String, Boolean> clicked;

    private DrawRect currentRect;
    private BufferedImage currentImage;
    private Point currentRubberPoint;
    private List<Point> rubberPoints = new ArrayList<>();
    private Color drawingColor;
    private Color backgroundColor;

    public DrawingPanel(Map<String, Boolean> clicked) {
        this.clicked = clicked;

        setBackground(backgroundColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    @Override
    public void setDrawingColor(Color drawingColor) {
        this.drawingColor = drawingColor;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private class DrawButtonWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() {
            while (clicked.get("DrawButton")) {
                SwingUtilities.invokeLater(() -> repaint());
                try {
                    Thread.sleep(10);  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private class RectangleWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() {
            while (clicked.get("rectangle")) {
                SwingUtilities.invokeLater(() -> repaint());
                try {
                    Thread.sleep(10);  // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private class RubberWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() {
            while (clicked.get("rubber")) {
                SwingUtilities.invokeLater(() -> repaint());
                try {
                    Thread.sleep(10); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }



    private void buttonClicked(MouseEvent e) {
        String selectedDrawingShape = "";

for (Map.Entry<String, Boolean> entry : clicked.entrySet()) {
    if (entry.getValue()) {
        selectedDrawingShape = entry.getKey();
        break;
    }
}

        switch (selectedDrawingShape) {
            case "DrawButton" -> {
               // start thread
                DrawButtonWorker drawButtonWorker = new DrawButtonWorker();
                //execute thread
                drawButtonWorker.execute();

                currentPencilPoint = e.getPoint();
                pencilPoints.add(currentPencilPoint);
                drawables.add(new PencilDrawing(pencilPoints, drawingColor));
                repaint();

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("DrawButton") &&  currentPencilPoint != null) {
                            currentPencilPoint = e.getPoint();
                            pencilPoints.add(currentPencilPoint);
                            repaint();
                        }
                    }
                });

            }
            case "rectangle" -> {

                RectangleWorker rectangleWorker = new RectangleWorker();
                rectangleWorker.execute();

                System.out.println(e.getX() + " " + e.getY() + " " + clicked + " " + clicked.get("rectangle"));
                currentRect = new DrawRect(drawingColor);
                drawables.add(currentRect);
                currentRect.setStartPoint(e.getX(), e.getY());


                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("rectangle")) {
                            currentRect.setEndPoint(e.getX(), e.getY());
                            repaint();
                        }
                    }
                });

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (clicked.get("rectangle")) {
                            currentRect.setEndPoint(e.getX(), e.getY());
                            repaint();
                        }
                    }
                });


            }
            case "rubber" -> {

                //start thread
                RubberWorker rubberWorker = new RubberWorker();
                //execute thread
                rubberWorker.execute();

                currentRubberPoint = e.getPoint();
                rubberPoints.add(currentRubberPoint);
                drawables.add(new Rubber(rubberPoints, getBackground()));
                repaint();

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("rubber") &&  currentRubberPoint != null) {
                            currentRubberPoint = e.getPoint();
                            rubberPoints.add(currentRubberPoint);
                            repaint();
                        }
                    }
                });

            }
            default -> System.out.println("NONE");
        }
    }

    public void setImage(BufferedImage image) {
        this.currentImage = image;
        repaint();
    }

    public void clearDrawing() {
        pencilPoints.clear();
        drawables.clear();
        removeAll();
        currentImage = null;
        setBackground(Color.WHITE);
        repaint();
    }

    @Override
   synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (currentImage != null) {
            g2d.drawImage(currentImage, 0, 0, this);
        }

        for (Drawable drawable : drawables) {
            drawable.draw(g2d);
        }
    }
}
