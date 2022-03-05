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
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> params = new ArrayList<String>();
        String[] input = new String [2];
        
        //Get Class List
        int csize = dg.classList.size();
        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        input = guiPop.createMethodPop(input);
        String methodName = input[0];
        String type =  input[1];
        
        params = guiPop.getParams(params);
        String message = dg.createMethod(className, type, methodName, params);
        return message;

        // JFrame x = new JFrame("test");
        // ArrayList<String> y = new ArrayList<>();
        // ArrayList<String> details = guiPop.createMethodPop();
        // ArrayList<String> parameters = guiPop.getParams(x, 0 , y);

        // String message = dg.createMethod(details.get(1), details.get(2), details.get(0), parameters);
        // return message;
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
        input = guiPop.getParams(input);
        String[] inputLst = input.toArray(new String[input.size()]);
        String message = dg.changeParameters(className, methodName, inputLst);
        return message;
    }

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

    public void listClassesCtr(){
        dg.listClasses();
    }

    public void listRelationshipsCtr(){
        dg.listRelationships();
    }


    

}
