import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
 
//this is the driver class
public class Driver {
    //Arraylist for classes
    static ArrayList <Class> classList = new ArrayList <Class> ();
    //Arraylist for attributes
    static ArrayList <Attribute> attributeList = new ArrayList <Attribute> ();
    //ArrayList for relationships goes here
	
    public static void main(String[] args) {

        //create scanner and read next line
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String [] tokens = new String [100];

        //main loop
        while(true) {
            //strip and tokenize input by spaces
            input.strip();
            tokens = input.split(" ");
            
            //exit the program
	        if(tokens[0].equalsIgnoreCase("exit")) {
		        break;
            }

            //All create commands go here with an if statement for class, relationship, and attribute as second token
            if(tokens[0].equalsIgnoreCase("Create"))
            {
                //create class
                if(tokens[1].equalsIgnoreCase("Class"))
                {
                    if(createClass(tokens[2]))
                    {
                        System.out.println("Class \"" + tokens[2] + "\" Added");
                    }
                }

                //Create Attribute
                //Command: create attribute <class_name> <attribute_name>
                if(tokens[1].equalsIgnoreCase("Attribute"))
                {
                    //Check for valid input length
                    if (tokens.length < 3)
                    {
                        System.out.println("ERROR: Input contains too few keywords");
                        continue;
                    }
                    if (tokens.length > 3)
                    {
                        System.out.println("ERROR: Input contains too many keywords");
                        continue;
                    }
                    if(createAttribute(tokens[2], tokens[3]))
                    {
                        System.out.println("Attribute \"" + tokens[3] + "\" Added to Class \"" + tokens[2] + "\"");
                    }
                }
            }
            //read in next line of input
            input = scanner.nextLine();
        }
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

    //Command: create attribute <class_name> <attribute_name>
    public static boolean createAttribute(String clasName, String attrName){
        //Check if class exists
        if(classExists(clasName))
        {
            //Check if attribute already exists
            for (Attribute a: attributeList)
            {
                if (attrName.equals(a.name))
                {
                    System.out.println("ERROR: Attribute with name \"" + attrName + "\" already exists");
                    return false;
                }
            }
            //Check if attribute name is valid
            if (!validation_check(attrName))
            {
                System.out.println("ERROR: Attribute name contains invalid character(s): `\\|:'\"<.>/?");
                return false;
            }
            //Add attribute to arraylist
            attributeList.add(new Attribute(attrName));
            return true;
        }
        System.out.println("ERROR: Class with name \"" + clasName + "\" does not exist");
        return false;
    }

    // Checks if a class already exists
    // Returns true if it exists
    public static boolean classExists(String name)
    {
        for (Class c: classList)
        {
            if (name.equals(c.name))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean createClass(String name)
    {
        //check to see if the name contains any invalid characters
        if (!validation_check(name))
        {
            //need error message here?
            //System.out.println("ERROR: Class Name contains invalid character(s): `\\|:'\"<.>/?");
            return false;
        }

        //check to see if class exists already
        for (Class c: classList)
        {
            if (name.equals(c.name))
            {
                System.out.println("ERROR: Class with name \"" + name + "\" already exists");
                return false;
            }
        }

        //add the new class to the classList
        classList.add(new Class(name));
        return true;
    }
}
