package UML.model;

import UML.model.*;
import UML.view.ClassBox;
import UML.controller.Listing;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;

public class Diagram {

    // Arraylist for classes
    public ArrayList<Class> classList;

    // ArrayList for relationships goes here
    public ArrayList<Relationship> relationships;

    // HashMap for maintaining class locations
    public HashMap<String, String> locations;

    // make undoRedo a global for testing
    public UndoRedo undoRedo = null;

    // Default constructor
    public Diagram() {
        classList = new ArrayList<Class>();
        relationships = new ArrayList<Relationship>();
        locations = new HashMap<String, String>();
    }

    public Diagram(ArrayList<Class> classes, ArrayList<Relationship> relList, HashMap<String, String> locs) {
        classList = new ArrayList<Class>();
        relationships = new ArrayList<Relationship>();
        locations = new HashMap<String, String>();

        for (Class c : classes) {
            classList.add(new Class(c));
        }

        for (Relationship r : relList) {
            relationships.add(new Relationship(r.type, r.src, r.dest));
        }

        for (HashMap.Entry<String, String> entry : locs.entrySet()) {
            String key = entry.getKey();
            String loc = entry.getValue();
            locations.put(key, loc);
        }
    }

    // Create class method
    // Command: Create class <classname>
    // undoable operation
    public String createClass(String name) {
        String error = validation_check(name);
        // check to see if the name contains any invalid characters
        if (!error.trim().equals("")) {
            return error;
        }

        // check to see if class exists already
        if (getClass(name) != null) {
            return "ERROR: Class with name \"" + name + "\" already exists";
        }

        // take snapshot then add the new class to the classList
        snapshot();
        classList.add(new Class(name));
        locations.put(name, "-1 -1");
        return "Class \"" + name + "\" Added";
    }

