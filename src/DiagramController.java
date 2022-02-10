import java.util.ArrayList;

public class DiagramController
{
    //Arraylist for classes
    static ArrayList <Class> classList = new ArrayList <Class> ();
    //ArrayList for relationships goes here

    
    //Default constructor
    public DiagramController()
    {

    }

    //Command: Create class <classname>
    public void createClass(String name)
    {
        //check to see if the name contains any invalid characters
        if (!validation_check(name))
        {
            //need error message here?
            //System.out.println("ERROR: Class Name contains invalid character(s): `\\|:'\"<.>/?");
            return;
        }

        //check to see if class exists already
        if (getClass (name) != null)
        {
            System.out.println("ERROR: Class with name \"" + name + "\" already exists");
            return;
        }

            //add the new class to the classList
            classList.add(new Class(name));
            System.out.println("Class \"" + name + "\" Added");
    }

    //rename class method
    public void renameClass(String oldName, String newName)
    {
        //get class with old name
        Class tempClass = getClass(oldName);
        if (tempClass == null)
        {
            System.out.println("ERROR: Class \"" + oldName + "\" does not exist");
            return;
        }

        //see if a class with new name already exists
        if (getClass(newName) != null)
        {
            System.out.println("ERROR: Class with name \"" + newName + "\" already exists");
            return;
        }

        if (!validation_check(newName))
        {
            System.out.println("ERROR: New name \"" + newName + "\" is an invalid name");
            return;
        }

        //change class name and set object again in classlist
        int index = classList.indexOf(tempClass);
        tempClass.rename(newName);

        classList.set(index, tempClass);
        System.out.println("Class \"" + oldName + "\" was renamed to \"" + newName + "\"");
    }

    //command: delete Class <classname>
    //deletes a class and all of it's attributes
    public void deleteClass(String className)
    {
        //if class exists then delete
        Class tempClass = getClass(className);
        if (tempClass == null)
        {
            System.out.println("ERROR: Class with name \"" + className + "\" does not exist");
        }
        else
        {
            classList.remove(tempClass);
            System.out.println("Class with name \"" + tempClass.name + "\" deleted");
        }
    }

    //Create attribute
    //Command: create attribute <class_name> <attribute_name>
    public void createAttribute(String clasName, String attrName){
        //Check if class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            //Check if attribute name is valid
            if (!validation_check(attrName))
            {
                //System.out.println("ERROR: Attribute name contains invalid character(s): `\\|:'\"<.>/?");
                return;
            }
            if (getAttribute(tempClass, attrName) != null)
            {
                System.out.println("ERROR: Attribute with name \"" + attrName + "\" for \"" + clasName + "\" already exists");
                return;
            }
            //Add attribute to arraylist
            tempClass.attributes.add(new Attribute(attrName));
            System.out.println("Attribute \"" + attrName + "\" Added to Class \"" + clasName + "\"");
        }
        else
        {
            System.out.println("ERROR: Class with name \"" + clasName + "\" does not exist");
        }
    }

    //delete attribute
    //Command: delete attribute <class_name> <attribute_name>
    public void deleteAttribute(String clasName, String attrName)
    {
        //Check if class exists
        Class tempClass = getClass(clasName);
        if(tempClass != null)
        {
            Attribute tempAttr = getAttribute(tempClass, attrName);
            if (tempAttr == null)
            {
                System.out.println("ERROR: Attribute with name \"" + attrName + "\" for \"" + clasName + "\" does not exist");
                return;
            }
            //delete attribute from arraylist
            tempClass.attributes.remove(tempAttr);
            System.out.println("Attribute \"" + attrName + "\" removed from Class \"" + clasName + "\"");
        }
        else
        {
            System.out.println("ERROR: Class with name \"" + clasName + "\" does not exist");
        }
    }

    //rename attribute method
    //Command: Rename attribute <class_name> <old_name> <new_name>
    public void renameAttribute(String clas, String oldName, String newName)
    {
        //Check if class exists
        Class tempClass = getClass(clas);
        if (tempClass == null)
        {
            System.out.println("ERROR: Class \"" + clas + "\" does not exist");
            return;
        }

        //Check if attribute with old name exists
        if (getAttribute(tempClass, oldName) == null)
        {
            System.out.println("ERROR: Attribute with name \"" + oldName + "\" does not exist");
            return;
        }

        //Check if attribute with new name already exists
        if (getAttribute(tempClass, newName) != null)
        {
            System.out.println("ERROR: Attribute with name \"" + newName + "\" already exists");
            return;
        }

        Attribute tempAttr = getAttribute(tempClass, oldName);
        if (!validation_check(newName))
        {
            System.out.println("ERROR: New name \"" + newName + "\" is an invalid name");
            return;
        }

        //change attribute name and set object again in classlist
        int index = tempClass.attributes.indexOf(tempAttr);
        tempAttr.rename_attribute(newName);
        tempClass.attributes.set(index, tempAttr);
        System.out.println("Attribute \"" + oldName + "\" was renamed to \"" + newName + "\"");
    }

    //Test method to find class
    private static Class getClass (String name)
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
    private static Attribute getAttribute(Class curClass, String attrName)
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
    /*
    * Checks the passed string for invalid characters
    * “`\\|:'\"<.>/?
    */
    public static boolean validation_check (String input) 
    {
        for (int i = 0; i < input.length(); i++)
        {
            if (" `\\|:'\"<.>/?".indexOf(input.charAt(i)) > -1)
            {
                System.out.println("ERROR: " + input.charAt(i) + " is an invalid character");
                return false;
            }
        }
        return true;
    }
}
