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
    //deletes a class
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

    //create relationship
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

    // Create Method
    // Command: create method <class_name> <method_name> <type> <param>
    public String createMethod(String className, String type, String methodName, String[] param)
    {
        ArrayList <String> parameter = new ArrayList <String> ();
        Class c = getClass(className);
        //checks if class exists
        if(c == null)
        {
            return "ERROR: Class with name \"" + className + "\" does not exist";
        }
        //checks if type is valid
        String error = validation_check(type);
        if(!error.equals(""))
        {
            return error + " in method type";
        }
        //checks if method name is valid
        error = validation_check(methodName);
        if(!error.equals(""))
        {
            return error + " in method name";
        }
        //checks parameter list
        for(int i = 5; i < param.length - 1; i += 2)
        {
            error = validation_check(param[i]);
            if(!error.equals(""))
            {
                return error + " in method parameter name";
            }
            error = validation_check(param[i + 1]);
            if(!error.equals(""))
            {
                return error + " in method parameter type";
            }
            parameter.add(param[i]);
            parameter.add(param[i + 1]);
        }
        //check if method exists
        if(getMethod(className, methodName, type, parameter) != null)
        {
            return "ERROR: method already exists";
        }
        c.methods.add(new Method(type, methodName, parameter));
        return "Method with name \"" + methodName + "\" added to class \"" + className + "\"";
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

    //get a relationship with name <name>
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

    //get a method with class name, method name, type, and parameter
    public static Method getMethod(String className, String methodName, String type, ArrayList <String> param)
    {
        Parameter p;
        int counter = 0;
        int neflag = 0;
        Class c = getClass(className);
        if(c != null)
        {
            for(Method m : c.methods)
            {
                if(m.name.equals(methodName) && m.type.equals(type) && !m.parameters.isEmpty() && !param.isEmpty() && m.parameters.size() == (param.size() / 2))
                {
                    for(int i = 0; i < param.size() - 1; i += 2)
                    {
                        p = m.parameters.get(counter);
                        if(p.name.equals(param.get(i + 1)) && p.type.equals(param.get(i)))
                        {
                            neflag++;
                        }
                        ++counter;
                    }
                    if(counter == neflag)
                    {
                        return m;
                    }
                }
                else if(m.name.equals(methodName) && m.type.equals(type) && m.parameters.isEmpty() && param.isEmpty())
                {
                    return m;
                }
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