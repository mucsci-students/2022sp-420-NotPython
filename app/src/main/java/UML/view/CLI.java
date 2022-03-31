package UML.view;

import UML.controller.CLIController;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
 
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.*;

public class CLI {
    
    public void runCLI ()
    {
        //create scanner and read next line
        Scanner scanner = new Scanner(System.in);
        String input;
        String [] tokens = new String [100];
        CLIController controller = new CLIController();

        //main loop
        while (true)
        {
            System.out.print("> ");
            input = scanner.nextLine();

            //strip and tokenize input by spaces
            input = input.strip();
            tokens = input.split("\\s+");

            //exit the program
	        if(tokens[0].equalsIgnoreCase("exit")) {
		        break;
            }

            controller.processCommand(tokens);
        }
    }

    public void runCLITabCompletion ()
    {
        try {

            StringsCompleter completer1 = new StringsCompleter("help", "exit");

            ArgumentCompleter completer2 = new ArgumentCompleter(
                new StringsCompleter("create"),
                new StringsCompleter("class", "field", "method", "relationship"),
                NullCompleter.INSTANCE
            );

            ArgumentCompleter completer3 = new ArgumentCompleter(
                new StringsCompleter("rename"),
                new StringsCompleter("class", "field", "method"),
                NullCompleter.INSTANCE
            );

            ArgumentCompleter completer4 = new ArgumentCompleter(
                new StringsCompleter("delete"),
                new StringsCompleter("class", "field", "method", "relationship", "parameter", "parameters"),
                NullCompleter.INSTANCE
            );

            ArgumentCompleter completer5 = new ArgumentCompleter(
                new StringsCompleter("change"),
                new StringsCompleter("parameter", "parameters"),
                NullCompleter.INSTANCE
            );

            ArgumentCompleter completer6 = new ArgumentCompleter(
                new StringsCompleter("list"),
                new StringsCompleter("class", "classes", "relationships"),
                NullCompleter.INSTANCE
            );

            ArgumentCompleter completer7 = new ArgumentCompleter(
                new StringsCompleter("save", "load"),
                new FileNameCompleter(),
                NullCompleter.INSTANCE
            );
            
            Completer completer = new AggregateCompleter(completer1, completer2, completer3, completer4, completer5, completer6, completer7);

            ConsoleReader console = new ConsoleReader();
            console.setPrompt("> ");
            console.addCompleter(completer);
            String input;
            String [] tokens = new String [100];
            CLIController controller = new CLIController();
            while ((input = console.readLine()) != null) {
                input = input.strip();
                tokens = input.split("\\s+");

                //exit the program
                if(tokens[0].equalsIgnoreCase("exit")) {
                    break;
                }

                controller.processCommand(tokens);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
