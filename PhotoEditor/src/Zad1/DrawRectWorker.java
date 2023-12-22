package Zad1;

import javax.swing.*;

class DrawRectWorker extends SwingWorker<Void, Void> {
    private final DrawRect drawRect;
    private final int endX, endY;

    public DrawRectWorker(DrawRect drawRect, int endX, int endY) {
        this.drawRect = drawRect;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    protected Void doInBackground() {
        drawRect.setEndPoint(endX, endY);
        return null;
    }
}