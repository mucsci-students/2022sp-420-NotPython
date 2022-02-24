package UML.view;

import UML.controller.GUIController;

import javax.swing.*;
import java.awt.*;

public class GUI {
    
    //Define elements of the GUI
    //Frames
    private JFrame mainFrame;
    //Panels
    private JPanel mainPanel;
    private JToolBar menuBar;
    //Buttons
    private JButton createButton;
    private JButton deleteButton;
    private JButton renameButton;
    private JButton listButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;

    public void GUI_view() { 
        //Create Main Frame
        mainFrame = new JFrame ("UML Editor");
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 650);
        mainFrame.setLocationRelativeTo(null);

        //Create elements for main frame
        createButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        renameButton = new JButton("Rename");
        listButton = new JButton("List");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        exitButton = new JButton("Exit");

        //Add elements to Main Frame
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.gray);
        menuBar = new JToolBar("Main ToolBar");
        menuBar.setPreferredSize(new Dimension(500, 40));

        //Creating the toolbar
        createButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(createButton);
        menuBar.addSeparator(new Dimension(10,40));
        deleteButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(deleteButton);
        menuBar.addSeparator(new Dimension(10,40));
        renameButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(renameButton);
        menuBar.addSeparator(new Dimension(10,40));
        listButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(listButton);
        menuBar.addSeparator(new Dimension(10,40));
        saveButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(saveButton);
        menuBar.addSeparator(new Dimension(10,40));
        loadButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(loadButton);
        menuBar.addSeparator(new Dimension(10,40));
        exitButton.setPreferredSize(new Dimension(100, 40));
        menuBar.add(exitButton);

        mainPanel.add(menuBar);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    public JFrame getFrame(){
        return mainFrame;
    }


    public static void main (String[] args) {
        GUI display = new GUI();
        display.GUI_view();
    }


}
