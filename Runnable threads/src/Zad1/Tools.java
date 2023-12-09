package Zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Tools extends JPanel{

    private JButton rectangle;
    private JButton rubber;
    private JButton drawButton;
    private JPanel toolsPanel;
    private Map<String,Boolean> clicked;

    protected static boolean isDrawing;
    public Tools() {

//        DrawingPanel drawingPanel = new DrawingPanel(null);
//        drawingPanel.isDrawing = false;

        toolsPanel = new JPanel(new GridLayout(7,1, 20, 20));
        toolsPanel.setBackground(Color.LIGHT_GRAY);

        drawButton = new JButton("Draw");
        rectangle = new JButton(assignIcon("rectangle"));
        rubber = new JButton(assignIcon("rubber"));

        clicked = new HashMap<>();

       // DrawingPanel drawingPanel = new DrawingPanel(null);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               initClicked("DrawButton");
            }
        });

        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initClicked("rectangle");
            }
        });


        rubber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initClicked("rubber");
            }
        });


        toolsPanel.add(drawButton);
        toolsPanel.add(new JLabel("Shapes"));
        toolsPanel.add(rectangle);
        toolsPanel.add(new JLabel("Tools"));
        toolsPanel.add(rubber);

        add(toolsPanel);

    }


    private ImageIcon assignIcon(String icon){
        return new ImageIcon(getClass().getResource("/Icons/" + icon + ".png"));
    }

    private void initClicked(String component){
      //  isDrawing = false;
        resetClicked();
        setClicked(component);
        System.out.println(clicked);
    }

    private void setClicked(String component){
        clicked.put(component, true);
    }

    private void resetClicked(){
        for (Map.Entry<String, Boolean> entry : clicked.entrySet()) {
            entry.setValue(false);
        }
    }


    public Map<String,Boolean> getClicked(){
        return clicked;
    }

//    public static boolean isDrawing() {
//        return isDrawing;
//    }
}
