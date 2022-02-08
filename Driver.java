import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
 
//this is the driver class
public class Driver {
    //Arraylist for classes
    static ArrayList <Class> classList = new ArrayList <Class> ();
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
