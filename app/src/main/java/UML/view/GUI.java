package UML.view;

import UML.controller.GUIController;

import javax.swing.*;
import java.awt.*;

public class GUI {

    GUIController guiCtr = new GUIController();

    JFrame mainFrame;
    //JPanel topPanel;
    JPanel mainPanel;
    //JPanel bottomPanel;
    JMenuBar mainBar;
    JButton saveButton;
    JButton loadButton;

    public void GUI_view() { 
        mainFrame = new JFrame ("UML Editor");
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.gray);
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 650);
        //mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setLocationRelativeTo(null); 

        //Initialize the Main Bar
        mainBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu createMenu = new JMenu("Create");
        JMenu deleteMenu = new JMenu("Delete");
        JMenu editMenu = new JMenu("Modify");
        JMenu listMenu = new JMenu("List");

        //File Menu Options
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        
        //Create Menu Options
        JMenuItem createClassMenuItem = new JMenuItem("Class");
        JMenuItem createMethodMenuItem = new JMenuItem("Method");
        JMenuItem createFieldMenuItem = new JMenuItem("Field");
        JMenuItem createRelationshipMenuItem = new JMenuItem("Relationship");
        createMenu.add(createClassMenuItem);
        createMenu.add(createMethodMenuItem);
        createMenu.add(createFieldMenuItem);
        createMenu.add(createRelationshipMenuItem);

        //Delete Menu Options
        JMenuItem deleteClassMenuItem = new JMenuItem("Class");
        JMenuItem deleteMethodMenuItem = new JMenuItem("Method");
        JMenuItem deleteFieldMenuItem = new JMenuItem("Field");
        JMenuItem deleteRelationshipMenuItem = new JMenuItem("Relationship");
        deleteMenu.add(deleteClassMenuItem);
        deleteMenu.add(deleteMethodMenuItem);
        deleteMenu.add(deleteFieldMenuItem);
        deleteMenu.add(deleteRelationshipMenuItem);

        //Modify Menu Options
        JMenu editClassMenu = new JMenu("Class");
        JMenuItem editClassnameItem = new JMenuItem("Class Name");
        editClassMenu.add(editClassnameItem);
        JMenu editMethodMenu = new JMenu("Method ");
        JMenuItem editMethodnameItem = new JMenuItem("Method Name");
        JMenuItem editMethodReturnItem = new JMenuItem("Method Return Type");
        JMenuItem editMethodParamItem = new JMenuItem("Method Parameters");
        editMethodMenu.add(editMethodnameItem);
        editMethodMenu.add(editMethodReturnItem);
        editMethodMenu.add(editMethodParamItem);
        JMenu editFieldMenu = new JMenu("Field");
        JMenuItem editFieldnameItem = new JMenuItem("Field Name");
        JMenuItem editFieldTypeItem = new JMenuItem("Field Type");
        editFieldMenu.add(editFieldnameItem);
        editFieldMenu.add(editFieldTypeItem);
        editMenu.add(editClassMenu);
        editMenu.add(editMethodMenu);
        editMenu.add(editFieldMenu);

        mainBar.add(Box.createHorizontalGlue());
        mainBar.add(fileMenu);
        mainBar.add(createMenu);
        mainBar.add(deleteMenu);
        mainBar.add(editMenu);
        mainBar.add(Box.createRigidArea(new Dimension(580,35)));
        
        mainPanel.add(mainBar);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        
        //FILE LISTENER
        //Save button listener
        saveMenuItem.addActionListener(e -> 
            guiCtr.guiSaveCtr() );

        //Load button listener
        loadMenuItem.addActionListener(e -> 
            guiCtr.guiLoadCtr() );

        //Exit button listener
        exitMenuItem.addActionListener(e -> 
            mainFrame.dispose());

        //CREATE LISTENER
        //Create Class Button
        createClassMenuItem.addActionListener(e -> 
            guiCtr.guiCreateClassCtr() );
        //Create Method Button
        createMethodMenuItem.addActionListener(e -> 
            guiCtr.createMethodCtr() );
        //Create Field Button
        createFieldMenuItem.addActionListener(e -> 
            guiCtr.createFieldCtr() );
        //Create Relationship Button
        createRelationshipMenuItem.addActionListener(e -> 
            guiCtr.createRelationshipCtr() );

        //DELETE LISTENER
        //Delete Class Button
        deleteClassMenuItem.addActionListener(e ->
            guiCtr.deleteClassCtr() );
        //Delete Class Button
        deleteMethodMenuItem.addActionListener(e ->
            guiCtr.deleteMethodCtr() );
        //Delete Class Button
        deleteFieldMenuItem.addActionListener(e ->
            guiCtr.deleteFieldCtr() );
        //Delete Class Button
        deleteRelationshipMenuItem.addActionListener(e ->
            guiCtr.deleteRelationshipCtr() );

        //MODIFY LISTENER
        //Modify Class Name Button
        editClassnameItem.addActionListener(e ->
            guiCtr.renameClassCtr() );
        //Modify Method Name Button
        editMethodnameItem.addActionListener(e -> 
            guiCtr.renameMethodCtr() );
        //Modify Method Return Type
        editMethodReturnItem.addActionListener(e ->
            guiCtr.editMethodReturnCtr() );
        //Modify Method Params
        editMethodParamItem.addActionListener(e ->
            guiCtr.editMethodParams() );
        //Modify Field Name
        editFieldnameItem.addActionListener(e -> 
            guiCtr.renameFieldCtr() );
        //Modify Field Type
        editFieldTypeItem.addActionListener(e ->
            guiCtr.editFieldType() );


    }    


}


