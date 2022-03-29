package UML.controller;

import javax.swing.*;
import java.util.*;

import UML.model.*;
import UML.view.GUIPopup;

public class GUIController {

    Diagram dg = new Diagram();
    GUIPopup guiPop = new GUIPopup();

    //Undo File GUI
    public String guiUndoCtr(){
        return dg.undo();
    }
    
    //Undo File GUI
    public String guiRedoCtr(){
        return dg.redo();
    }

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

    //GUI Create Class GUI Controller
    public String guiCreateClassCtr(){
        String message;
        String className = guiPop.guiCreateClassPop();
        message = dg.createClass(className);
        return message;
    }

    //GUI Create Method Controller
    public String createMethodCtr(){
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> params = new ArrayList<String>();
        String[] input = new String [2];
        
        //Get Data
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        input = guiPop.createMethodPop(input);
        String methodName = input[0];
        String type =  input[1];
        params = guiPop.getParams();

        String message = dg.createMethod(className, type, methodName, params);
        return message;
    }

    //GUI Create Field Controller
    public String createFieldCtr(){
        String[] input = new String[3];

        //Get Data
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

    //GUI Create Relationship Controller
    public String createRelationshipCtr(){
        String[] input = new String[3];

        //Get Data
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

    //GUI Delete Class GUI Controller
    public String deleteClassCtr(){
        int size = dg.classList.size();
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String className = guiPop.deleteClassPop(classes);
        String message = dg.deleteClass(className);
        return message;
    }

    //GUI Delete Method Controller
    public String deleteMethodCtr(){
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.deleteMethodPop(methods);
        String message = dg.deleteMethod(className, methodName);
        return message;
    }

    //GUI Delete Field Controller
    public String deleteFieldCtr(){
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int fsize = dg.getFieldSize(className);
        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        String fieldName = guiPop.deleteFieldPop(fields);
        String message = dg.deleteField(className, fieldName);
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

    //Rename Method GUI Controller
    public String renameMethodCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        input = guiPop.renameMethodPop(methods, input);
        String oldName = input[0];
        String newName = input[1];
        String message = dg.renameMethod(className, oldName, newName);
        return message;
    }

    //GUI Modify Single Parameter Controller
    public String editMethodSingleParam(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);
        String[] params = new String[msize];
        params = dg.convertMethodParamsListArray(className, methodName);
        String oldParam, newParam, newType;
        input = guiPop.editMethodSingleParamPop(params, input);
        oldParam = input[0];
        newParam = input[1];
        newType = input[2];
        String message = dg.changeParameter(className, methodName, oldParam, newParam, newType);
        return message;
    }

    //GUI Modify Parameters Controller
    public String editMethodParamsCtr(){
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> input = new ArrayList<String>();
        
        //Get Class List
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);
        String[] params = new String[msize];
        params = dg.convertMethodParamsListArray(className, methodName);
        input = guiPop.getParams();
        String[] inputLst = new String[input.size() + 5];
        for (int i = 5; i < inputLst.length; i++){
            inputLst[i] = input.get(i - 5);
        }
        String message = dg.changeParameters(className, methodName, inputLst);
        return message;
    }

    //GUI Rename Field Controller
    public String renameFieldCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int fsize = dg.getFieldSize(className);
        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        input = guiPop.renameFieldPop(fields, input);
        String oldName = input[0];
        String newName = input[1];
        String message = dg.renameField(className, oldName, newName);
        return message;
    }

    //GUI Delete Parameter Controller
    public String deleteSingleParamCtr(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);
        String[] params = new String[msize];
        params = dg.convertMethodParamsListArray(className, methodName);
        String param = guiPop.deleteSingleParamPop(params, input);
        String message = dg.deleteParameter(className, methodName, param);
        return message;
    }

    //GUI Delete Parameters Controller
    public String deleteParamsCtr(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);
        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);
        String message = dg.deleteParameters(className, methodName);
        return message;
    }

    //GUI List Classes Controller
    public void listClassesCtr(){
        dg.listClasses();
    }

    //GUI List Relationships Controller
    public void listRelationshipsCtr(){
        dg.listRelationships();
    }


    

}
