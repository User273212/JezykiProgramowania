package Zad1;

import javax.swing.*;
import java.awt.*;

public class RubberWorker extends SwingWorker<Void, Void> {
    private Rubber rubber;
    private int x, y;
    private DrawingPanel drawingPanel;

    RubberWorker(Rubber rubber, int x, int y, DrawingPanel drawingPanel) {
        this.rubber = rubber;
        this.x = x;
        this.y = y;
        this.drawingPanel = drawingPanel;
    }

    @Override
    protected Void doInBackground() {
        rubber.draw((Graphics2D) drawingPanel.getGraphics(), x, y);
        return null;
    }
}