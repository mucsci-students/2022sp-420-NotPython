package UML.view;

import UML.controller.GUIController;
import UML.controller.Listing;
import UML.model.Relationship;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;

public class GUI {

    GUIController guiCtr = new GUIController();
    Listing lister = new Listing();
    GUIUndoRedo undoRedo = new GUIUndoRedo();

    HashMap <String, ClassBox> boxes;
    HashMap <String, ArrowDraw> arrows;

    JFrame mainFrame;
    JPanel mainPanel;
    JPanel innerPanel;
    JPanel statusBarPanel;
    JLabel statusMsg;
    JMenuBar mainBar;
    JButton saveButton;
    JButton loadButton;

    int listOption = 2;
    String listClassName;
    int index = 0;
    Random rand = new Random();

    public GUI()
    {
        boxes = new HashMap <String, ClassBox>();
        arrows = new HashMap <String, ArrowDraw>();
    }

    public GUI(GUI other)
    {
        this.boxes = new HashMap <String, ClassBox>();

        Iterator boxIter = other.boxes.entrySet().iterator();

        while(boxIter.hasNext())
        {
            Map.Entry element = (Map.Entry) boxIter.next();
            ClassBox temp = (ClassBox) element.getValue();
            this.boxes.put((String) element.getKey(), temp.clone());
        }
    }

    public void GUI_view() { 
        mainFrame = new JFrame ("UML Editor");
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 850);
        //mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setLocationRelativeTo(null); 

        mainPanel = new JPanel();
        innerPanel = new JPanel();
        JScrollPane jsp = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //mainPanel.add(jsp);
        mainPanel.setPreferredSize(new Dimension (2000, 1500));
        mainPanel.setBackground(Color.gray);
        mainPanel.setLayout(null);        

