import java.util.ArrayList;
import java.util.Scanner;

/*
* attributes.java
* 
* attribute object constructor
*/
public class Attribute {
    private static string class_name;
    private static string name;
    
    /*
    * Attribute: attaches the attribute to the class
    * 
    */
    public Attribute (String attrib, String clas) 
    {
        name = attrib;
        class_name = clas;   
    }

    /*
    * rename_attribute: renames an attribute
    * 
    */
    public static String rename_attribute (String newName) 
    {
        name = newName;
    }

    
}