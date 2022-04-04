package UML.view;

import UML.controller.GUIController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class GUIPopup {

    //GUI Save File Popup
    public String guiSavePop(){
        JFrame saveFilePop = new JFrame("Save File");
        String getFileName = JOptionPane.showInputDialog(saveFilePop, "Enter File Name");
        return getFileName;
    }
    
    //GUI Load File Popup
    public String guiLoadPop(){
        JFrame loadFilePop = new JFrame("Load File");
        String getFileName = JOptionPane.showInputDialog(loadFilePop, "Enter File Name");
        return getFileName;
    }

    //GUI Create Method Popup
    public String[] createMethodPop(String[] input){
        //Create the Frame and Panel
        JFrame getMethodPop = new JFrame("Create Method");
        JPanel panel = new JPanel(new GridLayout(2, 1));

        //Creating Input Field 1
        JLabel methodNameLabel = new JLabel("Enter Method Name:");
        panel.add(methodNameLabel);
        JTextField methodName = new JTextField(5);
        panel.add(methodName);

        //Creating Input Field 2
        JLabel methodTypeLabel = new JLabel("Enter Method Type: ");
        panel.add(methodTypeLabel);
        JTextField methodType = new JTextField(5);
        panel.add(methodType);

        getMethodPop.add(panel);
        JOptionPane.showMessageDialog(getMethodPop, panel);
        input[0] = methodName.getText();
        input[1] = methodType.getText();
        return input;
    }

    //GUI Get Class Popup
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

    //GUI Get Method Popup
    public String getMethodPop(String[] methods){
        //Create the Frame and Panel
        JFrame getMethodPop = new JFrame("Method Selector");
        JPanel panel = new JPanel(new GridLayout(1, 2));

        //Creating Input Field 1
        JLabel methodNameLabel = new JLabel("Select Method Name:");
        panel.add(methodNameLabel);
        JComboBox names = new JComboBox(methods);
        names.setSelectedIndex(-1);
        panel.add(names);

        getMethodPop.add(panel);
        JOptionPane.showMessageDialog(getMethodPop, panel);
        return String.valueOf(names.getSelectedItem());
    }

    //GUI Get Parameters Popup
    public ArrayList<String> getParams() {  
        ArrayList<String> array = new ArrayList<>();

        //Create the Frame and Panel
        JFrame framePop = new JFrame("Class Selector");
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        //Creating Input Field 1
        JLabel label1 = new JLabel("Enter Parameter Type:");
        panel.add(label1);
        JTextField text1 = new JTextField(5);
        panel.add(text1);

        //Creating Input Field 2
        JLabel label2 = new JLabel("Enter Parameter Name: ");
        panel.add(label2);
        JTextField text2 = new JTextField(5);
        panel.add(text2);

        //Continue CheckBox
        JLabel filler = new JLabel("");
        panel.add(filler);
        JCheckBox moreBox = new JCheckBox("More Parameters?", true);
        panel.add(moreBox);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        String input1 = text1.getText();
        String input2 = text2.getText();
        
        if((text1.getText().isEmpty() || text2.getText().isEmpty()) && moreBox.isSelected()){
            JOptionPane.showMessageDialog(framePop, "ERROR: Previous entries were invalid and not recorded ");
        }
        else if((text1.getText().isEmpty() && text2.getText().isEmpty()) && !moreBox.isSelected()){
            return array;
        }
        else{
            array.add(input2);
            array.add(input1);
        }
        if(moreBox.isSelected()){
            array.addAll(getParams());            
        }
        return array;
    }

    //GUI Create Feild Popup
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

    //GUI Create Relationship Popup
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

    //GUI Delete Method Popup
    public String deleteMethodPop(String[] methods){
        //Create the Frame and Panel
        JFrame methodDelPop = new JFrame("Delete Method");
        JPanel panel = new JPanel(new GridLayout(2, 2));

        //Creating Input Field 1
        JLabel methodNameLabel = new JLabel("Select Method Name:");
        panel.add(methodNameLabel);
        JComboBox methodNames = new JComboBox(methods);
        methodNames.setSelectedIndex(-1);
        panel.add(methodNames);

        //Creating the frame and getting the data
        methodDelPop.add(panel);
        JOptionPane.showMessageDialog(methodDelPop, panel);
        return String.valueOf(methodNames.getSelectedItem());
    }

    //Popup Window for Delete Field
    public String deleteFieldPop(String[] fields){
        //Create the Frame and Panel
        JFrame fieldDelPop = new JFrame("Delete Field");
        JPanel panel = new JPanel(new GridLayout(2, 2));

        //Creating Input Field 1
        JLabel fieldNameLabel = new JLabel("Select Field Name:");
        panel.add(fieldNameLabel);
        JComboBox fieldNames = new JComboBox(fields);
        fieldNames.setSelectedIndex(-1);
        panel.add(fieldNames);

        //Creating the frame and getting the data
        fieldDelPop.add(panel);
        JOptionPane.showMessageDialog(fieldDelPop, panel);
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

    //GUI Rename Class Popup
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
        JFrame framePop = new JFrame("Rename Method");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        //Creating Input Field 1
        JLabel fieldNameLabel = new JLabel("Select Method Name:");
        panel.add(fieldNameLabel);
        JComboBox fieldNames = new JComboBox(method);
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

    //GUI Modify Method Parameter
    public String[] editMethodSingleParamPop(String[] params, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Modify Method Parameters");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        //Creating Input Field 1
        JLabel label1 = new JLabel("Select Parameter Name:");
        panel.add(label1);
        JComboBox names = new JComboBox(params);
        names.setSelectedIndex(-1);
        panel.add(names);

        //Creating Input Field 2
        JLabel label2 = new JLabel("Enter New Name:");
        panel.add(label2);
        JTextField name2 = new JTextField(5);
        panel.add(name2);

        //Creating Input Field 3
        JLabel label3 = new JLabel("Enter New Type: ");
        panel.add(label3);
        JTextField type = new JTextField(5);
        panel.add(type);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        input[0] = String.valueOf(names.getSelectedItem());
        input[1] = name2.getText();
        input[2] = type.getText();;
        return input;
    }

    //GUI Rename Field Popup
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

    //GUI Delete Single Parameter Popup
    public String deleteSingleParamPop(String[] params, String[] input){
        //Create the Frame and Panel
        JFrame framePop = new JFrame("Edit Single Parameter");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        //Creating Input Field 1
        JLabel paramLabel = new JLabel("Select Parameter:");
        panel.add(paramLabel);
        JComboBox paramBox = new JComboBox(params);
        paramBox.setSelectedIndex(-1);
        panel.add(paramBox);

        //Creating the frame and getting the data
        framePop.add(panel);
        JOptionPane.showMessageDialog(framePop, panel);
        return String.valueOf(paramBox.getSelectedItem());
    }

    public void listClassesPop(){
        //For future use
    }

    public void listRelationshipsPop(){
        //For future use
    }
    
    //GUI Create Class Popup
    public String guiCreateClassPop(){
        JFrame createClassPop = new JFrame("Create Class");
        String getClassName = JOptionPane.showInputDialog(createClassPop, "Enter Class Name");
        return getClassName;
    }

}