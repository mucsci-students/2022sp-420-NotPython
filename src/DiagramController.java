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
            //delete attribute to arraylist
            tempClass.attributes.remove(tempAttr);
            System.out.println("Attribute \"" + attrName + "\" removed from Class \"" + clasName + "\"");
        }
        else
        {
            System.out.println("ERROR: Class with name \"" + clasName + "\" does not exist");
        }
    }

    //Test method find class
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
