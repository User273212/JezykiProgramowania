package Zad1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class GUI extends JFrame {

    private DrawingPanel drawingPanel;
    private Tools tools;
    private JMenu menu;
    private JMenuBar menuBar;

    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
   // private JColorChooser colorChooser;
    private Color drawingColor;
    private Color backgroundColor;

    public GUI() {
        setTitle("PhotoDrawingEditor Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 600);

        addMenu();

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

       // colorChooser = new JColorChooser(Color.BLACK);


        // Create toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        // Create DrawingPanel instance
        tools = new Tools();
        drawingPanel = new DrawingPanel(tools.getClicked());

        JButton drawingColorButton = new JButton("Choose drawing Color");

        drawingColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingColor = JColorChooser.showDialog(GUI.this, "Choose Color", Color.BLACK);
                System.out.println("drawingColor: " + drawingColor);
                // System.out.println("drawingColor: " + drawingColor);
                drawingPanel.setDrawingColor(drawingColor);
            }
        });

        JButton backgroundColorButton = new JButton("Choose background Color");

        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundColor = JColorChooser.showDialog(GUI.this, "Choose background Color", Color.WHITE);
                drawingPanel.setBackground(backgroundColor);
            }
        });

        JButton clearButton = new JButton("Clear");

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clearDrawing();
            }
        });

        // Add buttons to the toolbar

        toolbar.add(drawingColorButton);
        toolbar.add(backgroundColorButton);
        toolbar.add(clearButton);

        toolbar.setBackground(Color.LIGHT_GRAY);


        tools.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(drawingPanel, BorderLayout.CENTER);
        getContentPane().add(tools, BorderLayout.WEST);


        // Add toolbar to the frame
        getContentPane().add(toolbar, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addMenu(){
        menu = new JMenu("File");
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        menu.add(newFile);
        menu.add(openFile);
        menu.add(saveFile);

        menuBar = new JMenuBar();

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void newFile(){
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to start a new file? Any unsaved changes will be lost.");
        if(confirm == JOptionPane.YES_OPTION)
            drawingPanel.clearDrawing();
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Set a file filter for .png files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(filter);

        int userResponse = fileChooser.showOpenDialog(null);

        if (userResponse == JFileChooser.APPROVE_OPTION) {

            drawingPanel.clearDrawing();

            File file = fileChooser.getSelectedFile();

            try {
                // Read the image from the file
                BufferedImage image = ImageIO.read(file);

                // Set the image as the content of the DrawingPanel
                drawingPanel.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Set a file filter for .png files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(filter);

        int userResponse = fileChooser.showSaveDialog(null);

        if (userResponse == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Ensure the file has a .png extension
            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getParentFile(), file.getName() + ".png");
            }

            try {
                // Create an image of the DrawingPanel
                BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                drawingPanel.paint(g);
                g.dispose();

                // Save the image to the selected file
                ImageIO.write(image, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
