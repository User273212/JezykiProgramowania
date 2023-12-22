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
    private List<Drawable> drawables = new ArrayList<>();
    private Map<String, Boolean> clicked;

    private DrawRect currentRect;
    private BufferedImage currentImage;
    private List<Point> rubberPoints = new ArrayList<>();
    private Color drawingColor;
    private Color backgroundColor;

    private PencilDrawing pencil;
    private Rubber rubber;

    public DrawingPanel(Map<String, Boolean> clicked) {
        super(true);
        this.clicked = clicked;

        setBackground(backgroundColor);

         pencil = new PencilDrawing();
         rubber = new Rubber();
        // rubber.setDrawingColor(getBackground());


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

                pencil.startDrawing(e.getX(), e.getY());
                drawables.add(pencil);
                PencilWorker pencilWorker = new PencilWorker(pencil, e.getX(), e.getY(), DrawingPanel.this);
                pencilWorker.execute();
                repaint();
            //    drawables.add(pencilWorker.getProgress(), this::paintComponent);

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("DrawButton") ) {
                            new PencilWorker(pencil, e.getX(), e.getY(), DrawingPanel.this).execute();
                            repaint();
                        }
                    }
                });

            }
            case "rectangle" -> {
                currentRect = new DrawRect(drawingColor);
                drawables.add(currentRect);
                currentRect.setStartPoint(e.getX(), e.getY());

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("rectangle")) {
                            new DrawRectWorker(currentRect, e.getX(), e.getY()).execute();
                            repaint();
                        }
                    }
                });

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (clicked.get("rectangle")) {
                            new DrawRectWorker(currentRect, e.getX(), e.getY()).execute();
                            repaint();
                        }
                    }
                });
            }

            case "rubber" -> {


                rubber.startDrawing(e.getX(), e.getY());
                drawables.add(rubber);
                RubberWorker rubberWorker = new RubberWorker(rubber, e.getX(), e.getY(), DrawingPanel.this);
                rubberWorker.execute();
                repaint();
                //    drawables.add(pencilWorker.getProgress(), this::paintComponent);

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (clicked.get("rubber") ) {
                            new RubberWorker(rubber, e.getX(), e.getY(), DrawingPanel.this).execute();
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
      //  pencilPoints.clear();
        pencil.clearPoints();
        rubber.clearPoints();
        drawables.clear();
        removeAll();
        currentImage = null;
        setBackground(Color.WHITE);
        repaint();
    }

    @Override
   synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        rubber.setDrawingColor(getBackground());


        Graphics2D g2d = (Graphics2D) g;

        if (currentImage != null) {
            g2d.drawImage(currentImage, 0, 0, this);
        }

        for (Drawable drawable : drawables) {
            drawable.draw(g2d);
            repaint();
        }
    }
}
