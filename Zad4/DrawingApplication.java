package Zad4;

import javax.swing.*;

public class DrawingApplication extends JFrame {

    DrawingApplication() {
        setTitle("Interactive Drawing App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingApplication());
    }
}