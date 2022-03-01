package UML.model;

import UML.model.*;
import UML.controller.Listing;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Diagram {

    //Arraylist for classes
    public static ArrayList <Class> classList = new ArrayList <Class> ();
    //ArrayList for relationships goes here
    public static ArrayList <Relationship> relationships = new ArrayList <Relationship> ();
    //ArrayList for fields
    public static ArrayList <Field> fields = new ArrayList <Field> ();
    Save save = new Save();
    Load load = new Load();
    //Default constructor
    public Diagram()
    {

    }

    //Create class method
    //Command: Create class <classname>
    public String createClass(String name)
    {   
        String error = validation_check(name);
        //check to see if the name contains any invalid characters
        if (!error.trim().equals(""))
        {
            return error;
        }

        //check to see if class exists already
        if (getClass (name) != null)
        {
            return "ERROR: Class with name \"" + name + "\" already exists";
        }

        //add the new class to the classList
        classList.add(new Class(name));
        return "Class \"" + name + "\" Added";
    }

    //Rename class method
    //Command: rename class <old_name> <new_name>
    public String renameClass(String oldName, String newName)
    {
        //get class with old name
        Class tempClass = getClass(oldName);
        if (tempClass == null)
        {
            return "ERROR: Class \"" + oldName + "\" does not exist";
        }

        //see if a class with new name already exists
        if (getClass(newName) != null)
        {
            return "ERROR: Class with name \"" + newName + "\" already exists";
        }

        String error = validation_check(newName);
        //check to see if the name contains any invalid characters
        if (!error.trim().equals(""))
        {
            return error;
        }

        //change class name and set object again in classlist
        int index = classList.indexOf(tempClass);
        tempClass.rename(newName);

        classList.set(index, tempClass);
        return "Class \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    //Deletes a class and all of it's fields and methods
    //Command: delete class <classname>
    public String deleteClass(String className)
    {
        //if class exists then delete
        Class tempClass = getClass(className);
        if (tempClass == null)
        {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }

        //delete all relationships associated with class <className>
        Relationship tempRelationship;
        for (int i = 0; i < relationships.size(); ++i)
        {
            tempRelationship = relationships.get(i);
            if (tempRelationship.src.equals(className) || tempRelationship.dest.equals(className))
            {
                relationships.remove(tempRelationship);
                --i;
            }
        }

        //delete class
        classList.remove(tempClass);
        return "Class with name \"" + tempClass.name + "\" and its relationships deleted";
    }

    // Create field
    // Command: create field <class_name> <field_name> <field_type>
    public String createField(String clasName, String fldName, String fldType){
        //check to seeif class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            //check to see if the name contains any invalid characters
            String error = validation_check(fldName);
            if (!error.equals(""))
            {
                return error + " in field name";
            }
            //check to see if the type contains any invalid characters
            error = validation_check(fldType);
            if (!error.equals(""))
            {
                return error + " in field type";
            }
            //check to see if field already exists
            if (getField(tempClass, fldName) != null)
            {
                return "ERROR: Field with name \"" + fldName + "\" for \"" + clasName + "\" already exists";
            }

            //Add field to arrayList
            tempClass.fields.add(new Field(fldName, fldType));
            return "Field \"" + fldName + "of type \"" + fldType + "\" Added to Class \"" + clasName + "\"";
        }
        else
        {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    // Delete field
    // Command: delete field <class_name> <field_name>
    public String deleteField(String clasName, String fldName)
    {
        //Check if class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            Field tempFld = getField(tempClass, fldName);
            if (tempFld == null)
            {
                return "ERROR: Field with name \"" + fldName + "\" for \"" + clasName + "\" does not exist";
            }
            //delete field from arraylist
            tempClass.fields.remove(tempFld);
            return "Field \"" + fldName + "\" removed from Class \"" + clasName + "\"";
        }
        else
        {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    // Rename field method
    // Command: rename field <class_name> <old_name> <new_name>
    public String renameField(String clas, String oldName, String newName)
    {
        //Check if class exists
        Class tempClass = getClass(clas);
        if (tempClass == null)
        {
            return "ERROR: Class \"" + clas + "\" does not exist";
        }

        //Check if field with old name exists
        if (getField(tempClass, oldName) == null)
        {
            return "ERROR: Field with name \"" + oldName + "\" does not exist";
        }

        //Check if field with new name already exists
        if (getField(tempClass, newName) != null)
        {
            return "ERROR: Field with name \"" + newName + "\" already exists";
        }

        Field tempFld = getField(tempClass, oldName);
        
        //check to see if the name contains any invalid characters
        String error = validation_check(newName);
        if (!error.trim().equals(""))
        {
            return error;
        }

        //change field name and set object again in classlist
        int index = tempClass.fields.indexOf(tempFld);
        tempFld.rename_field(newName);
        tempClass.fields.set(index, tempFld);
        return "Field \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    //create relationship
    //Command: create relationship <relationship_type> <src> <dest>
    public String createRelationship(String type, String src, String dest)
    {
        //check to see if source exists already
        if (getClass(src) == null)
        {
            return "ERROR: Class with name \"" + src + "\" does not exist";
        }

        //check to see if destination exists already
        if (getClass(dest) == null)
        {
            return "ERROR: Class with name \"" + dest + "\" does not exist";
        }

        //check for correct relationship type
        if (!(type.equalsIgnoreCase("aggregation") || type.equalsIgnoreCase("composition") ||
              type.equalsIgnoreCase("inheritance") || type.equalsIgnoreCase("realization")))
        {
            return "ERROR: Incorrect type: \"" + type + "\" valid types are Aggregation, Composition, Inheritance and Realization";
        }

        //check to see if relationship exists already
        if (getRelationship (src, dest) != null || getRelationship(dest, src) != null)
        {
            return "ERROR: Relationship from " + src + " to " + dest +" of type " + type + " already exists";
        }

        //check for correct relationship type
        if (!(type.equalsIgnoreCase("aggregation") || type.equalsIgnoreCase("composition") ||
              type.equalsIgnoreCase("inheritance") || type.equalsIgnoreCase("realization")))
        {
            return "ERROR: Incorrect type: \"" + type + "\" valid types are Aggregation, Composition, Inheritance and Realization";
        }

        //check to see if relationship exists already
        if (getRelationship (src, dest) != null || getRelationship(dest, src) != null)
        {
            return "ERROR: Relationship from " + src + " to " + dest +" of type " + type + " already exists";
        }

        //add the new relationship to the relationship list
        relationships.add(new Relationship(type, src, dest));
        return "Relationship from " + src + " to " + dest + " of type " + type + " added";
    }


    // delete relationship method
    //Command: delete relationship <src> <dest>
    public String deleteRelationship(String src, String dest)
    {
        //if relationship exists then delete
        Relationship tempRelationship = getRelationship(src, dest);
        if (tempRelationship == null)
        {
            return "ERROR: Relationship from " + src + " to " + dest +" does not exist";
        }

        //delete the relationship from the relationship list
        relationships.remove(tempRelationship);
        return "Relationship from " + src + " to " + dest +" deleted";
    }


    //saves program to .json or .yaml file
    public String saveDiagram(String fileName)
    {
        //makes sure end of file name has .json or .yaml
        if (!(fileName.toLowerCase().contains(".json"))) //|| fileName.toLowerCase().contains(".yaml")))
        {
            return "ERROR: Unsupported file type: please choose .json";
        }
        save.saveFile(fileName, classList, relationships);
        return "Successfully saved to " + fileName;

    }

    //loads diagram from .json or .yaml file
    public String loadDiagram(String fileName)
    {
        Map <ArrayList<Class>, ArrayList<Relationship>> map = new HashMap<ArrayList<Class>, ArrayList<Relationship>>();
        //makes sure end of file name has .json or .yaml
        if (!fileName.toLowerCase().contains(".json"))
        {
            return "ERROR: Unsupported file type: please choose .json";
        }
        
        //read files into data structure
        map = load.loadFile(fileName);
        for (Map.Entry<ArrayList<Class>, ArrayList<Relationship>> iter : map.entrySet())
        {
            classList = iter.getKey();
            relationships = iter.getValue();
            return "Successfully loaded from " + fileName;
        }
        
        return "";
    }

    //List class
    //Command: list class <class_name>
    public void listClass(String className) {
        Listing listing = new Listing();
    	listing.listClass(classList, className);
    }
    
    //List classes
    //Command: list classes
    public void listClasses() {
        Listing listing = new Listing();
    	listing.listClasses(classList);
    }
    
    //List relationships
    //Command: list relationships
    public void listRelationships() {
        Listing listing = new Listing();
    	listing.listRelationships(relationships);
    }

    //Test method to find class
    public static Class getClass (String name)
    {
        for (Class c: classList)
        {
            if (name.equals(c.name))
            {
                return c;
            }
        }
        return null;
    }

    //gets an field with fldName for curClass
    public static Field getField(Class curClass, String fldName)
    {
        //Check if field already exists
        for (Field f: curClass.fields)
        {
            if (fldName.equals(f.name))
            {
                return f;
            }
        }
        return null;
    }

    //get a relationship for with name <name>
    public static Relationship getRelationship(String src, String dest)
    {
        for (Relationship r: relationships)
        {
            if (src.equals(r.src) && dest.equals(r.dest))
            {
                return r;
            }
        }
        return null;
    }

    /*
    * Checks the passed string for invalid characters
    * “`\\|:'\"<.>/?
    */
    public static String validation_check (String input) 
    {
        for (int i = 0; i < input.length(); i++)
        {
            if (" `\\|:'\"<.>/?!".indexOf(input.charAt(i)) > -1)
            {
                return "ERROR: invalid character: " + input.charAt(i);
            }
        }
        return "";
    }
}