    // Rename class method
    // Command: rename class <old_name> <new_name>
    // undoable operation
    public String renameClass(String oldName, String newName) {
        // get class with old name
        Class tempClass = getClass(oldName);
        if (tempClass == null) {
            return "ERROR: Class \"" + oldName + "\" does not exist";
        }

        // see if a class with new name already exists
        if (getClass(newName) != null) {
            return "ERROR: Class with name \"" + newName + "\" already exists";
        }

        String error = validation_check(newName);
        // check to see if the name contains any invalid characters
        if (!error.trim().equals("")) {
            return error;
        }

        // take snapshot then change class name and set object again in classlist
        // and change key in location
        snapshot();
        int index = classList.indexOf(tempClass);
        String locString = locations.remove(tempClass.name);
        tempClass.rename(newName);
        locations.put(newName, locString);
        classList.set(index, tempClass);
        return "Class \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    // Deletes a class and all of it's fields and methods
    // Command: delete class <classname>
    // undoable operation
    public String deleteClass(String className) {
        // if class exists then delete
        Class tempClass = getClass(className);
        if (tempClass == null) {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }

        // snapshot for undo
        snapshot();

        // delete all relationships associated with class <className>
        Relationship tempRelationship;
        for (int i = 0; i < relationships.size(); ++i) {
            tempRelationship = relationships.get(i);
            if (tempRelationship.src.equals(className) || tempRelationship.dest.equals(className)) {
                relationships.remove(tempRelationship);
                --i;
            }
        }

        // delete class
        classList.remove(tempClass);
        locations.remove(tempClass.name);
        return "Class with name \"" + tempClass.name + "\" and its relationships deleted";
    }

    // Create field
    // Command: create field <className> <field_type> <field_name>
    // undoable operation
    public String createField(String clasName, String fldName, String fldType) {
        // Check if class exists
        Class tempClass = getClass(clasName);
        if (tempClass != null) {
            // check to see if the name contains any invalid characters
            String error = validation_check(fldName);
            if (!error.trim().equals("")) {
                return error;
            }
            if (getField(tempClass, fldName) != null) {
                return "ERROR: Field with name \"" + fldName + "\" for \"" + clasName + "\" already exists";
            }

            // Take snapshot then add field to arraylist
            snapshot();
            tempClass.fields.add(new Field(fldName, fldType));
            return "Field \"" + fldName + "\" of type \"" + fldType + "\" Added to Class \"" + clasName + "\"";
        } else {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    // Delete field
    // Command: delete field <className> <field_name>
    // undoable operation
    public String deleteField(String clasName, String fldName) {
        // Check if class exists
        Class tempClass = getClass(clasName);
        if (tempClass != null) {
            Field tempFld = getField(tempClass, fldName);
            if (tempFld == null) {
                return "ERROR: Field with name \"" + fldName + "\" for \"" + clasName + "\" does not exist";
            }
            // take a snapshot then delete field from arraylist
            snapshot();
            tempClass.fields.remove(tempFld);
            return "Field \"" + fldName + "\" removed from Class \"" + clasName + "\"";
        } else {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    // Rename field method
    // Command: rename field <className> <old_name> <new_name>
    public String renameField(String clas, String oldName, String newName) {
        // Check if class exists
        Class tempClass = getClass(clas);
        if (tempClass == null) {
            return "ERROR: Class \"" + clas + "\" does not exist";
        }

        // Check if field with old name exists
        if (getField(tempClass, oldName) == null) {
            return "ERROR: Field with name \"" + oldName + "\" does not exist";
        }

        // Check if field with new name already exists
        if (getField(tempClass, newName) != null) {
            return "ERROR: Field with name \"" + newName + "\" already exists";
        }

        Field tempFld = getField(tempClass, oldName);

        // check to see if the name contains any invalid characters
        String error = validation_check(newName);
        if (!error.trim().equals("")) {
            return error;
        }

        // snapshot then change field name and set object again in classlist\
        snapshot();
        int index = tempClass.fields.indexOf(tempFld);
        tempFld.rename_field(newName);
        tempClass.fields.set(index, tempFld);
        return "Field \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    // Create Method
    // Command: create method <class_name> <method_name> <type> <param>
    // undoable operation
    public String createMethod(String className, String type, String methodName, ArrayList<String> parameter) {
        Set<String> paramNames = new HashSet<String>();
        Class c = getClass(className);
        // checks if class exists
        if (c == null) {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
        // checks if type is valid
        String error = validation_check(type);
        if (!error.equals("")) {
            return error + " in method type";
        }
        // checks if method name is valid
        error = validation_check(methodName);
        if (!error.equals("")) {
            return error + " in method name";
        }

        if ((parameter.size() % 2) != 0) {
            return "ERROR: Parameter list length is incorrect";
        }
        // checks parameter list
        for (int i = 0; i < parameter.size() - 1; i += 2) {
            error = validation_check(parameter.get(i));
            if (!error.equals("")) {
                return error + " in method parameter name";
            }

            if (!paramNames.add(parameter.get(i))) {
                return "ERROR: Duplicate parameter name \"" + parameter.get(i) + "\" in parameter list";
            }

            error = validation_check(parameter.get(i + 1));
            if (!error.equals("")) {
                return error + " in method parameter type";
            }
        }
        // check if method exists
        if (getMethod(className, methodName) != null) {
            return "ERROR: method already exists";
        }

        // snapshot then create new method
        snapshot();
        c.methods.add(new Method(type, methodName, parameter));
        return "Method with name \"" + methodName + "\" added to class \"" + className + "\"";
    }

    // Delete method
    // Command: delete method <className> <method_name>
    // undoable operation
    public String deleteMethod(String className, String methodName) {
        // Check if class exists
        Class tempClass = getClass(className);
        if (tempClass != null) {
            Method tempMethod = getMethod(className, methodName);
            if (tempMethod == null) {
                return "ERROR: Method with name \"" + methodName + "\" for \"" + className + "\" does not exist";
            }

            // snapshot then delete method from arraylist
            snapshot();
            tempClass.methods.remove(tempMethod);
            return "Method \"" + methodName + "\" removed from Class \"" + className + "\"";
        } else {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
    }

    // Rename method
    // Command: rename method <class_name> <old_name> <new_name>
    // undoable command
    public String renameMethod(String clas, String oldName, String newName) {
        // Check if class exists
        Class tempClass = getClass(clas);
        if (tempClass == null) {
            return "ERROR: Class \"" + clas + "\" does not exist";
        }

        // Check if method exists
        if (getMethod(clas, oldName) == null) {
            return "ERROR: Method with name \"" + oldName + "\" for \"" + clas + "\" does not exist";
        }

        // Check if method with new name already exists
        if (getMethod(clas, newName) != null) {
            return "ERROR: Method \"" + newName + "\" already exists";
        }

        // check to see if the name contains any invalid characters
        String error = validation_check(newName);
        if (!error.trim().equals("")) {
            return error;
        }

        // snapshot then change method name and set object in classlist
        snapshot();
        Method tempMtd = getMethod(clas, oldName);
        int index = tempClass.methods.indexOf(tempMtd);
        tempMtd.rename_method(newName);
        tempClass.methods.set(index, tempMtd);
        return "Method \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    // create relationship
    // Command: create relationship <relationship_type> <src> <dest>
    // undoable command
    public String createRelationship(String type, String src, String dest) {
        if (src.equals(dest)) {
            return "ERROR: relationship cannot exist between a class and itself";
        }

        // check to see if source exists already
        if (getClass(src) == null) {
            return "ERROR: Class with name \"" + src + "\" does not exist";
        }

        // check to see if destination exists already
        if (getClass(dest) == null) {
            return "ERROR: Class with name \"" + dest + "\" does not exist";
        }

        // check for correct relationship type
        if (!(type.equalsIgnoreCase("aggregation") || type.equalsIgnoreCase("composition") ||
                type.equalsIgnoreCase("inheritance") || type.equalsIgnoreCase("realization"))) {
            return "ERROR: Incorrect type: \"" + type
                    + "\" valid types are Aggregation, Composition, Inheritance and Realization";
        }

        // check to see if relationship exists already
        if (getRelationship(src, dest) != null || getRelationship(dest, src) != null) {
            return "ERROR: Relationship from " + src + " to " + dest + " of type " + type + " already exists";
        }

        // check for correct relationship type
        if (!(type.equalsIgnoreCase("aggregation") || type.equalsIgnoreCase("composition") ||
                type.equalsIgnoreCase("inheritance") || type.equalsIgnoreCase("realization"))) {
            return "ERROR: Incorrect type: \"" + type
                    + "\" valid types are Aggregation, Composition, Inheritance and Realization";
        }

        // check to see if relationship exists already
        if (getRelationship(src, dest) != null || getRelationship(dest, src) != null) {
            return "ERROR: Relationship from " + src + " to " + dest + " of type " + type + " already exists";
        }

        // snapshot then add the new relationship to the relationship list
        snapshot();
        relationships.add(new Relationship(type, src, dest));
        return "Relationship from " + src + " to " + dest + " of type " + type + " added";
    }

    // delete relationship method
    // Command: delete relationship <src> <dest>
    // undoable command
    public String deleteRelationship(String src, String dest) {
        // if relationship exists then delete
        Relationship tempRelationship = getRelationship(src, dest);
        if (tempRelationship == null) {
            return "ERROR: Relationship from " + src + " to " + dest + " does not exist";
        }

        // snapshot then delete the relationship from the relationship list
        snapshot();
        relationships.remove(tempRelationship);
        return "Relationship from " + src + " to " + dest + " deleted";
    }

    // Change parameter method
    // Command: Change parameter <className> <method_name> <old_parameter>
    // <new_parameter> <new_parameter_type>
    // undoable command
    public String changeParameter(String className, String method_name, String old_parameter, String new_parameter,
            String new_parameter_type) {

        // Check if class exists
        Class tempClass = getClass(className);
        if (tempClass != null) {
            // Check if method exists
            Method tempMethod = getMethod(className, method_name);
            if (tempMethod != null) {
                // Check if parameter exists
                Parameter tempParameter = getParameter(tempMethod, old_parameter);
                if (tempParameter != null) {
                    // Check if new parameter name is valid
                    String error = validation_check(new_parameter);
                    if (!error.equals("")) {
                        return error + " in new parameter name";
                    }
                    // Check if new parameter type is valid
                    error = validation_check(new_parameter_type);
                    if (!error.equals("")) {
                        return error + " in new parameter type";
                    }

                    // snapshot then change parameter name
                    snapshot();
                    tempParameter.name = new_parameter;
                    tempParameter.type = new_parameter_type;
                    return "Parameter with name \"" + old_parameter + "\" changed to \"" + new_parameter
                            + "\" with type \"" + new_parameter_type + "\"";
                } else {
                    return "ERROR: Parameter with name \"" + old_parameter + "\" does not exist";
                }
            } else {
                return "ERROR: Method with name \"" + method_name + "\" does not exist";
            }
        } else {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
    }

    // Change parameters method
    // Command: Change parameters <className> <method_name> <parameters>
    // undoable command
    public String changeParameters(String className, String method_name, String[] param) {
        Set<String> paramNames = new HashSet<String>();
        ArrayList<String> parameter = new ArrayList<String>();
        String error = "";
        // Check if class exists
        Class tempClass = getClass(className);
        if (tempClass != null) {
            // Check if method exists
            Method tempMethod = getMethod(className, method_name);
            if (tempMethod != null) {
                for (int i = 4; i < param.length - 1; i += 2) {
                    error = validation_check(param[i]);
                    if (!error.equals("")) {
                        return error + " in method parameter name";
                    }
                    error = validation_check(param[i + 1]);

                    if (!paramNames.add(param[i])) {
                        return "ERROR: Duplicate parameter name \"" + parameter.get(i) + "\" in parameter list";
                    }

                    if (!error.equals("")) {
                        return error + " in method parameter type";
                    }
                    parameter.add(param[i]);
                    parameter.add(param[i + 1]);
                }

                if ((param.length % 2) != 0) {
                    return "ERROR: Parameter list length is incorrect";
                }

                // snapshot then change the parameters
                snapshot();
                tempMethod.parameters.clear();
                for (int i = 0; i < parameter.size() - 1; i += 2) {
                    tempMethod.parameters.add(new Parameter(parameter.get(i), parameter.get(i + 1)));
                }
                return "Parameters for method \"" + method_name + "\" changed to the new parameters list provided.";
            } else {
                return "ERROR: Method with name \"" + method_name + "\" does not exist";
            }
        } else {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
    }

    // Delete parameter method
    // Command: Delete parameter <className> <method_name> <parameter>
    // undoable command
    public String deleteParameter(String className, String method_name, String parameter) {

        ArrayList<String> parameters = new ArrayList<String>();
        String error = "";
        // Check if class exists
        Class tempClass = getClass(className);
        if (tempClass != null) {
            // Check if method exists
            Method tempMethod = getMethod(className, method_name);
            if (tempMethod != null) {
                // Check if parameter exists
                Parameter tempParameter = getParameter(tempMethod, parameter);
                if (tempParameter != null) {
                    for (Parameter p : tempMethod.parameters) {
                        if (!p.name.equals(parameter)) {
                            parameters.add(p.name);
                            parameters.add(p.type);
                        }
                    }

                    // snapshot then create new list of parameters
                    snapshot();
                    tempMethod.parameters.clear();
                    for (int i = 0; i < parameters.size() - 1; i += 2) {
                        tempMethod.parameters.add(new Parameter(parameters.get(i), parameters.get(i + 1)));
                    }

                    return "Parameter \"" + parameter + "\" removed from method \"" + method_name + "\"";
                } else {
                    return "ERROR: Parameter with name \"" + parameter + "\" does not exist";
                }
            } else {
                return "ERROR: Method with name \"" + method_name + "\" does not exist";
            }
        } else {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
    }

    // Delete parameters method
    // Command: Delete parameters <className> <method_name>
    // undoable command
    public String deleteParameters(String className, String method_name) {

        String error = "";
        // Check if class exists
        Class tempClass = getClass(className);
        if (tempClass != null) {
            // Check if method exists
            Method tempMethod = getMethod(className, method_name);
            if (tempMethod != null) {
                snapshot();
                tempMethod.parameters.clear();
                return "All parameters removed from method \"" + method_name + "\"";
            } else {
                return "ERROR: Method with name \"" + method_name + "\" does not exist";
            }
        } else {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
    }

    public void copyGUILocations(HashMap<String, String> locs) {
        locations = locs;
    }

    // saves program to .json or .yaml file
    public String saveDiagram(String fileName) {
        Save save = new Save();

        // makes sure end of file name has .json or .yaml
        if (!(fileName.toLowerCase().contains(".json"))) // || fileName.toLowerCase().contains(".yaml")))
        {
            return "ERROR: Unsupported file type: please choose .json";
        }
        try{
            save.saveFile(fileName, classList, relationships, locations);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "Successfully saved to " + fileName;

    }

    // loads diagram from .json or .yaml file
    public String loadDiagram(String fileName) {
        Load load = new Load();
        UndoRedo undoRedo = UndoRedo.getInstance();

        // makes sure end of file name has .json or .yaml
        if (!fileName.toLowerCase().contains(".json")) {
            return "ERROR: Unsupported file type: please choose .json";
        }

        // clear undo history when diagram is loaded
        undoRedo.reset();

        // read files into data structure
        try {
            Map<String, Object> map = load.loadFile(fileName);
            classList = (ArrayList<Class>) map.get("classList");
            relationships = (ArrayList<Relationship>) map.get("relationships");
            locations = (HashMap<String, String>) map.get("locations");
            undoRedo = null;
            return "Successfully loaded from " + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            undoRedo = null;
            return "Failed to load";
        }
    }

    // List class
    // Command: list class <className>
    public void listClass(String className) {
        Listing listing = new Listing();
        listing.listClass(classList, className);
    }

    // List classes
    // Command: list classes
    public void listClasses() {
        Listing listing = new Listing();
        listing.listClasses(classList);
    }

    // List relationships
    // Command: list relationships
    public void listRelationships() {
        Listing listing = new Listing();
        listing.listRelationships(relationships);
    }

    // Test method to find class
    public Class getClass(String name) {
        for (Class c : classList) {
            if (name.equals(c.name)) {
                return c;
            }
        }
        return null;
    }

    // gets an field with fldName for curClass
    public Field getField(Class curClass, String fldName) {
        // Check if field already exists
        for (Field f : curClass.fields) {
            if (fldName.equals(f.name)) {
                return f;
            }
        }
        return null;
    }

    public HashMap<String, String> getLocations() {
        return locations;
    }

    // get a relationship for with name <name>
    public Relationship getRelationship(String src, String dest) {
        for (Relationship r : relationships) {
            if (src.equals(r.src) && dest.equals(r.dest)) {
                return r;
            }
        }
        return null;
    }

    // Get a method with class name, method name and type
    public Method getMethod(String className, String methodName) {
        Class c = getClass(className);
        if (c != null) {
            for (Method m : c.methods) {
                if (m.name.equals(methodName)) {
                    return m;
                }
            }
        }
        return null;
    }

    // Get a parameter with method, parameter name
    public Parameter getParameter(Method method, String parameterName) {
        if (method != null) {
            for (Parameter p : method.parameters) {
                if (p.name.equals(parameterName)) {
                    return p;
                }
            }
        }
        return null;
    }

    /*
     * Checks the passed string for invalid characters
     * “`\\|:'\"<.>/?
     */
    public static String validation_check(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (" `\\|:'\"<.>/?!".indexOf(input.charAt(i)) > -1) {
                return "ERROR: " + input.charAt(i) + " is an invalid character";
            }
        }
        return "";
    }

    // Helper function to convert Classes ArrayList to Array
    public String[] convertClassListArray() {
        int size = classList.size();
        String[] arrList = new String[size];
        for (int i = 0; i < size; i++) {
            arrList[i] = classList.get(i).name;
        }
        return arrList;
    }

    // Helper function to convert Fields ArrayList to Array
    public String[] convertFieldListArray(String className) {
        Class clas = getClass(className);
        int size = clas.fields.size();
        String[] arrList = new String[size];
        for (int i = 0; i < size; i++) {
            arrList[i] = clas.fields.get(i).name;
        }
        return arrList;
    }

    // Helper function to convert Methods ArrayList to Array
    public String[] convertMethodListArray(String className) {
        Class clas = getClass(className);
        int size = clas.methods.size();
        String[] arrList = new String[size];
        for (int i = 0; i < size; i++) {
            arrList[i] = clas.methods.get(i).name;
        }
        return arrList;
    }

    // Helper function to convert Parameters ArrayList to Array
    public String[] convertMethodParamsListArray(String className, String methodName) {
        Class clas = getClass(className);
        Method metho = getMethod(className, methodName);
        int psize = metho.parameters.size();
        String[] arrList = new String[psize];
        for (int i = 0; i < psize; i++) {
            arrList[i] = metho.parameters.get(i).name;
        }
        return arrList;
    }

    // Helper function to get method size
    public int getMethodSize(String className) {
        Class clas = getClass(className);
        return clas.methods.size();
    }

    // Helper function to get field size
    public int getFieldSize(String className) {
        Class clas = getClass(className);
        return clas.fields.size();
    }

    // creates clone of current diagram
    public Diagram clone() {
        return new Diagram(this.classList, this.relationships, this.locations);
    }

    // get instance then take a snapshot of the current state of the diagram
    public void snapshot() {
        undoRedo = UndoRedo.getInstance();
        undoRedo.snapshotDiagram(clone());
        undoRedo = null;
    }

    // undo command
    public String undo() {
        undoRedo = UndoRedo.getInstance();

        // check if we can undo first
        if (!undoRedo.canUndo()) {
            return "ERROR: No undoable operations have been completed";
        }

        // actually undo if no error
        Diagram old = undoRedo.undo(clone());
        this.classList = new ArrayList<Class>(old.classList);
        this.relationships = new ArrayList<Relationship>(old.relationships);
        this.locations = old.locations;
        undoRedo = null;
        return "Undo successful";
    }

    // redo command
    public String redo() {
        // get instance
        undoRedo = UndoRedo.getInstance();

        // check if we can redo
        if (!undoRedo.canRedo()) {
            return "ERROR: No command has been undone";
        }

        // actually redo if no error
        Diagram old = undoRedo.redo(clone());
        this.classList = new ArrayList<Class>(old.classList);
        this.relationships = new ArrayList<Relationship>(old.relationships);
        this.locations = old.locations;
        undoRedo = null;
        return "Redo successful";
    }

    public String fieldsToString(String className) {
        String text = "";
        Class c = getClass(className);

        for (Field f : c.fields) {
            text += " " + f.type + " " + f.name + "\n";
        }
        return text;
    }

    public ArrayList<Relationship> getRelationships() {
        ArrayList<Relationship> result = new ArrayList<Relationship>();

        for (Relationship r : relationships) {
            result.add(new Relationship(r.type, r.src, r.dest));
        }

        return result;
    }

    public String methodsToString(String className) {
        String text = "";
        Class c = getClass(className);
        for (Method m : c.methods) {
            text += "  " + m.type + " " + m.name;
            text += " (";
            for (Parameter p : m.parameters) {
                text += p.type + " " + p.name;
                if (m.parameters.indexOf(p) != (m.parameters.size() - 1)) {
                    text += ", ";
                }
            }
            text += ")  \n";

        }
        return text;
    }
}
