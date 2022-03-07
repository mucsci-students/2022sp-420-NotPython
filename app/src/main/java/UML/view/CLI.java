package UML.view;

import UML.controller.CLIController;
import java.util.Scanner;

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
}
