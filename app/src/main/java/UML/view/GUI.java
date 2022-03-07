package UML.view;

import UML.controller.GUIController;
import UML.controller.Listing;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GUI {

    GUIController guiCtr = new GUIController();
    Listing lister = new Listing();

    JFrame mainFrame;
    JPanel mainPanel;
    JPanel statusBarPanel;
    JLabel statusMsg;
    JMenuBar mainBar;
    JButton saveButton;
    JButton loadButton;
    JTextArea listingArea;

    int listOption = 2;
    String listClassName;

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

        //Status Bar
        statusBarPanel = new JPanel();
        statusMsg = new JLabel(" " + "Status Messages", JLabel.LEFT);
        statusMsg.setForeground(Color.WHITE);
        statusBarPanel.setLayout(new BorderLayout());
        statusBarPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBarPanel.setBackground(Color.BLACK);
        statusBarPanel.add(statusMsg, BorderLayout.WEST);
        mainFrame.add("South", statusBarPanel);

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
        JMenuItem deleteSingleParamMenuItem = new JMenuItem("Parameter");
        JMenuItem deleteParamsMenuItem = new JMenuItem("Parameters");
        deleteMenu.add(deleteClassMenuItem);
        deleteMenu.add(deleteMethodMenuItem);
        deleteMenu.add(deleteFieldMenuItem);
        deleteMenu.add(deleteRelationshipMenuItem);
        deleteMenu.add(deleteSingleParamMenuItem);
        deleteMenu.add(deleteParamsMenuItem);

        //Modify Menu Options
        JMenu editClassMenu = new JMenu("Class");
        JMenuItem editClassnameItem = new JMenuItem("Class Name");
        editClassMenu.add(editClassnameItem);
        JMenu editMethodMenu = new JMenu("Method ");
        JMenuItem editMethodnameItem = new JMenuItem("Method Name");
        JMenuItem editMethodSingleParam = new JMenuItem("Method Single Parameter");
        JMenuItem editMethodParamItem = new JMenuItem("Method Parameters");
        editMethodMenu.add(editMethodnameItem);
        editMethodMenu.add(editMethodSingleParam);
        editMethodMenu.add(editMethodParamItem);
        JMenu editFieldMenu = new JMenu("Field");
        JMenuItem editFieldnameItem = new JMenuItem("Field Name");
        editFieldMenu.add(editFieldnameItem);
        editMenu.add(editClassMenu);
        editMenu.add(editMethodMenu);
        editMenu.add(editFieldMenu);

        //List Menu Options
        JMenuItem listClass = new JMenuItem("Class");
        JMenuItem listClasses = new JMenuItem("Classes");
        JMenuItem listRelationships = new JMenuItem("Relationships");
        listMenu.add(listClasses);
        listMenu.add(listRelationships);

        mainBar.add(Box.createHorizontalGlue());
        mainBar.add(fileMenu);
        mainBar.add(createMenu);
        mainBar.add(deleteMenu);
        mainBar.add(editMenu);
        mainBar.add(listMenu);
        mainBar.add(Box.createRigidArea(new Dimension(550,35)));

        //UML Display Area
        listingArea = new JTextArea("UML Diagram\n", 38, 111);
        listingArea.setEditable(false);
        Font font = new Font("Courier New", Font.BOLD, 12);
        listingArea.setFont(font);

        // Retreiving CLI Output 
        PrintStream stdout = System.out;
        stdout.println("Starting gui for console output"); 
        GuiOutputStream rawout = new GuiOutputStream(listingArea);
        System.setOut(new PrintStream(rawout, true));

        mainPanel.add(mainBar);
        mainPanel.add(new JScrollPane(listingArea));
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        
        //FILE LISTENER
        //Save button listener
        saveMenuItem.addActionListener(e -> {
            String message = guiCtr.guiSaveCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Load button listener
        loadMenuItem.addActionListener(e -> {
            String message = guiCtr.guiLoadCtr();
            listSelector();
            statusMsg.setText(message);
        });
        //Exit button listener
        exitMenuItem.addActionListener(e -> {
            System.exit(0);
        });

        //CREATE LISTENER
        //Create Class Button
        createClassMenuItem.addActionListener(e -> {
            String message = guiCtr.guiCreateClassCtr();
            listSelector();
            statusMsg.setText(message); 
        });
        //Create Method Button
        createMethodMenuItem.addActionListener(e -> {
            String message = guiCtr.createMethodCtr();
            listSelector();
            statusMsg.setText(message);
        });
        //Create Field Button
        createFieldMenuItem.addActionListener(e -> {
            String message = guiCtr.createFieldCtr();
            listSelector();
            statusMsg.setText(message);
        });
        //Create Relationship Button
        createRelationshipMenuItem.addActionListener(e -> {
            String message = guiCtr.createRelationshipCtr(); 
            listSelector();
            statusMsg.setText(message);
        });

        //DELETE LISTENER
        //Delete Class Button
        deleteClassMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteClassCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Delete Class Button
        deleteMethodMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteMethodCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Delete Class Button
        deleteFieldMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteFieldCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Delete Class Button
        deleteRelationshipMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteRelationshipCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Delete Single Parameter Button
        deleteSingleParamMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteSingleParamCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Delete Parameters Button
        deleteParamsMenuItem.addActionListener(e -> {
            String message = guiCtr.deleteParamsCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        
        //MODIFY LISTENER
        //Modify Class Name Button
        editClassnameItem.addActionListener(e -> {
            String message = guiCtr.renameClassCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Modify Method Name Button
        editMethodnameItem.addActionListener(e -> {
            String message = guiCtr.renameMethodCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Modify Method Single Param
        editMethodSingleParam.addActionListener(e -> {
            String message = guiCtr.editMethodSingleParam(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Modify Method Params
        editMethodParamItem.addActionListener(e -> {
            String message = guiCtr.editMethodParamsCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
        //Modify Field Name
        editFieldnameItem.addActionListener(e -> {
            String message = guiCtr.renameFieldCtr(); 
            listSelector();
            statusMsg.setText(message);
        });
            
        //LIST LISTENER
        //List Classes
        listClasses.addActionListener(e -> {
            listOption = 2;
            listClassesView();
            statusMsg.setText("Printed Diagram");
        });
        //List Relationships
        listRelationships.addActionListener(e -> {
            listOption = 3;
            listRelationshipsView(); 
            statusMsg.setText("Printed Diagram");
        });

    }

    public void listSelector(){
        if(listOption == 2){
            listClassesView();
        }
        else if(listOption == 3){
            listRelationshipsView();
        }
        else {
            statusMsg.setText("ERROR: LISTING SELECTION INVALID");
        }
    }

    public void listClassesView(){
        listingArea.setText("UML Diagram\n");
        guiCtr.listClassesCtr();
    }

    
    public void listRelationshipsView(){
        listingArea.setText("UML Diagram\n");
        guiCtr.listRelationshipsCtr();
    }

    //Gets Stream from Command Line
    private class GuiOutputStream extends OutputStream {
        JTextArea textArea;

        public GuiOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int data) throws IOException {
            textArea.append(new String(new byte[] { (byte) data }));
        }
    }

}


