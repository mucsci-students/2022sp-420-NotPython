package UML.controller;

import UML.view.GUI;
import UML.model.Diagram;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import jline.console.ConsoleReader;
import jline.console.completer.*;

//this is the driver class
public class CLIController {
    Diagram dg = new Diagram();
    ConsoleReader console = null;
    ArrayList<String> classNames = null;
    ArrayList<String> methodNames = null;
    ArrayList<String> fieldNames = null;

    public CLIController(ConsoleReader console){
        this.console = console;
        classNames = new ArrayList<>();
        methodNames = new ArrayList<>();
        fieldNames = new ArrayList<>();
        console.addCompleter(getCompleter());
    }


    public void processCommand(String[] tokens) {

        // All create commands go here with an if statement for class, relationship, and
        // attribute as second token
        if (tokens[0].equalsIgnoreCase("Create")) {
            // create class
            // Command: create class <class_name>
            if (lengthChecker(tokens, 3) && tokens[1].equalsIgnoreCase("Class") ) {
                System.out.println(dg.createClass(tokens[2]));

                addClass(tokens[2]);
                this.updateCompleters();

                return;
            }

            // Create Field
            // Command: create field <class_name> <field_name> <field_type> 
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("Field")) {
                System.out.println(dg.createField(tokens[2], tokens[3], tokens[4]));

                addField(tokens[3]);
                this.updateCompleters();

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

                addMethod(tokens[3]);
                this.updateCompleters();

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

                deleteClass(tokens[2]);
                this.updateCompleters();

                return;
            }

            // delete field
            // Command: delete field <class_name> <field_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Field")) {
                System.out.println(dg.deleteField(tokens[2], tokens[3]));

                deleteField(tokens[3]);
                this.updateCompleters();

                return;
            }

            // delete method
            // Command: delete method <class_name> <method_name>
            if (lengthChecker(tokens, 4) && tokens[1].equalsIgnoreCase("Method")) {
                System.out.println(dg.deleteMethod(tokens[2], tokens[3]));

                deleteMethod(tokens[3]);
                this.updateCompleters();
                
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

                deleteClass(tokens[2]);
                addClass(tokens[3]);
                this.updateCompleters();

                return;
            }

            // Rename field
            // Command: Rename field <class_name> <old_name> <new_name>
            if (lengthChecker(tokens, 5) && tokens[1].equalsIgnoreCase("field")) {
                System.out.println(dg.renameField(tokens[2], tokens[3], tokens[4]));

                deleteField(tokens[3]);
                addField(tokens[4]);
                this.updateCompleters();

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

                
                deleteMethod(tokens[3]);
                addMethod(tokens[4]);
                this.updateCompleters();

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
            try{
                System.out.println(dg.saveDiagram(tokens[1]));
            }
            catch (Exception e){
                System.out.println("Save Failed");
            }
            
            return;
        }

        // load file
        // command: load <file_name>
        if (tokens[0].equalsIgnoreCase("load") && lengthChecker(tokens, 2)) {
            try{
                System.out.println(dg.loadDiagram(tokens[1]));
            }
            catch(Exception e){
                System.out.println("Loading Failed");
                e.printStackTrace();
            }
            
            return;
        }

