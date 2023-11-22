import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//zadanie 1

public class ColorChangeApp extends JFrame {

    private final JTextField colorTextField;
    private final JButton changeColorButton;
    private final JPanel colorPanel;
    private final JPanel panel;

    public ColorChangeApp() {
        setTitle("Color Change App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        colorTextField = new JTextField(10);
        changeColorButton = new JButton("Change Color");
        colorPanel = new JPanel();
        panel = new JPanel(new GridLayout(3, 1, 10, 10));

        panel.add(colorTextField);
        panel.add(changeColorButton);
        panel.add(colorPanel);
        add(panel);

        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor();
            }
        });
    }

    private void changeColor() {
        String colorName = colorTextField.getText().toLowerCase();

        try {
            Color newColor = getColorByName(colorName);
            colorPanel.setBackground(newColor);
            colorTextField.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid color name. Please enter a valid color.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Color getColorByName(String colorName) {
        return switch (colorName) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            default -> throw new IllegalArgumentException("Invalid color name");
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColorChangeApp colorChangeApp = new ColorChangeApp();
            colorChangeApp.setVisible(true);
        });
    }
}
