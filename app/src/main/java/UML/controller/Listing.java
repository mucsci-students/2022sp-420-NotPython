package UML.controller;

import java.util.ArrayList;

import UML.model.Class;
import UML.model.Diagram;
import UML.model.Field;
import UML.model.Method;
import UML.model.Parameter;
import UML.model.Relationship;

public class Listing {
	
	
	//Listing class: List the content of a class defined by the user
	
	public static void listClass(ArrayList<Class> list, String className) {
		
		//Check the class name is valid
		if(Diagram.validation_check(className).equals("")) {
			
			Class classAux;
			
			//Look for the class info
			for(int i = 0; i < list.size(); i++) {
				classAux = list.get(i);
				if(classAux.name.equals(className)) {
					
					int boxLength = maximumLength(classAux) + 10;
					
					System.out.print(" ");
					for(int j = 0; j < boxLength; j++) {
						System.out.print("_");
					}
					System.out.println("");
					
					printRow(classAux.name, boxLength, false);
					
					System.out.print("|");
					for(int j = 0; j < boxLength; j++) {
						System.out.print("=");
					}
					System.out.println("|");
					
					for(Field fld: classAux.fields) {
						printRow(fld.type + " " + fld.name, boxLength, false);
					}

					String methodString = "";
					for(Method m: classAux.methods) {
						int counter = 0;
						methodString = m.type + " " + m.name + " (";
						for(Parameter p: m.parameters) {
							methodString += p.type + " " + p.name;
							if(counter < m.parameters.size() - 1) {
								methodString += ", ";
							}
							counter++;
						}
						methodString += ")";
						printRow(methodString, boxLength, false);
					}

					System.out.print("|");
					for(int j = 0; j < boxLength; j++) {
						System.out.print("_");
					}
					System.out.println("|");
				}
			}
		}
	}
	
	// List classes: List the content of all the existing classes
	public static void listClasses(ArrayList<Class> list) {
		//Iterate through all the classes and print its content
		for(Class classSample: list) {
			listClass(list, classSample.name);
		}
	}
	
	//List relationships: List all the exiting relationships
	
	public static void listRelationships(ArrayList<Relationship> list) {
		//Iterate through all the relationships and print its content
		for(Relationship relationshipSample: list) {
			
			int length1 = relationshipSample.src.length() + 10;
			
			System.out.print(" ");
			for(int j = 0; j < length1; j++) {
				System.out.print("_");
			}
			
			for(int j = 0; j < 38; j++) {
				System.out.print(" ");
			}
			
			int length2 = relationshipSample.dest.length() + 10;
			
			System.out.print("  ");
			for(int j = 0; j < length2; j++) {
				System.out.print("_");
			}
			
			System.out.println("");			
			
			printRelationship(relationshipSample.type, relationshipSample.src, relationshipSample.dest);
			
			System.out.print("|");
			for(int j = 0; j < length1; j++) {
				System.out.print("_");
			}
			System.out.print("|");
			
			for(int j = 0; j < 38; j++) {
				System.out.print(" ");
			}
			
			System.out.print("|");
			for(int j = 0; j < length2; j++) {
				System.out.print("_");
			}
			System.out.println("|");
	
		}
	}
	
	//Auxiliar function to print the relationships
	public static void printRelationship(String type, String class1, String class2) {
		int dashes = (28 - type.length())/2;
		printRow(class1, class1.length()+10, true);
		System.out.print("     ");
		for(int i = 0; i < dashes; i++) {
			System.out.print("-");
		}
		System.out.print(type);
		for(int i = 0; i < dashes; i++) {
			System.out.print("-");
		}
		System.out.print(">     ");
		printRow(class2, class2.length()+10, true);
		System.out.println("");
	}
	
	//Auxiliar function to print a row(either a class name or a field name)
	private static void printRow(String phrase, int length, boolean isRelationship) {
		int blankSpaces = length;
		int remaining = blankSpaces-phrase.length() - 3;
		int counter = 1;
		
		System.out.print("|");
		while(counter <= remaining/2) {
			System.out.print(" ");
			counter++;
		}
		System.out.print("<> " + phrase);
		for(int i = 0; i <= remaining-counter; i++) {
			System.out.print(" ");
		}
		if(isRelationship)
			System.out.print("|");
		else
			System.out.println("|");
	}
	
	//Method to know what is the length of the longest sentence in the class(ClassName, Field, Method)
	private static int maximumLength(Class classSample) {
		int maxLength = 0;
		
		if(classSample.name.length()>maxLength) {
			maxLength = classSample.name.length();
		}
		
		for(Field fld: classSample.fields) {
			if((fld.name.length() + fld.type.length() + 1)>maxLength) {
				maxLength = fld.name.length() + fld.type.length() + 1;
			}
		}

		int methodlength = 0;
		for(Method m: classSample.methods) {
			methodlength = m.name.length() + m.type.length() + 3;
			for(Parameter p: m.parameters)
			{
				methodlength += p.name.length() + p.type.length() + 3;
			}
			if(methodlength > maxLength) {
				maxLength = methodlength;
			}
		}
		return maxLength;
	}

}