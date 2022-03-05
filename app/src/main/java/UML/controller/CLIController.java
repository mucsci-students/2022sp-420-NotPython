package UML.controller;

import UML.model.Diagram;
import java.io.File;
import java.util.ArrayList;

//this is the driver class
public class CLIController {

    public void processCommand(String[] tokens) {

        Diagram dg = new Diagram();
        // All create commands go here with an if statement for class, relationship, and
        // attribute as second token
        if (tokens[0].equalsIgnoreCase("Create")) {
            // create class
            // Command: create class <class_name>
            if (lengthChecker(tokens, 3) && tokens[1].equalsIgnoreCase("Class") ) {
                System.out.println(dg.createClass(tokens[2]));
                return;
            }

            // Create Field
            // Command: create field <class_name> <field_name> <field_type> 
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("Field")) {
                System.out.println(dg.createField(tokens[2], tokens[3], tokens[4]));
                return;
            }

            // Create Relationship
            // Command: create relationship <relationship_type> <src> <dest>
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("Relationship")) {
                System.out.println(dg.createRelationship(tokens[2], tokens[3], tokens[4]));
                return;
            }

            // Create Method
            // Command: create method <class_name> <method_name> <type> <param>
            if (tokens.length > 4 && tokens[1].equalsIgnoreCase("Method")) {
                ArrayList <String> params = new ArrayList <String> ();
                for(int i = 5; i < tokens.length; i++)
                {
                    params.add(tokens[i]);
                }
                System.out.println(dg.createMethod(tokens[2], tokens[4], tokens[3], params));
                return;
            }
        }

        // All delete commands go here with an if statement for class, relationship, and
        // attribute as second token
        if (tokens[0].equalsIgnoreCase("Delete")) {
            // delete class
            // Command: delete class <class_name>
            if (lengthChecker(tokens, 3) && tokens[1].equalsIgnoreCase("Class")) {
                System.out.println(dg.deleteClass(tokens[2]));
                return;
            }

            // delete field
            // Command: delete field <class_name> <field_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Field")) {
                System.out.println(dg.deleteField(tokens[2], tokens[3]));
                return;
            }

            // delete method
            // Command: delete method <class_name> <method_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Method")) {
                System.out.println(dg.deleteMethod(tokens[2], tokens[3]));
                return;
            }

            // delete relationship
            // Command: delete relationship <relationship_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Relationship")) {
                System.out.println(dg.deleteRelationship(tokens[2], tokens[3]));
                return;
            }

            // Delete parameter
            // Command: Delete parameter <class_name> <method_name>  <parameter>
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("Parameter")) {
                System.out.println(dg.deleteParameter(tokens[2], tokens[3], tokens[4]));
                return;
            }

            // Delete parameters
            // Command: Delete parameters <class_name> <method_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Parameters")) {
                System.out.println(dg.deleteParameters(tokens[2], tokens[3]));
                return;
            }
        }

        if (tokens[0].equalsIgnoreCase("Rename")) {
            // Rename class
            // Command: Rename Class <old_name> <new_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Class")) {
                System.out.println(dg.renameClass(tokens[2], tokens[3]));
                return;
            }

            // Rename field
            // Command: Rename field <class_name> <old_name> <new_name>
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("field")) {
                System.out.println(dg.renameField(tokens[2], tokens[3], tokens[4]));
                return;
            }

            // Rename method
            // Command: Rename method <class_name> <old_name> <new_name> <type> <param> 
            if (tokens.length > 4 && tokens[1].equalsIgnoreCase("method")) {
                ArrayList <String> parameter = new ArrayList <String> ();
                for(int i = 5; i < tokens.length - 5; i += 2)
                {
                    parameter.add(tokens[i]);
                    parameter.add(tokens[i + 1]);
                }   
                System.out.println(dg.renameMethod(tokens[2], tokens[3], tokens[4]));
                return;
            }
        }


        if (tokens[0].equalsIgnoreCase("Change")) {
            // Change single parameter
            // Command: Change parameter <class_name> <method_name> <old_parameter> <new_parameter> <new_parameter_type> 
            if (lengthChecker(tokens, 7) && tokens[1].equalsIgnoreCase("Parameter")) {
                System.out.println(dg.changeParameter(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]));
                return;
            }

            // Change list of parameters
            // Command: Change parameters <class_name> <method_name> <param>
            if (tokens.length > 3 && tokens[1].equalsIgnoreCase("Parameters")) {
                System.out.println(dg.changeParameters(tokens[2], tokens[3], tokens));
                return;
            }

        }

        // All list commands go here with an if statement for class, classes and
        // relationships as second token
        if (tokens[0].equalsIgnoreCase("List")) {
            // List class
            // Command: list class <class_name>
            if (lengthChecker(tokens, 3) && tokens[1].equalsIgnoreCase("Class")) {
                dg.listClass(tokens[2]);
                return;
            }

            // List classes
            // Command: list classes
            if (lengthChecker(tokens, 2) && tokens[1].equalsIgnoreCase("Classes")) {
                dg.listClasses();
                return;
            }

            // List relationships
            // Command: list relationships
            if (lengthChecker(tokens, 2) && tokens[1].equalsIgnoreCase("Relationships")) {
                dg.listRelationships();
                return;
            }
        }

        // save file
        // command: save <file_name>
        if (tokens[0].equalsIgnoreCase("Save") && lengthChecker(tokens, 2)) {
            System.out.println(dg.saveDiagram(tokens[1]));
            return;
        }

        // load file
        // command: load <file_name>
        if (tokens[0].equalsIgnoreCase("load") && lengthChecker(tokens, 2)) {
            System.out.println(dg.loadDiagram(tokens[1]));
            return;
        }
        // help user
        if (lengthChecker(tokens, 1) && tokens[0].equalsIgnoreCase("Help")) {
            System.out.printf("%-70s\n\t%-50s\n", "COMMANDS", "USAGE");

            System.out.printf("%-70s\n\t%-50s\n", "help", "shows this help message");
            System.out.printf("%-70s\n\t%-50s\n", "create class <class_name>", "creates a class with the given name");
            System.out.printf("%-70s\n\t%-50s\n", "rename class <old_name> <new_name>", "renames the class");
            System.out.printf("%-70s\n\t%-50s\n", "delete class <class_name>", "deletes the class");

            System.out.printf("%-70s\n\t%-50s\n", "create relationship <type> <src> <dest>",
                    "creates a relationship given src and dest classes");
            System.out.printf("\t%-70s\n", "and a type: Aggregation, Composition, Inheritance or Realization");
            System.out.printf("%-70s\n\t%-50s\n", "delete relationship <src> <dest>",
                    "deletes a relationship given its src and dest");

            System.out.printf("%-70s\n\t%-50s\n", "create field <class_name> <field_name> <field_type>", "creates a field");
            System.out.printf("%-70s\n\t%-50s\n", "rename field <class_name> <old_name> <new_name>",
                    "renames an field");
            System.out.printf("%-70s\n\t%-50s\n", "delete field <class_name> <field_name>", "deletes a field");

            System.out.printf("%-70s\n\t%-50s\n", "create method <class_name> <method_name> <parameters>", "creates a method");
            System.out.printf("%-70s\n\t%-50s\n", "delete method <class_name> <method_name> <parameters>", "deletes a method");
            System.out.printf("%-70s\n\t%-50s\n", "rename method <class_name> <old_name> <param> <new_name>", "renames a field");

            System.out.printf("%-70s\n\t%-50s\n", "change parameter <class_name> <method_name> <old_parameter> <new_parameter> <new_parameter_type>", 
                    "changes a single parameter");
            System.out.printf("%-70s\n\t%-50s\n", "change parameters <class_name> <method_name> <parameters>", 
                    "changes the whole list of parameters");

            System.out.printf("%-70s\n\t%-50s\n", "delete parameter <class_name> <method_name> <parameter>", "deletes a single parameter");
            System.out.printf("%-70s\n\t%-50s\n", "delete parameters <class_name> <method_name>", "deletes the whole list of parameters");

            System.out.printf("%-70s\n\t%-50s\n", "save <file_name>", "saves a file to a JSON format");
            System.out.printf("%-70s\n\t%-50s\n", "load <file_name>", "loads a file from a JSON format");

            System.out.printf("%-70s\n\t%-50s\n", "list class <class_name>",
                    "lists the contents of a class given its name");
            System.out.printf("%-70s\n\t%-50s\n", "list classes", "lists all the classes and contents");
            System.out.printf("%-70s\n\t%-50s\n", "list relationships", "lists the relationships between classes");
            System.out.printf("%-70s\n\t%-50s\n", "exit", "exits the program");

            return;
        }

        if (tokens[0].trim().equals(""))
            return;

        System.out.print("ERROR: Command \"");
        for (int i = 0; i < tokens.length; ++i)
        {
            if (!tokens[i].trim().equals(""))
                System.out.print(tokens[i]);
            else
                break;

            if (i < (tokens.length - 1))
            {
                System.out.print(" ");
            }
        }
        System.out.println("\" is invalid");

    }

    public static boolean lengthChecker(String[] tokens, int size) {
        // Check for valid input length
        if (tokens.length < size) {
            //System.out.println("ERROR: Input contains too few keywords");
            return false;
        }
        if (tokens.length > size) {
            //System.out.println("ERROR: Input contains too many keywords");
            return false;
        }
        return true;
    }
}