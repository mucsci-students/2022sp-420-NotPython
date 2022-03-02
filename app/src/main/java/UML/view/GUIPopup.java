package UML.view;

import UML.controller.GUIController;

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

    public String guiCreateClassPop(){
        JFrame createClassPop = new JFrame("Create Class");
        String getClassName = JOptionPane.showInputDialog(createClassPop, "Enter Class Name");
        return getClassName;
    }

    public void createMethodPop(){

    }

    public void createFieldPop(){

    }

    public void createRelationshipPop(){

    }

    public String deleteClassPop(){
        JFrame deleteClassPop = new JFrame("Delete Class");
        String getClassName = JOptionPane.showInputDialog(deleteClassPop, "Enter Class Name");
        return getClassName;
    }

    public void deleteMethodPop(){

    }

    public void deleteFieldPop(){

    }

    public void deleteRelationshipPop(){

    }

    public void renameClassPop(){

    }

    public void renameMethodPop(){

    }

    public void editMethodReturnPop(){

    }

    public void editMethodParamsPop(){

    }

    public void renameFieldPop(){

    }

    public void editFieldTypePop(){

    }

    public void listClassPop(){

    }

    public void listClassesPop(){

    }

    public void listRelationshipsPop(){

    }
}