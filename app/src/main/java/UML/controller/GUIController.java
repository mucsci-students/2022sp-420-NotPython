package UML.controller;

import javax.swing.*;
import java.util.*;

import UML.model.*;
import UML.model.Class;
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

        if (fileName.equals(""))
        {
            return "ERROR: file name not entered";
        }

        message = dg.saveDiagram(fileName);
        return message;
    }
    
    //Load File GUI
    public String guiLoadCtr(){
        String message;
        String fileName = guiPop.guiLoadPop();

        if (fileName.equals(""))
        {
            return "ERROR: file name not entered";
        }

        message = dg.loadDiagram(fileName);
        return message;
    }

    //GUI Create Class GUI Controller
    public String[] guiCreateClassCtr(){
        String message;
        String[] returning = new String [2];
        String className = guiPop.guiCreateClassPop();
        returning[0] = className;

        if (className.equals(""))
        {
            returning[1] = "ERROR: class name not entered";
            return returning;
        }

        message = dg.createClass(className);
        returning[1] = message;
        return returning;
    }

    //GUI Create Method Controller
    public String[] createMethodCtr(){
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> params = new ArrayList<String>();
        String[] input = new String [2];
        String[] values = new String [2];
        
        //Get Data
        int csize = dg.classList.size();

        if (csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;

        if (className.equals(""))
        {
            values[1] = "ERROR: class name not selected";
            return values;
        }

        input = guiPop.createMethodPop(input);

        String methodName = input[0];

        if (methodName.equals(""))
        {
            values[1] = "ERROR: method name not entered";
            return values;
        }

        String type =  input[1];

        if (type.equals(""))
        {
            values[1] = "ERROR: method type not entered";
            return values;
        }

        params = guiPop.getParams();
        
        String message = dg.createMethod(className, type, methodName, params);
        values[1] = message;
        return values;
    }

    //GUI Create Field Controller
    public String[] createFieldCtr(){
        String[] input = new String[3];
        String[] values = new String [2];

        //Get Data
        int size = dg.classList.size();

        if (size == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        input = guiPop.createFieldPop(classes, input); 
        String className = input[0];
        values[0] = className;
        if (className.equals(""))
        {
            values[1] = "ERROR: class name not selected";
            return values;
        }

        String field = input[1];

        if (field.equals(""))
        {
            values[1] = "ERROR: field name not entered";
            return values;
        }

        String type = input[2];

        if (type.equals(""))
        {
            values[1] = "ERROR: field type not entered";
            return values;
        }

        String message = dg.createField(className, field, type);
        values[1] = message;
        return values;
    }

    //GUI Create Relationship Controller
    public String createRelationshipCtr(){
        String[] input = new String[3];

        //Get Data
        int size = dg.classList.size();

        if (size == 0)
        {
            return "ERROR: no classes exist";
        }
        
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String[] types = {"Aggregation", "Composition", "Inheritance", "Realization"};
        input = guiPop.createRelationshipPop(types, classes, input);
        String type = input[0];

        if (type.equals(""))
        {
            return "ERROR: relationship type not entered";
        }

        String src = input[1];
        
        if (src.equals(""))
        {
            return "ERROR: relationship source class not entered";
        }

        String dest = input[2];

        if (dest.equals(""))
        {
            return "ERROR: relationship destination class not entered";
        }

        String message = dg.createRelationship(type, src, dest);
        return message;
    }

    //GUI Delete Class GUI Controller
    public String deleteClassCtr(){
        int size = dg.classList.size();

        if (size == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String className = guiPop.deleteClassPop(classes);

        if (className.equals(""))
        {
            return "ERROR: class not selected";
        }

        String message = dg.deleteClass(className);
        return message;
    }

    //GUI Delete Method Controller
    public String deleteMethodCtr(){
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            return "ERROR: no methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.deleteMethodPop(methods);

        if (methodName.equals(""))
        {
            return "ERROR: no method name selected";
        }

        String message = dg.deleteMethod(className, methodName);
        return message;
    }

    //GUI Delete Field Controller
    public String deleteFieldCtr(){
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int fsize = dg.getFieldSize(className);

        if (fsize == 0)
        {
            return "ERROR: no fields exist for \"" + className + "\"";
        }

        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        String fieldName = guiPop.deleteFieldPop(fields);

        if (fieldName.equals(""))
        {
            return "ERROR: field name not entered";
        }

        String message = dg.deleteField(className, fieldName);
        return message;
    }

    //Delete Relationship GUI Controller
    public String deleteRelationshipCtr(){
        String message, src, dest;
        String[] input = new String[2];
        int size = dg.classList.size();

        if (size == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        input = guiPop.deleteRelationshipPop(classes, input);
        src = input[0];

        if (src.equals(""))
        {
            return "ERROR: source class not entered";
        }

        dest = input[1];

        if (dest.equals(""))
        {
            return "ERROR: destination class not entered";
        }

        message = dg.deleteRelationship(src, dest);
        return message;
    }

    //Rename Class GUI Controller
    public String renameClassCtr(){
        String message, oldName, newName;
        String[] input = new String[2];
        int size = dg.classList.size();
        String[] classes = new String[size];

        if (size == 0)
        {
            return "ERROR: no classes exist";
        }

        classes = dg.convertClassListArray();
        input = guiPop.renameClassPop(classes, input);
        oldName = input[0];

        if (oldName.equals(""))
        {
            return "ERROR: old class name not selected";
        }

        newName = input[1];

        if (newName.equals(""))
        {
            return "ERROR: new class name not entered";
        }

        message = dg.renameClass(oldName, newName);
        return message;
    }

    //Rename Method GUI Controller
    public String renameMethodCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);

        if (className.equals(""))
        {
            return "ERROR: class not selected";
        }

        int msize = dg.getMethodSize(className);

        if (msize < 1)
        {
            return "ERROR: No methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);

        input = guiPop.renameMethodPop(methods, input);
        String oldName = input[0];

        if (oldName.equals(""))
        {
            return "ERROR: old method name not selected";
        }

        String newName = input[1];

        if (newName.equals(""))
        {
            return "ERROR: Did not write method name to change to";
        }

        String message = dg.renameMethod(className, oldName, newName);
        return message;
    }

    //GUI Modify Single Parameter Controller
    public String editMethodSingleParam(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);

        //check to see if there are methods
        if (msize < 1)
        {
            return "ERROR: No methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            return "ERROR: method name not selected";
        }

        String[] params = dg.convertMethodParamsListArray(className, methodName);
        
        if (params.length == 0)
        {
            return "ERROR: This method has no parameters";
        }

        String oldParam, newParam, newType;
        input = guiPop.editMethodSingleParamPop(params, input);
        oldParam = input[0];

        if (oldParam.equals(""))
        {
            return "ERROR: Old Parameter name not entered";
        }

        newParam = input[1];

        if (newParam.equals(""))
        {
            return "ERROR: New Parameter name not entered";
        }

        newType = input[2];

        if(newType.equals(""))
        {
            return "ERROR: New Parameter type not entered";
        }

        String message = dg.changeParameter(className, methodName, oldParam, newParam, newType);
        return message;
    }

    //GUI Modify Parameters Controller
    public String editMethodParamsCtr(){
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> input = new ArrayList<String>();
        
        //Get Class List
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);

        if (className.equals(""))
        {
            return "ERROR: no class name selected";
        }

        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            return "ERROR: no methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            return "ERROR: method name not selected";
        }

        String[] params = new String[msize];
        params = dg.convertMethodParamsListArray(className, methodName);
        input = guiPop.getParams();
        String[] inputLst = new String[input.size() + 4];
        for (int i = 4; i < inputLst.length; i++){
            inputLst[i] = input.get(i - 4);
        }

        String message = dg.changeParameters(className, methodName, inputLst);
        return message;
    }

    //GUI Rename Field Controller
    public String renameFieldCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int fsize = dg.getFieldSize(className);

        if (fsize == 0)
        {
            return "ERROR: no fields exist for \"" + className + "\"";
        }

        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        input = guiPop.renameFieldPop(fields, input);
        String oldName = input[0];

        if (oldName.equals(""))
        {
            return "ERROR: old field name not selected";
        }

        String newName = input[1];

        if (newName.equals(""))
        {
            return "ERROR: New field name not entered";
        }

        String message = dg.renameField(className, oldName, newName);
        return message;
    }

    //GUI Delete Parameter Controller
    public String deleteSingleParamCtr(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            return "ERROR: no methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            return "ERROR: method name not entered";
        }

        String[] params = dg.convertMethodParamsListArray(className, methodName);

        if (params.length == 0)
        {
            return "ERROR: no parameters exist for \"" + methodName + "\" in \"" + className + "\""; 
        }

        String param = guiPop.deleteSingleParamPop(params, input);

        if (param.equals(""))
        {
            return "ERROR: parameter not selected";
        }

        String message = dg.deleteParameter(className, methodName, param);
        return message;
    }

    //GUI Delete Parameters Controller
    public String deleteParamsCtr(){
        //Get Class List
        String[] input = new String[3];
        int csize = dg.classList.size();

        if(csize == 0)
        {
            return "ERROR: no classes exist";
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);

        if (className.equals(""))
        {
            return "ERROR: class name not selected";
        }

        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            return "ERROR: no methods exist for \"" + className + "\"";
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            return "ERROR: no method name selected";
        }

        String message = dg.deleteParameters(className, methodName);
        return message;
    }

    //Method to know what is the length of the longest sentence in the class(ClassName, Field, Method)
	public String maximumWord(String className) {
		int maxLength = 0;
        String maxWord = "";
		Class classSample = dg.getClass(className);

		if(classSample.name.length()>maxLength) {
			maxLength = classSample.name.length();
            maxWord = classSample.name;
		}
		
		for(Field fld: classSample.fields) {
			if((fld.name.length() + fld.type.length() + 1)>maxLength) {
				maxLength = fld.name.length() + fld.type.length() + 2;
                maxWord = fld.name + fld.type + "---";
			}
		}

		int methodlength = 0;
        String maxMethodWord = "";
		for(Method m: classSample.methods) {
			methodlength = m.name.length() + m.type.length() + 3;
            maxMethodWord = m.name + m.type + "---";
			for(Parameter p: m.parameters)
			{
				methodlength += p.name.length() + p.type.length() + 5;
                maxMethodWord += p.name + p.type + "----";
			}
			if(methodlength > maxLength) {
				maxLength = methodlength;
                maxWord = maxMethodWord;
			}
		}
		return maxWord;
	}

    public String updateFieldsCtr(String className){
        return dg.fieldsToString(className);
    }

    public String updateMethodsCtr(String className){
        return dg.methodsToString(className);
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