        //Initialize the Main Bar
        mainBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu utilMenu = new JMenu("Utility");
        JMenu createMenu = new JMenu("Create");
        JMenu deleteMenu = new JMenu("Delete");
        JMenu editMenu = new JMenu("Modify");
        //JMenu listMenu = new JMenu("List");

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
        JMenuItem saveDiagramImage = new JMenuItem("Save Image");
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveDiagramImage);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);

        //Utility Menu Options
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        utilMenu.add(redoMenuItem);
        utilMenu.add(undoMenuItem);

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

      
        mainBar.add(Box.createHorizontalGlue());
        mainBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);       
        mainBar.add(editMenu);
        mainBar.add(deleteMenu);
        mainBar.add(createMenu);
        mainBar.add(utilMenu);
        mainBar.add(fileMenu);

        mainFrame.add("North", mainBar);
        //mainPanel.add(new JScrollPane(listingArea));
        mainFrame.add(jsp);
        //mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        
        //FILE LISTENER
        //Redo button listener
        redoMenuItem.addActionListener(e -> {
            String message = guiCtr.guiRedoCtr(); 
            if (undoRedo.canRedo())
            {
                GUI old = undoRedo.redo(clone());

                this.boxes = new HashMap <String, ClassBox> ();
                this.arrows = new HashMap <String, ArrowDraw> ();
                
                //initialize the class boxes
                for (HashMap.Entry<String, ClassBox> entry : old.boxes.entrySet()) {
                    String key = entry.getKey();
                    ClassBox temp = entry.getValue();
                    ClassBox box = new ClassBox(temp.name, temp.x_pos, temp.y_pos, guiCtr, this);
                    this.boxes.put(box.name, box);
                }

                ArrayList <Relationship> rels = guiCtr.passRelationships();

                Graphics g = mainPanel.getGraphics();

                //initialize relationship arrows
                for (Relationship r : rels)
                {
                    ArrowDraw arrow = new ArrowDraw(boxes.get(r.src).panel, boxes.get(r.dest).panel, r.type, g);
                    arrow.setVisible(true);
                    arrow.setOpaque(false);
                    arrow.setLocation(0, 0);
                    arrow.setSize(4000, 3000);
                    arrows.put(r.src+r.dest, arrow);
                }
            }
            statusMsg.setText(message);
            updater();
        });

        //Undo button listener
        undoMenuItem.addActionListener(e -> {
            String message = guiCtr.guiUndoCtr();
            if (undoRedo.canUndo())
            {
                GUI old = undoRedo.undo(clone());
                this.boxes = new HashMap <String, ClassBox> ();
                this.arrows = new HashMap <String, ArrowDraw> ();
                
                //initialize class boxes
                for (HashMap.Entry<String, ClassBox> entry : old.boxes.entrySet()) {
                    String key = entry.getKey();
                    ClassBox temp = entry.getValue();
                    ClassBox box = new ClassBox(temp.name, temp.x_pos, temp.y_pos, guiCtr, this);
                    this.boxes.put(box.name, box);
                }

                ArrayList <Relationship> rels = guiCtr.passRelationships();

                Graphics g = mainPanel.getGraphics();
                
                //initialize relationship arrows
                for (Relationship r : rels)
                {
                    ArrowDraw arrow = new ArrowDraw(boxes.get(r.src).panel, boxes.get(r.dest).panel, r.type, g);
                    arrow.setVisible(true);
                    arrow.setOpaque(false);
                    arrow.setLocation(0, 0);
                    arrow.setSize(4000, 3000);
                    arrows.put(r.src+r.dest, arrow);
                }
            }
            statusMsg.setText(message);
            updater();
        });
        //Save button listener
        saveMenuItem.addActionListener(e -> {
            HashMap <String, String> locations = new HashMap <String, String> ();
            for (HashMap.Entry<String, ClassBox> entry : boxes.entrySet()){
                String key = entry.getKey();
                ClassBox temp = entry.getValue();
                locations.put(key, temp.x_pos + " " + temp.y_pos);
            }
            String message = guiCtr.guiSaveCtr(locations);
            statusMsg.setText(message);
        });
        //Save Image button listener
        saveDiagramImage.addActionListener(e -> {
            String fileName = guiCtr.guiSaveImageCtr();
            BufferedImage img = new BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            mainPanel.print(img.getGraphics()); // or: panel.printAll(...);
            try {
                ImageIO.write(img, "png", new File(fileName + ".png"));
            }
            catch (IOException ee) {
                // TODO Auto-generated catch block
                ee.printStackTrace();
            }
            statusMsg.setText("Image Successfully Saved As: " + fileName);
        });
        //Load button listener
        loadMenuItem.addActionListener(e -> {
            this.boxes = new HashMap <String, ClassBox> ();
            this.arrows = new HashMap <String, ArrowDraw> ();
            String message = guiCtr.guiLoadCtr();
            undoRedo = new GUIUndoRedo();
            loadIntoGUI();
            statusMsg.setText(message);
        });
        //Exit button listener
        exitMenuItem.addActionListener(e -> {
            System.exit(0);
        });

        //CREATE LISTENER
        //Create Class Button
        createClassMenuItem.addActionListener(e -> {
            String[] values = guiCtr.guiCreateClassCtr();
            String className = values[0];
            String message = values[1];

            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                ClassBox box = new ClassBox(className, rand.nextInt(mainPanel.getWidth()), rand.nextInt(mainPanel.getHeight()), guiCtr, this);
                boxes.put(className, box);
                updater();
                index++;
            } 
        });
        //Create Method Button
        createMethodMenuItem.addActionListener(e -> {
            String[] values = guiCtr.createMethodCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Create Field Button
        createFieldMenuItem.addActionListener(e -> {
            String[] values = guiCtr.createFieldCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Create Relationship Button
        createRelationshipMenuItem.addActionListener(e -> {
            String[] values = guiCtr.createRelationshipCtr();
            String message = values[1];
            String type = values[0];
            String src = values[2];
            String dest = values[3];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                Graphics g = mainPanel.getGraphics();
                ArrowDraw arrow = new ArrowDraw(boxes.get(src).panel, boxes.get(dest).panel, type, g);
                arrow.setVisible(true);
                arrow.setOpaque(false);
                arrow.setLocation(0, 0);
                arrow.setSize(4000, 3000);
                arrows.put(src+dest, arrow);
                mainPanel.add(arrow);
                mainPanel.repaint();
                updater();
            }
        });

        //DELETE LISTENER
        //Delete Class Button
        deleteClassMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteClassCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                boxes.remove(className);
                updater();
            }
        });
        //Delete Method Button
        deleteMethodMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteMethodCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Delete Field Button
        deleteFieldMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteFieldCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Delete Relationship Button
        deleteRelationshipMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteRelationshipCtr();
            String message = values[0];
            String src = values[1];
            String dest = values[2];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                arrows.remove(src+dest);
                updater();
            }
        });
        //Delete Single Parameter Button
        deleteSingleParamMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteSingleParamCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Delete Parameters Button
        deleteParamsMenuItem.addActionListener(e -> {
            String[] values = guiCtr.deleteParamsCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        
        //MODIFY LISTENER
        //Modify Class Name Button
        editClassnameItem.addActionListener(e -> {
            String[] values = guiCtr.renameClassCtr();
            String message = values[1];
            String className = values[0];
            String newName = values[2];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                ClassBox temp = boxes.get(className);
                temp.rename(newName);
                boxes.remove(className);
                boxes.put(newName, temp);
                updater();
            }
        });
        //Modify Method Name Button
        editMethodnameItem.addActionListener(e -> {
            String[] values = guiCtr.renameMethodCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Modify Method Single Param
        editMethodSingleParam.addActionListener(e -> {
            String[] values = guiCtr.editMethodSingleParam();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Modify Method Params
        editMethodParamItem.addActionListener(e -> {
            String[] values = guiCtr.editMethodParamsCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
        //Modify Field Name
        editFieldnameItem.addActionListener(e -> {
            String[] values = guiCtr.renameFieldCtr();
            String message = values[1];
            String className = values[0];
            statusMsg.setText(message);
            if(!message.contains("ERROR")){
                snapshot();
                updater();
            }
        });
    }

    public void updater(){
        mainPanel.removeAll();
        for (HashMap.Entry<String, ClassBox> entry : boxes.entrySet()) {
            String key = entry.getKey();
            ClassBox box = entry.getValue();
            box.updateFields();
            box.updateMethods();
            mainPanel.add(box.panel);
        }
        
        mainPanel.repaint();
        arrowUpdater();
        //mainPanel.validate();
        
    }

    public void arrowUpdater(){
        for (HashMap.Entry<String, ArrowDraw> entry : arrows.entrySet()) {
            String key = entry.getKey();
            ArrowDraw arrow = entry.getValue();
            Graphics g = mainPanel.getGraphics();
            arrow.arrowRedraw(g);
            arrow.repaint();
            mainPanel.add(arrow);
            
        }
    }

    public void loadIntoGUI(){
        String[] classListArray = guiCtr.getClassListArray();
        HashMap <String, String> locs = guiCtr.getLocationsCtr();
        ClassBox box;
        //load classes into GUI
        for(int i = 0; i < classListArray.length; i++){
            String className = classListArray[i];

            //FIX LOCATION LOADING
            String classLoc = locs.get(className);
            if (classLoc.equals("-1 -1")){
                box = new ClassBox(className, rand.nextInt(mainPanel.getWidth()), rand.nextInt(mainPanel.getHeight()), guiCtr, this);
            }
            else{
                String [] location = classLoc.split(" ");
                box = new ClassBox(className, Integer.parseInt(location[0]), Integer.parseInt(location[1]), guiCtr, this);
            }
            boxes.put(className, box);
        }

        //Load relationships
        ArrayList <Relationship> rels = guiCtr.passRelationships();
        Graphics g = mainPanel.getGraphics();
        //initialize relationship arrows
        for (Relationship r : rels)
        {
            ArrowDraw arrow = new ArrowDraw(boxes.get(r.src).panel, boxes.get(r.dest).panel, r.type, g);
            arrow.setVisible(true);
            arrow.setOpaque(false);
            arrow.setLocation(0, 0);
            arrow.setSize(4000, 3000);
            arrows.put(r.src+r.dest, arrow);
        }
        updater();
    }

    //clone design pattern
    public GUI clone()
    {
        return new GUI(this);
    }

    public void snapshot()
    {
        undoRedo.snapshotGUI(clone());
    }

}

