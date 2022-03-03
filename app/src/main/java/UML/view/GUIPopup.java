package UML.view;

import UML.controller.GUIController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class GUIPopup {

    public String guiSavePop(){
        JFrame saveFilePop = new JFrame("Save File");
        String getFileName = JOptionPane.showInputDialog(saveFilePop, "Enter File Name");
        return getFileName;
    }
    
    public String guiLoadPop(){
        JFrame loadFilePop = new JFrame("Load File");
        String getFileName = JOptionPane.showInputDialog(loadFilePop, "Enter File Name");
        return getFileName;
    }

    public ArrayList<String> createMethodPop(){
        ArrayList<String> details = new ArrayList<String>();
        JFrame createMethodPop = new JFrame("Create Method");
        // 0
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter Method Name"));
        // 1
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter class to add method to"));
        // 2
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter type of method"));
        return details;
    }

    public String getClassPop(String[] classes){
        //Create the Frame and Panel
        JFrame getClassPop = new JFrame("Class Selector");
        JPanel panel = new JPanel(new GridLayout(1, 2));

        //Creating Input Field 1
        JLabel classNameLabel = new JLabel("Select Class Name:");
        panel.add(classNameLabel);
        JComboBox classNames = new JComboBox(classes);
        classNames.setSelectedIndex(-1);
        panel.add(classNames);

        getClassPop.add(panel);
        JOptionPane.showMessageDialog(getClassPop, panel);
        return String.valueOf(classNames.getSelectedItem());
    }

    public ArrayList<String> getParams(JFrame frame, int counter, ArrayList<String> array) {
        array.add(JOptionPane.showInputDialog(frame, "Enter name of parameter"));
        if(!(array.get(counter).equals(""))){
            array.add(JOptionPane.showInputDialog(frame, "Enter parameter type"));
            return getParams(frame, counter+=2, array);
        }
        return array;
    }

    public String[] createFieldPop(String[] classes, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Create Field");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        //Creating Input Field 1
        JLabel classLabel = new JLabel("Select Class Name:");
        panel.add(classLabel);
        JComboBox classNames = new JComboBox(classes);
        classNames.setSelectedIndex(-1);
        panel.add(classNames);

        //Creating Input Field 2
        JLabel fieldNameLabel = new JLabel("Enter Field Name:");
        panel.add(fieldNameLabel);
        JTextField fieldName = new JTextField(5);
        panel.add(fieldName);

        //Creating Input Field 3
        JLabel fieldTypeLabel = new JLabel("Enter Field Type: ");
        panel.add(fieldTypeLabel);
        JTextField fieldType = new JTextField(5);
        panel.add(fieldType);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        input[0] = String.valueOf(classNames.getSelectedItem());
        input[1] = fieldName.getText();
        input[2] = fieldType.getText();;
        return input;
    }

    public String[] createRelationshipPop(String[] types, String[] classes, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Create Relationship");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        //Creating Input Field 1
        JLabel typeLabel = new JLabel("Select Type:");
        panel.add(typeLabel);
        JComboBox typeNames = new JComboBox(types);
        typeNames.setSelectedIndex(-1);
        panel.add(typeNames);

        //Creating Input Field 2
        JLabel classNameLabel1 = new JLabel("Select Class Name:");
        panel.add(classNameLabel1);
        JComboBox classNames1 = new JComboBox(classes);
        classNames1.setSelectedIndex(-1);
        panel.add(classNames1);

        //Creating Input Field 3
        JLabel classNameLabel2 = new JLabel("Select Class Name:");
        panel.add(classNameLabel2);
        JComboBox classNames2 = new JComboBox(classes);
        classNames2.setSelectedIndex(-1);
        panel.add(classNames2);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        input[0] = String.valueOf(typeNames.getSelectedItem());
        input[1] = String.valueOf(classNames1.getSelectedItem());
        input[2] = String.valueOf(classNames2.getSelectedItem());
        return input;
    }

    //Popup Window for Delete Class
    public String deleteClassPop(String[] classes){
        //Create the Frame and Panel
        JFrame deleteClassPop = new JFrame("Delete Class");
        JPanel panel = new JPanel(new GridLayout(1, 2));

        //Creating Input Field 1
        JLabel classNameLabel = new JLabel("Select Class Name:");
        panel.add(classNameLabel);
        JComboBox classNames = new JComboBox(classes);
        classNames.setSelectedIndex(-1);
        panel.add(classNames);

        deleteClassPop.add(panel);
        JOptionPane.showMessageDialog(deleteClassPop, panel);
        return String.valueOf(classNames.getSelectedItem());
    }

    public ArrayList<String> deleteMethodPop(){
        ArrayList<String> details = new ArrayList<String>();

        JFrame createMethodPop = new JFrame("Create Method");
        // 0
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter Method Name"));

        // 1
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter class of method to delete"));

        // 2
        details.add(JOptionPane.showInputDialog(createMethodPop, "Enter type of method"));

        return details;
    }

    //Popup Window for Delete Field
    public String deleteFieldPop(String[] fields){
        //Create the Frame and Panel
        JFrame deleteFieldPop = new JFrame("Delete Field");
        JPanel panel = new JPanel(new GridLayout(2, 2));

        //Creating Input Field 1
        JLabel fieldNameLabel = new JLabel("Select Field Name:");
        panel.add(fieldNameLabel);
        JComboBox fieldNames = new JComboBox(fields);
        fieldNames.setSelectedIndex(-1);
        panel.add(fieldNames);

        //Creating the frame and getting the data
        deleteFieldPop.add(panel);
        JOptionPane.showMessageDialog(deleteFieldPop, panel);
        return String.valueOf(fieldNames.getSelectedItem());
    }

    //Popup Window for Delete Relationship
    public String[] deleteRelationshipPop(String[] classes, String[] input){
        //Create the Frame and Panel
        JFrame deleteRelatePop = new JFrame("Delete Relationship");
        JPanel panel = new JPanel(new GridLayout(2, 2));

        //Creating Input Field 1
        JLabel classNameLabel = new JLabel("Select Class Name:");
        panel.add(classNameLabel);
        JComboBox classNames = new JComboBox(classes);
        classNames.setSelectedIndex(-1);
        panel.add(classNames);

        //Creating Input Field 2
        JLabel classNameLabel2 = new JLabel("Select Class Name:");
        panel.add(classNameLabel2);
        JComboBox classNames2 = new JComboBox(classes);
        classNames2.setSelectedIndex(-1);
        panel.add(classNames2);

        //Creating the frame and getting the data
        deleteRelatePop.add(panel);
        JOptionPane.showMessageDialog(deleteRelatePop, panel);
        input[0] = String.valueOf(classNames.getSelectedItem());
        input[1] = String.valueOf(classNames2.getSelectedItem());
        return input;
    }

    public String[] renameClassPop(String[] classes, String[] input){
        JFrame renameClassPop = new JFrame("Rename Class");
        JPanel panel = new JPanel(new GridLayout(2, 2));

        //Creating Input Field 1
        JLabel classNameLabel = new JLabel("Select Class Name:");
        panel.add(classNameLabel);
        JComboBox classNames = new JComboBox(classes);
        classNames.setSelectedIndex(-1);
        panel.add(classNames);

        //Creating Input Field 2
        JLabel classNameLabel2 = new JLabel("Enter New Name:");
        panel.add(classNameLabel2);
        JTextField text = new JTextField(5);
        panel.add(text);

        //Creating the frame and getting data
        renameClassPop.add(panel);
        JOptionPane.showMessageDialog(renameClassPop, panel);
        input[0] = String.valueOf(classNames.getSelectedItem());
        input[1] = text.getText();
        return input;
    }

    //Popup Window For Rename Method
    public String[] renameMethodPop(String[] method, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Rename Field");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        //Creating Input Field 1
        JLabel fieldNameLabel = new JLabel("Select Field Name:");
        panel.add(fieldNameLabel);
        JComboBox fieldNames = new JComboBox(method);
        fieldNames.setSelectedIndex(-1);
        panel.add(fieldNames);

        //Creating Input Field 2
        JLabel fieldNameLabel2 = new JLabel("Enter New Name:");
        panel.add(fieldNameLabel2);
        JTextField text = new JTextField(5);
        panel.add(text);

        //Creating Input Field 3
        JLabel fieldNameLabel3 = new JLabel("Enter Type:");
        panel.add(fieldNameLabel3);
        JTextField text2 = new JTextField(5);
        panel.add(text2);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        input[0] = String.valueOf(fieldNames.getSelectedItem());
        input[1] = text.getText();
        input[2] = text2.getText();
        return input;
    }

    public void editMethodReturnPop(){

    }

    public void editMethodParamsPop(){

    }

    public String[] renameFieldPop(String[] fields, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Rename Field");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        //Creating Input Field 1
        JLabel fieldNameLabel = new JLabel("Select Field Name:");
        panel.add(fieldNameLabel);
        JComboBox fieldNames = new JComboBox(fields);
        fieldNames.setSelectedIndex(-1);
        panel.add(fieldNames);

        //Creating Input Field 2
        JLabel fieldNameLabel2 = new JLabel("Enter New Name:");
        panel.add(fieldNameLabel2);
        JTextField text = new JTextField(5);
        panel.add(text);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        input[0] = String.valueOf(fieldNames.getSelectedItem());
        input[1] = text.getText();
        return input;
    }

    public void editFieldTypePop(){

    }

    public void listClassPop(){

    }

    public void listClassesPop(){

    }

    public void listRelationshipsPop(){

    }
    
    public String guiCreateClassPop(){
        JFrame createClassPop = new JFrame("Create Class");
        String getClassName = JOptionPane.showInputDialog(createClassPop, "Enter Class Name");
        return getClassName;
    }

}