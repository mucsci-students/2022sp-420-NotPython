package listing;

import java.util.Arrays;
import java.util.List;

public class Listing {
	
	
	//Listing class: Code to be developed
	
	public static void listClass(String className) {
		
		List<String> attributesList = Arrays.asList("sup1", "sup2", "sup3");
		//Check class exits
		
		//Get all the info
		
		//Print
		

		System.out.println(" ________________");
		printRow(className, 0);
		System.out.println("|================|");
		
		for(String att: attributesList) {
			printRow(att, 1);
		}
		System.out.println("|________________|");
	}
	
	private static void printRow(String phrase, int isAttribute) {
		int blankSpaces = 16;
		int remaining = blankSpaces-phrase.length()-(isAttribute*3);
		int counter = 1;
		
		System.out.print("|");
		while(counter <= remaining/2) {
			System.out.print(" ");
			counter++;
		}
		if(isAttribute==0)
			System.out.print(phrase);
		else
			System.out.print("<> " + phrase);
		for(int i = 0; i <= remaining-counter; i++) {
			System.out.print(" ");
		}
		System.out.println("|");
	}
	
	public static void listClasses() {

	}
	
	public static void listRelationships() {

	}

}