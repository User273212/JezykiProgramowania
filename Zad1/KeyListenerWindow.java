package Zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListenerWindow extends JFrame {

    private final JLabel text;

    public KeyListenerWindow() {
        text = new JLabel("Coordinates: 000:000");
        init();

        text.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new BorderLayout());
        add(text, BorderLayout.NORTH);
        add(new MouseListenerPanel(text), BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    text.setText("Coordinates: 000:000");
            }
        });

        setVisible(true);
    }

    private void init() {
        setTitle("Mouse and keyboard");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}
