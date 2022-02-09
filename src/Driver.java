import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
 
//this is the driver class
public class Driver {
	
    public static void main(String[] args) {

        //create scanner and read next line
        Scanner scanner = new Scanner(System.in);
        String input;
        String [] tokens = new String [10];
        DiagramController dc = new DiagramController();

        //main loop
        while(true) {
            //read in next line of input
            input = scanner.nextLine();

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
                //Command: create class <class_name>
                if(tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 3))
                {
                    dc.createClass(tokens[2]);
                    continue;
                }

                //Create Attribute
                //Command: create attribute <class_name> <attribute_name>
                if(tokens[1].equalsIgnoreCase("Attribute") && lengthChecker(tokens, 4))
                {
                    dc.createAttribute(tokens[2], tokens[3]);
                    continue;
                }
            }

            //All delete commands go here with an if statement for class, relationship, and attribute as second token
            if(tokens[0].equalsIgnoreCase("Delete"))
            {
                //delete class
                //Command: delete class <class_name>
                if(tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 3))
                {
                    dc.deleteClass(tokens[2]);
                    continue;
                }

                //delete attribute
                //Command: delete attribute <class_name> <attribute_name>
                if(tokens[1].equalsIgnoreCase("Attribute") && lengthChecker(tokens, 4))
                {
                    dc.deleteAttribute(tokens[2], tokens[3]);
                    continue;
                }

            }
            System.out.println("ERROR: Command \"" + input + "\" is invalid");
        }
    }

    public static boolean lengthChecker (String [] tokens, int size)
    {
        //Check for valid input length
        if (tokens.length < size)
        {
            System.out.println("ERROR: Input contains too few keywords");
            return false;
        }
        if (tokens.length > size)
        {
            System.out.println("ERROR: Input contains too many keywords");
            return false;
        }
        return true;
    }
}
