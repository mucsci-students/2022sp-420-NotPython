import java.io.File;
import java.util.Scanner;
 
//this is the driver class
public class Driver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while(true) {   
	        if(input=="exit") {
		        break;
            }
            input = scanner.next();
        }
    }

    /*
    * Checks the passed string for invalid characters
    * “`\\|:'\"<.>/?
    */
    public static void validation_check (String input) 
    {
        for (int i = 0; i < input.length(); i++)
        {
            if (" `\\|:'\"<.>/?".indexOf(input.charAt(i)) >= -1)
            {
                System.out.println("ERROR: " + input.charAt(i) + " is an invalid character");
            }
        }
    }
}