        // export image
        // command: export <file_name>
        if (tokens[0].equalsIgnoreCase("export") && lengthChecker(tokens, 2)) {
            GUI gui = new GUI();
            //Creates a temp file to transfer to GUI 
            try{
                dg.saveDiagram("diagramExporter.json");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            gui.printCLI(tokens[1]);
            //Delete the temp file
            try {
                Files.delete(Paths.get("diagramExporter.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Image Successfully Exported");
            return;
        }

        // export image
        // command: export <file_name>
        if (tokens[0].equalsIgnoreCase("export") && lengthChecker(tokens, 2)) {
            GUI gui = new GUI();
            //Creates a temp file to transfer to GUI 
            try{
                dg.saveDiagram("diagramExporter.json");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            gui.printCLI(tokens[1]);
            //Delete the temp file
            try {
                Files.delete(Paths.get("diagramExporter.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Image Successfully Exported");
            return;
        }

        //undo previous command
        if (lengthChecker (tokens, 1) && tokens[0].equalsIgnoreCase("undo"))
        {
            System.out.println(dg.undo());
            return;
        }

        if (lengthChecker (tokens, 1) && tokens[0].equalsIgnoreCase("redo"))
        {
            System.out.println(dg.redo());
            return;
        }

        // help user
        if (lengthChecker(tokens, 1) && tokens[0].equalsIgnoreCase("Help")) {
            System.out.printf("%-70s\n\t%-50s\n", "COMMANDS", "USAGE");

            System.out.printf("%-70s\n\t%-50s\n", "help", "shows this help message");
            System.out.printf("%-70s\n\t%-50s\n", "undo", "undoes the previous command");
            System.out.printf("%-70s\n\t%-50s\n", "redo", "redoes the previously undone command");
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
            System.out.printf("%-70s\n\t%-50s\n", "export <file_name>", "saves the diagram as a PNG image");

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

    public void addClass(String className){
        classNames.add(className);
    }

    public void addMethod(String methodName){
        methodNames.add(methodName);
    }

    public void addField(String fieldName){
        fieldNames.add(fieldName);
    }

    public void deleteClass(String className){
        for(int i = 0; i < classNames.size(); i++){
            if(classNames.get(i).equals(className)){
                classNames.remove(i);
            }
        }
    }

    public void deleteMethod(String methodName){
        for(int i = 0; i < methodNames.size(); i++){
            if(methodNames.get(i).equals(methodName)){
                methodNames.remove(i);
            }
        }
    }

    public void deleteField(String fieldName){
        for(int i = 0; i < fieldNames.size(); i++){
            if(fieldNames.get(i).equals(fieldName)){
                fieldNames.remove(i);
            }
        }
    }

    private void updateCompleters(){
        Completer[] completers = console.getCompleters().toArray(new Completer[0]);

        console.removeCompleter(completers[0]);

        Completer finalCompleter = getCompleter();

        console.addCompleter(finalCompleter);
    }

    public Completer getCompleter(){

        Completer completer1 = new AggregateCompleter(
            new StringsCompleter("help", "exit", "undo", "redo", "export"),
            new ArgumentCompleter(
                new StringsCompleter("save", "load"),
                new FileNameCompleter(),
                NullCompleter.INSTANCE
            )
        );

        Completer completer2 = new AggregateCompleter(
            new ArgumentCompleter(
                new StringsCompleter("create"),
                new StringsCompleter("class"),
                NullCompleter.INSTANCE
            ),
            new ArgumentCompleter(
                new StringsCompleter("create"),
                new StringsCompleter("field"),
                new StringsCompleter(classNames),
                NullCompleter.INSTANCE
            ),
            new ArgumentCompleter(
                new StringsCompleter("create"),
                new StringsCompleter("method"),
                new StringsCompleter(classNames),
                NullCompleter.INSTANCE
            ),
            new ArgumentCompleter(
                new StringsCompleter("create"),
                new StringsCompleter("relationship"),
                new StringsCompleter("aggregation", "composition", "inheritance", "realization"),
                new StringsCompleter(classNames),
                new StringsCompleter(classNames),
                NullCompleter.INSTANCE
            )
        );

        Completer completer3 = new AggregateCompleter(
            new ArgumentCompleter(
                new StringsCompleter("rename"),
                new StringsCompleter("class"),
                new StringsCompleter(classNames),
                NullCompleter.INSTANCE
            ),
            new ArgumentCompleter(
                new StringsCompleter("rename"),
                new StringsCompleter("field"),
                new StringsCompleter(classNames),
                new StringsCompleter(fieldNames),
                NullCompleter.INSTANCE
            ),
            new ArgumentCompleter(
                new StringsCompleter("rename"),
                new StringsCompleter("method"),
                new StringsCompleter(classNames),
                new StringsCompleter(methodNames),
                NullCompleter.INSTANCE
            )
        );

        Completer completer4 = new ArgumentCompleter(
            new StringsCompleter("change"),
            new StringsCompleter("parameter", "parameters"),
            new StringsCompleter(classNames),
            new StringsCompleter(methodNames),
            NullCompleter.INSTANCE
        );

        Completer completer5 = new ArgumentCompleter(
            new StringsCompleter("list"),
            new StringsCompleter("class", "classes", "relationships"),
            new StringsCompleter(classNames),
            NullCompleter.INSTANCE
        );

        Completer completer6 = new AggregateCompleter(
                new ArgumentCompleter(
                    new StringsCompleter("delete"),
                    new StringsCompleter("class"),
                    new StringsCompleter(classNames),
                    NullCompleter.INSTANCE
                ),
                new ArgumentCompleter(
                    new StringsCompleter("delete"),
                    new StringsCompleter("relationship"),
                    new StringsCompleter(classNames),
                    new StringsCompleter(classNames),
                    NullCompleter.INSTANCE
                ),
                new ArgumentCompleter(
                    new StringsCompleter("delete"),
                    new StringsCompleter("field"),
                    new StringsCompleter(classNames),
                    new StringsCompleter(fieldNames),
                    NullCompleter.INSTANCE
                ),
                new ArgumentCompleter(
                    new StringsCompleter("delete"),
                    new StringsCompleter("method", "parameter", "parameters"),
                    new StringsCompleter(classNames),
                    new StringsCompleter(methodNames),
                    NullCompleter.INSTANCE
                )
        );
        
        Completer completer = new AggregateCompleter(completer1, completer2, completer3, completer4, completer5, completer6);

        return completer;
    }
}