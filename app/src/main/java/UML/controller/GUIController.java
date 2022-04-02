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
    public String[] createRelationshipCtr(){
        String[] input = new String[3];
        String[] values = new String[4];
        //Get Data
        int size = dg.classList.size();

        if (size == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }
        
        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String[] types = {"Aggregation", "Composition", "Inheritance", "Realization"};
        input = guiPop.createRelationshipPop(types, classes, input);
        String type = input[0];
        values[0] = type;
        if (type.equals(""))
        {
            values[1] = "ERROR: relationship type not entered";
            return values;
        }

        String src = input[1];
        values[2] = src;
        
        if (src.equals(""))
        {
            values[1] = "ERROR: relationship source class not entered";
            return values;
        }

        String dest = input[2];
        values[3] = dest;

        if (dest.equals(""))
        {
            values[1] = "ERROR: relationship destination class not entered";
            return values;
        }

        String message = dg.createRelationship(type, src, dest);
        values[1] = message;
        return values;
    }

    //GUI Delete Class GUI Controller
    public String[] deleteClassCtr(){
        int size = dg.classList.size();
        String[] values = new String[2];
        if (size == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[size];
        classes = dg.convertClassListArray();
        String className = guiPop.deleteClassPop(classes);
        values[0] = className;
        if (className.equals(""))
        {
            values[1] = "ERROR: class not selected";
            return values;
        }

        String message = dg.deleteClass(className);
        values[1] = message;
        return values;
    }

    //GUI Delete Method Controller
    public String[] deleteMethodCtr(){
        int csize = dg.classList.size();
        String values[] = new String[2];
        if (csize == 0)
        {
            values[1] =  "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int msize = dg.getMethodSize(className);
        values[0] = className;
        if (msize == 0)
        {
            values[1] = "ERROR: no methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.deleteMethodPop(methods);

        if (methodName.equals(""))
        {
            values[1] = "ERROR: no method name selected";
            return values;
        }

        String message = dg.deleteMethod(className, methodName);
        values[1] = message;
        return values;
    }

    //GUI Delete Field Controller
    public String[] deleteFieldCtr(){
        int csize = dg.classList.size();
        String[] values = new String[2];

        if (csize == 0)
        {
            values[1] =  "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;
        int fsize = dg.getFieldSize(className);

        if (fsize == 0)
        {
            values[1] = "ERROR: no fields exist for \"" + className + "\"";
            return values;
        }

        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        String fieldName = guiPop.deleteFieldPop(fields);

        if (fieldName.equals(""))
        {
            values[1] = "ERROR: field name not entered";
            return values;
        }

        String message = dg.deleteField(className, fieldName);
        values[1] = message;
        return values;
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
    public String[] renameClassCtr(){
        String message, oldName, newName;
        String[] values = new String[3];
        String[] input = new String[2];
        int size = dg.classList.size();
        String[] classes = new String[size];

        if (size == 0)
        {
            values[1] =  "ERROR: no classes exist";
            return values;
        }

        classes = dg.convertClassListArray();
        input = guiPop.renameClassPop(classes, input);
        oldName = input[0];

        if (oldName.equals(""))
        {
            values[1] = "ERROR: old class name not selected";
            return values;
        }
        values[0] = oldName;
        newName = input[1];
        values[2] = newName;

        if (newName.equals(""))
        {
            values[1] = "ERROR: new class name not entered";
            return values;
        }

        message = dg.renameClass(oldName, newName);
        values[1] = message;
        return values;
    }

    //Rename Method GUI Controller
    public String[] renameMethodCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();
        String[] values = new String[2];
        if (csize == 0)
        {
            values[1] =  "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;
        if (className.equals(""))
        {
            values[1] = "ERROR: class not selected";
            return values;
        }

        int msize = dg.getMethodSize(className);

        if (msize < 1)
        {
            values[1] = "ERROR: No methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);

        input = guiPop.renameMethodPop(methods, input);
        String oldName = input[0];

        if (oldName.equals(""))
        {
            values[1] = "ERROR: old method name not selected";
            return values;
        }

        String newName = input[1];

        if (newName.equals(""))
        {
            values[1] = "ERROR: Did not write method name to change to";
            return values;
        }

        String message = dg.renameMethod(className, oldName, newName);
        values[1] = message;
        return values;
    }

    //GUI Modify Single Parameter Controller
    public String[] editMethodSingleParam(){
        //Get Class List
        String[] input = new String[3];
        String[] values = new String[2];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;
        //Get Method List
        int msize = dg.getMethodSize(className);

        //check to see if there are methods
        if (msize < 1)
        {
            values[1] = "ERROR: No methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            values[1] = "ERROR: method name not selected";
            return values;
        }

        String[] params = dg.convertMethodParamsListArray(className, methodName);
        
        if (params.length == 0)
        {
            values[1] = "ERROR: This method has no parameters";
            return values;
        }

        String oldParam, newParam, newType;
        input = guiPop.editMethodSingleParamPop(params, input);
        oldParam = input[0];

        if (oldParam.equals(""))
        {
            values[1] = "ERROR: Old Parameter name not entered";
            return values;
        }

        newParam = input[1];

        if (newParam.equals(""))
        {
            values[1] = "ERROR: New Parameter name not entered";
            return values;
        }

        newType = input[2];

        if(newType.equals(""))
        {
            values[1] = "ERROR: New Parameter type not entered";
            return values;
        }

        String message = dg.changeParameter(className, methodName, oldParam, newParam, newType);
        return values;
    }

    //GUI Modify Parameters Controller
    public String[] editMethodParamsCtr(){
        JFrame x = new JFrame("Edit Parameters");
        ArrayList<String> input = new ArrayList<String>();
        String[] values = new String[2];
        
        //Get Class List
        int csize = dg.classList.size();

        if (csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;

        if (className.equals(""))
        {
            values[1] = "ERROR: no class name selected";
            return values;
        }

        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            values[1] = "ERROR: no methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            values[1] = "ERROR: method name not selected";
            return values;
        }

        String[] params = new String[msize];
        params = dg.convertMethodParamsListArray(className, methodName);
        input = guiPop.getParams();
        String[] inputLst = new String[input.size() + 4];
        for (int i = 4; i < inputLst.length; i++){
            inputLst[i] = input.get(i - 4);
        }

        String message = dg.changeParameters(className, methodName, inputLst);
        return values;
    }

    //GUI Rename Field Controller
    public String[] renameFieldCtr(){
        String[] input = new String[2];
        int csize = dg.classList.size();
        String[] values = new String[2];

        if (csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        int fsize = dg.getFieldSize(className);
        values[0] = className;

        if (fsize == 0)
        {
            values[1] = "ERROR: no fields exist for \"" + className + "\"";
            return values;
        }

        String[] fields = new String[fsize];
        fields = dg.convertFieldListArray(className);
        input = guiPop.renameFieldPop(fields, input);
        String oldName = input[0];

        if (oldName.equals(""))
        {
            values[1] = "ERROR: old field name not selected";
            return values;
        }

        String newName = input[1];

        if (newName.equals(""))
        {
            values[1] = "ERROR: New field name not entered";
            return values;
        }

        String message = dg.renameField(className, oldName, newName);
        return values;
    }

    //GUI Delete Parameter Controller
    public String[] deleteSingleParamCtr(){
        //Get Class List
        String[] input = new String[3];
        String[] values = new String[2];
        int csize = dg.classList.size();

        if (csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;
        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            values[1] = "ERROR: no methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            values[1] = "ERROR: method name not entered";
            return values;
        }

        String[] params = dg.convertMethodParamsListArray(className, methodName);

        if (params.length == 0)
        {
            values[1] = "ERROR: no parameters exist for \"" + methodName + "\" in \"" + className + "\""; 
            return values;
        }

        String param = guiPop.deleteSingleParamPop(params, input);

        if (param.equals(""))
        {
            values[1] = "ERROR: parameter not selected";
            return values;
        }

        String message = dg.deleteParameter(className, methodName, param);

        return values;
    }

    //GUI Delete Parameters Controller
    public String[] deleteParamsCtr(){
        //Get Class List
        String[] input = new String[3];
        String[] values = new String[2];
        int csize = dg.classList.size();

        if(csize == 0)
        {
            values[1] = "ERROR: no classes exist";
            return values;
        }

        String[] classes = new String[csize];
        classes = dg.convertClassListArray();
        String className = guiPop.getClassPop(classes);
        values[0] = className;
        if (className.equals(""))
        {
            values[1] = "ERROR: class name not selected";
            return values;
        }

        //Get Method List
        int msize = dg.getMethodSize(className);

        if (msize == 0)
        {
            values[1] = "ERROR: no methods exist for \"" + className + "\"";
            return values;
        }

        String[] methods = new String[msize];
        methods = dg.convertMethodListArray(className);
        String methodName = guiPop.getMethodPop(methods);

        if (methodName.equals(""))
        {
            values[1] = "ERROR: no method name selected";
            return values;
        }

        String message = dg.deleteParameters(className, methodName);
        values[1] = message;
        return values;
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
