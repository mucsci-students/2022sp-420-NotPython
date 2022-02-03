import java.util.ArrayList;
import java.util.Scanner;

public class Attribute {
    private static string class_name;
    private static string name;
    
    
    public Attribute (String attrib, String clas) 
    {
        name = attrib;
        class_name = clas;   
    }

    public static String rename_attribute (String newName) 
    {
        name = newName;
    }

    /*
    * Checks the passed string for invalid characters
    * “`\\|:'\"<.>/?
    */
    public static void validation_check (String input) 
    {
        for (int i; i < input.length(); i++)
        {
            if (" `\\|:'\"<.>/?".indexOf(input.charAt(i)) >= -1)
            {
                System.out.println("ERROR: " + input.charAt(i) + " is an invalid character");
            }
        }
    }


    public static void main (String[] args) 
    {

    }

}