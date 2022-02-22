package UML;

import UML.view.CLI;

public class App
{
    public static void main (String [] args)
    {
        CLI cmdInterface = new CLI();
        //create gui here

        if (args.length == 0)
        {
            //run gui here
        }
        else if (args.length == 1 && args[0].equals("--cli"))
        {
            cmdInterface.runCLI();
        }
        else
        {
            System.out.println("Invalid arguments passed to the program");
        }
    }
}