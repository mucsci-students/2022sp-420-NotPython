import java.io.File;
import java.util.Scanner;

public class driver {

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