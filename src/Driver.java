import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
 
//this is the driver class
public class Driver {
	
    public static void main(String[] args) {

        //create scanner and read next line
        Scanner scanner = new Scanner(System.in);
        String input;
        String [] tokens = new String [100];
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
                if(tokens[1].equalsIgnoreCase("Class"))
                {
                    dc.createClass(tokens[2]);
                    continue;
                }

                //Create Attribute
                //Command: create attribute <class_name> <attribute_name>
                if(tokens[1].equalsIgnoreCase("Attribute"))
                {
                    //Check for valid input length
                    if (tokens.length < 4)
                    {
                        System.out.println("ERROR: Input contains too few keywords");
                        continue;
                    }
                    if (tokens.length > 4)
                    {
                        System.out.println("ERROR: Input contains too many keywords");
                        continue;
                    }

                    dc.createAttribute(tokens[2], tokens[3]);
                    continue;
                }
            }

            //All delete commands go here with an if statement for class, relationship, and attribute as second token
            if(tokens[0].equalsIgnoreCase("Delete"))
            {
            //delete class
                if(tokens[1].equalsIgnoreCase("Class"))
                {
                    dc.deleteClass(tokens[2]);
                    continue;
                }
            }
            System.out.println("ERROR: Command \"" + input + "\" is invalid");
        }
    }
}
