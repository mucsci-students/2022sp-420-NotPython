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
    
    /*public void runCLI ()
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
    }*/

    public void runCLITabCompletion ()
    {
        try {
            ConsoleReader console = new ConsoleReader();
            console.setPrompt("> ");
            String input;
            String [] tokens = new String [100];
            CLIController controller = new CLIController(console);
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
