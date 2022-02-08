import java.util.ArrayList;
import java.util.Scanner;

/*
* attributes.java
* 
* attribute object constructor
*/
public class Attribute {
    public static String name;
    
    /*
    * Attribute: attaches the attribute to the class
    * 
    */
    public Attribute (String attrib) 
    {
        name = attrib;
    }

    /*
    * rename_attribute: renames an attribute
    * 
    */
    public static void rename_attribute (String newName) 
    {
        name = newName;
    }

    
}