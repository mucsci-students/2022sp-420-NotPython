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
    Save save = new Save();
    Load load = new Load();
    //Default constructor
    public Diagram()
    {

    }

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

    //rename class method
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

    //command: delete Class <classname>
    //deletes a class and all of it's attributes
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

    //Create attribute
    //Command: create attribute <class_name> <attribute_name>
    public String createAttribute(String clasName, String attrName){
        //Check if class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            //check to see if the name contains any invalid characters
            String error = validation_check(attrName);
            if (!error.trim().equals(""))
            {
                return error;
            }
            if (getAttribute(tempClass, attrName) != null)
            {
                return "ERROR: Attribute with name \"" + attrName + "\" for \"" + clasName + "\" already exists";
            }
            //Add attribute to arraylist
            tempClass.attributes.add(new Attribute(attrName));
            return "Attribute \"" + attrName + "\" Added to Class \"" + clasName + "\"";
        }
        else
        {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    //delete attribute
    //Command: delete attribute <class_name> <attribute_name>
    public String deleteAttribute(String clasName, String attrName)
    {
        //Check if class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            Attribute tempAttr = getAttribute(tempClass, attrName);
            if (tempAttr == null)
            {
                return "ERROR: Attribute with name \"" + attrName + "\" for \"" + clasName + "\" does not exist";
            }
            //delete attribute from arraylist
            tempClass.attributes.remove(tempAttr);
            return "Attribute \"" + attrName + "\" removed from Class \"" + clasName + "\"";
        }
        else
        {
            return "ERROR: Class with name \"" + clasName + "\" does not exist";
        }
    }

    //rename attribute method
    //Command: Rename attribute <class_name> <old_name> <new_name>
    public String renameAttribute(String clas, String oldName, String newName)
    {
        //Check if class exists
        Class tempClass = getClass(clas);
        if (tempClass == null)
        {
            return "ERROR: Class \"" + clas + "\" does not exist";
        }

        //Check if attribute with old name exists
        if (getAttribute(tempClass, oldName) == null)
        {
            return "ERROR: Attribute with name \"" + oldName + "\" does not exist";
        }

        //Check if attribute with new name already exists
        if (getAttribute(tempClass, newName) != null)
        {
            return "ERROR: Attribute with name \"" + newName + "\" already exists";
        }

        Attribute tempAttr = getAttribute(tempClass, oldName);
        
        //check to see if the name contains any invalid characters
        String error = validation_check(newName);
        if (!error.trim().equals(""))
        {
            return error;
        }

        //change attribute name and set object again in classlist
        int index = tempClass.attributes.indexOf(tempAttr);
        tempAttr.rename_attribute(newName);
        tempClass.attributes.set(index, tempAttr);
        return "Attribute \"" + oldName + "\" was renamed to \"" + newName + "\"";
    }

    //create relationship
<<<<<<< Updated upstream
    //add functionality for type with name in next sprint
    public void createRelationship(/*String name,*/ String src, String dest)
=======
    public String createRelationship(String type, String src, String dest)
>>>>>>> Stashed changes
    {
        //check to see if relationship exists already
        if (getRelationship (src, dest) != null)
        {
            System.out.println("ERROR: Relationship from " + src + " to " + dest +" already exists");
            return;
        }

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

<<<<<<< Updated upstream
        //add the new relationship to the relationship list
        relationships.add(new Relationship(src, dest));
        System.out.println("Relationship from " + src + " to " + dest +" added");
=======
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
>>>>>>> Stashed changes
    }

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

    //gets an attribute with attrName for curClass
    public static Attribute getAttribute(Class curClass, String attrName)
    {
        //Check if attribute already exists
        for (Attribute a: curClass.attributes)
        {
            if (attrName.equals(a.name))
            {
                return a;
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
                return "ERROR: " + input.charAt(i) + " is an invalid character";
            }
        }
        return "";
    }
}