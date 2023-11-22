package Zad3;


import javax.swing.*;

public class Application extends JFrame {

    Application() {
        setTitle("Interactive Shapes App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapesPanel shapesPanel = new ShapesPanel();
        add(shapesPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Application());
    }
}