import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Shape;
import java.util.List;

public class MainWindow extends JFrame{
private JSlider slider;
private Circles circles;
private int size;
private List<Shape> shapeList;
    public MainWindow(){
        setTitle("Flying Circles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 600);

//        JPanel circlePanel = new JPanel();


        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 50);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size = slider.getValue();
            }
        });



       Circles circles = new Circles(size);
       circles.setSize(size);

       shapeList = circles.getShapelist();


        panel.add(slider);

//        circlePanel.add(circles);

        add(circles, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }
}
