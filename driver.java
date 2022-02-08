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
}
