package UML;

import UML.view.CLI;
import UML.view.GUI;

public class App
{
    public static void main (String [] args)
    {
        CLI cmdInterface = new CLI();
        GUI guiInterface = new GUI();

        if (args.length == 0)
        {
            guiInterface.GUI_view();
        }
        else if (args.length == 1 && args[0].equals("--cli"))
        {
            cmdInterface.runCLITabCompletion();
        }
        else
        {
            System.out.println("Invalid arguments passed to the program");
        }
    }
}