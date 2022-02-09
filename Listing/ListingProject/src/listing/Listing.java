package listing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listing {
	
	
	//Listing class: Code to be developed
	
	public static void listClass(ArrayList<Class> list, String className) {
		
		if(Driver.validation_check(className)) {
			
			Class classAux;
			
			for(int i = 0; i < list.size(); i++) {
				classAux = list.get(i);
				if(classAux.name.equals(className)) {
					
					System.out.println(" ________________");
					printRow(classAux.name, 0, false);
					System.out.println("|================|");
					
					for(Attribute attr: classAux.attributes) {
						printRow(attr.name, 1, false);
					}
					
					System.out.println("|________________|");
				}
			}
		}
	}
	
	public static void listClasses(ArrayList<Class> list) {
		for(Class classSample: list) {
			System.out.println(" ________________");
			printRow(classSample.name, 0, false);
			System.out.println("|================|");
			
			classSample.attributes.add(new Attribute("Gold"));
			
			for(Attribute attr: classSample.attributes) {
				printRow(attr.name, 1, false);
			}
			
			System.out.println("|________________|");
		}
	}
	
	public static void listRelationships() {
		System.out.println(" ________________                              ________________");
		printRelationship("Car", "Plane");
		System.out.println("|________________|                            |________________|");
		
		System.out.println(" ________________                              ________________");
		printRelationship("Train", "Boat");
		System.out.println("|________________|                            |________________|");
		
	}
	
	public static void printRelationship(String class1, String class2) {
		printRow(class1, 0, true);
		System.out.print("     <----Related----->     ");
		printRow(class2, 0, true);
		System.out.println("");
	}
	
	private static void printRow(String phrase, int isAttribute, boolean isRelationship) {
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
		if(isRelationship)
			System.out.print("|");
		else
			System.out.println("|");
	}

}