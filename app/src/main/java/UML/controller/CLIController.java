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
            if (tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 3)) {
                System.out.println(dg.createClass(tokens[2]));
                return;
            }

            // Create Field
            // Command: create field <class_name> <field_name> <field_type> 
            if (tokens[1].equalsIgnoreCase("Field") && lengthChecker(tokens, 5)) {
                dg.createField(tokens[2], tokens[3], tokens[4]);
                return;
            }

            // Create Relationship
            // Command: create relationship <relationship_type> <src> <dest>
            if (tokens[1].equalsIgnoreCase("Relationship") && lengthChecker(tokens, 5)) {
                System.out.println(dg.createRelationship(tokens[2], tokens[3], tokens[4]));
                return;
            }
        }

        // All delete commands go here with an if statement for class, relationship, and
        // attribute as second token
        if (tokens[0].equalsIgnoreCase("Delete")) {
            // delete class
            // Command: delete class <class_name>
            if (tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 3)) {
                System.out.println(dg.deleteClass(tokens[2]));
                return;
            }

            // delete field
            // Command: delete field <class_name> <field_name>
            if (tokens[1].equalsIgnoreCase("Field") && lengthChecker(tokens, 4)) {
                dg.deleteField(tokens[2], tokens[3]);
                return;
            }

            // delete relationship
            // Command: delete relationship <relationship_name>
            if (tokens[1].equalsIgnoreCase("Relationship") && lengthChecker(tokens, 4)) {
                System.out.println(dg.deleteRelationship(tokens[2], tokens[3]));
                return;
            }
        }

        if (tokens[0].equalsIgnoreCase("Rename")) {
            // Rename class
            // Command: Rename Class <old_name> <new_name>
            if (tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 4)) {
                System.out.println(dg.renameClass(tokens[2], tokens[3]));
                return;
            }

            // Rename field
            // Command: Rename field <class_name> <old_name> <new_name>
            if (tokens[1].equalsIgnoreCase("field") && lengthChecker(tokens, 5)) {
                dg.renameField(tokens[2], tokens[3], tokens[4]);
                return;
            }

            // Rename method
            // Command: Rename method <class_name> <old_name> <new_name>
            //if (tokens[1].equalsIgnoreCase("method") && lengthChecker(tokens, 5)) {
            //    dg.renameMethod(tokens[2], tokens[3], tokens[4]);
            //    return;
            //}
        }

        // All list commands go here with an if statement for class, classes and
        // relationships as second token
        if (tokens[0].equalsIgnoreCase("List")) {
            // List class
            // Command: list class <class_name>
            if (tokens[1].equalsIgnoreCase("Class") && lengthChecker(tokens, 3)) {
                dg.listClass(tokens[2]);
                return;
            }

            // List classes
            // Command: list classes
            if (tokens[1].equalsIgnoreCase("Classes") && lengthChecker(tokens, 2)) {
                dg.listClasses();
                return;
            }

            // List relationships
            // Command: list relationships
            if (tokens[1].equalsIgnoreCase("Relationships") && lengthChecker(tokens, 2)) {
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
        if (tokens[0].equalsIgnoreCase("Help")) {
            System.out.printf("%-60s%-50s\n", "COMMANDS", "USAGE");

            System.out.printf("%-60s%-50s\n", "help", "shows this help message");
            System.out.printf("%-60s%-50s\n", "create class <class_name>", "creates a class with the given name");
            System.out.printf("%-60s%-50s\n", "rename class <old_name> <new_name>", "renames the class");
            System.out.printf("%-60s%-50s\n", "delete class <class_name>", "deletes the class");

            System.out.printf("%-60s%-50s\n", "create relationship <type> <src> <dest>",
                    "creates a relationship given src and dest classes and a type: Aggregation, Composition, Inheritance or Realization");
            System.out.printf("%-60s%-50s\n", "delete relationship <src> <dest>",
                    "deletes a relationship given its src and dest");

            System.out.printf("%-60s%-50s\n", "create field <class_name> <field_name> <field_type>", "creates a field");
            System.out.printf("%-60s%-50s\n", "rename field <class_name> <old_name> <new_name>",
                    "renames an field");
            System.out.printf("%-60s%-50s\n", "delete field <class_name> <field_name>", "deletes a field");

            System.out.printf("%-60s%-50s\n", "save <file_name>", "saves a file to a JSON/ YAML format");
            System.out.printf("%-60s%-50s\n", "load <file_name>", "loads a file from a JSON/ YAML format");

            System.out.printf("%-60s%-50s\n", "list class <class_name>",
                    "lists the contents of a class given its name");
            System.out.printf("%-60s%-50s\n", "list classes", "lists all the classes and contents");
            System.out.printf("%-60s%-50s\n", "list relationships", "lists the relationships between classes");
            System.out.printf("%-60s%-50s\n", "exit", "exits the program");

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
            System.out.println("ERROR: Input contains too few keywords");
            return false;
        }
        if (tokens.length > size) {
            System.out.println("ERROR: Input contains too many keywords");
            return false;
        }
        return true;
    }
}