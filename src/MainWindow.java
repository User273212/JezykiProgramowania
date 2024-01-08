import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
private JSlider slider;
private int size;
    public MainWindow(){
        setTitle("Flying Circles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 600);

//        JPanel circlePanel = new JPanel();
        Circles circles = new Circles();

        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, size);

        panel.add(slider);

//        circlePanel.add(circles);
        add(circles, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }
}
