package UML.view;

import UML.controller.GUIController;

import javax.swing.*;
import java.awt.*;

public class GUI {
    
    public void GUI_view() { 
        //Create Main Frame
        JFrame mainFrame = new JFrame ("UML Editor");
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 650);
        mainFrame.setLocationRelativeTo(null);

        //Create elements for main frame
        JButton createButton = new JButton("Create");
        JButton deleteButton = new JButton("Delete");
        JButton renameButton = new JButton("Rename");
        JButton listButton = new JButton("List");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exitButton = new JButton("Exit");

        //Add elements to Main Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.gray);
        JToolBar menuBar = new JToolBar("Main ToolBar");
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

}
