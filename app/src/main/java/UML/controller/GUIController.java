package UML.controller;

import javax.swing.*;
import java.util.*;
// import java.awt.*;

import UML.model.*;
import UML.view.GUIPopup;

public class GUIController {

    Diagram dg = new Diagram();
    GUIPopup guiPop = new GUIPopup();

    //Save File GUI
    public String guiSaveCtr(){
        String message;
        String fileName = guiPop.guiSavePop();
        message = dg.saveDiagram(fileName);
        return message;
    }
    
    //Load File GUI
    public String guiLoadCtr(){
        String message;
        String fileName = guiPop.guiLoadPop();
        message = dg.loadDiagram(fileName);
        return message;
    }

    //Create Class GUI Controller
    public String guiCreateClassCtr(){
        String message;
        String className = guiPop.guiCreateClassPop();
        message = dg.createClass(className);
        return message;
    }

    public String createMethodCtr(){
        JFrame x = new JFrame("test");
        ArrayList<String> y = new ArrayList<>();
        ArrayList<String> details = guiPop.createMethodPop();
        ArrayList<String> parameters = guiPop.getParams(x, 0 , y);

        String message = dg.createMethod(details.get(1), details.get(2), details.get(0), parameters);
        return message;
    }

    public String createFieldCtr(){
        String[] input = new String[3];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        input = guiPop.createFieldPop(classes, input); 
        String className = input[0];
        String field = input[1];
        String type = input[2];
        String message = dg.createField(className, field, type);
        return message;
    }

    public String createRelationshipCtr(){
        String[] input = new String[3];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String[] types = {"Aggregation", "Composition", "Inheritance", "Realization"};
        input = guiPop.createRelationshipPop(types, classes, input);
        String type = input[0];
        String src = input[1];
        String dest = input[2];
        String message = dg.createRelationship(type, src, dest);
        return message;
    }

    //Delete Class GUI Controller
    public String deleteClassCtr(){
        String message;
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String className = guiPop.deleteClassPop(classes);
        message = dg.deleteClass(className);
        return message;
    }

    public String deleteMethodCtr(){
        ArrayList<String> details = guiPop.deleteMethodPop();
        String message = dg.deleteMethod(details.get(1), details.get(2), details.get(0));
        return message;
    }

    public String deleteFieldCtr(){
        String message, clasName, fieldName;
        String[] input = new String[2];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        int fsize = dg.fields.size();
        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray();
        input = guiPop.deleteFieldPop(classes, fields, input);
        clasName = input[0];
        fieldName = input[1];
        message = dg.deleteField(clasName, fieldName);
        return message;
    }

    //Delete Relationship GUI Controller
    public String deleteRelationshipCtr(){
        String message, src, dest;
        String[] input = new String[2];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        input = guiPop.deleteRelationshipPop(classes, input);
        src = input[0];
        dest = input[1];
        message = dg.deleteRelationship(src, dest);
        return message;
    }

    //Rename Class GUI Controller
    public String renameClassCtr(){
        String message, oldName, newName;
        String[] input = new String[2];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        input = guiPop.renameClassPop(classes, input);
        oldName = input[0];
        newName = input[1];
        message = dg.renameClass(oldName, newName);
        return message;
    }

    public String renameMethodCtr(){
        return "";
    }

    public String editMethodReturnCtr(){
        return "";
    }

    public String editMethodParamsCtr(){
        return "";
    }

    public String renameFieldCtr(){
        String[] input = new String[3];
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        int fsize = dg.fields.size();
        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray();
        input = guiPop.renameFieldPop(classes, fields, input);
        String className = input[0];
        String field = input[1];
        String type = input[2];
        String message = dg.createField(className, field, type);
        return message;
    }

    public String editFieldTypeCtr(){
        return "";
    }

    public String listClassCtr(){
        return "";
    }

    public void listClassesCtr(){
        dg.listClasses();
    }

    public String listRelationshipsCtr(){
        return "";
    }


    

}
