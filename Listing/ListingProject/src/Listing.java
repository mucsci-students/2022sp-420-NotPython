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
		System.out.println("|                |");
		printRow(className);
		System.out.println("|----------------|");
		
		for(String att: attributesList) {
			printRow(att);
		}
		System.out.println("|________________|");
	}
	
	private static void printRow(String phrase) {
		int blankSpaces = 16;
		int remaining = blankSpaces-phrase.length();
		int counter = 1;
		
		System.out.print("|");
		while(counter <= remaining/2) {
			System.out.print(" ");
			counter++;
		}
		System.out.print(phrase);
		for(int i = 0; i <= remaining-counter; i++) {
			System.out.print(" ");
		}
		System.out.println("|");
	}
	
	public void listClasses() {
		
	}
	
	public void listRelationships() {
		
	}

}
