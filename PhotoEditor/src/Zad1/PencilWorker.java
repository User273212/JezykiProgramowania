package Zad1;

import javax.swing.*;
import java.awt.*;

public class PencilWorker extends SwingWorker<Void, Void> {
    private PencilDrawing pencil;
    private int x, y;
    private DrawingPanel drawingPanel;

    PencilWorker(PencilDrawing pencil, int x, int y, DrawingPanel drawingPanel) {
        this.pencil = pencil;
        this.x = x;
        this.y = y;
        this.drawingPanel = drawingPanel;
    }

    @Override
    protected Void doInBackground() {
        pencil.draw((Graphics2D) drawingPanel.getGraphics(), x, y);
        return null;
    }
}