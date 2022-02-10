import java.util.ArrayList;

public class Listing {
	
	
	//Listing class: List the content of a class defined by the user
	
	public static void listClass(ArrayList<Class> list, String className) {
		
		//Check the class name is valid
		if(Driver.validation_check(className)) {
			
			Class classAux;
			
			//Look for the class info
			for(int i = 0; i < list.size(); i++) {
				classAux = list.get(i);
				if(classAux.name.equals(className)) {
					
					System.out.println(" ______________________");
					printRow(classAux.name, 0, false);
					System.out.println("|======================|");
					
					for(Attribute attr: classAux.attributes) {
						printRow(attr.name, 1, false);
					}
					
					System.out.println("|______________________|");
				}
			}
		}
	}
	
	// List classes: List the content of all the existing classes
	
	public static void listClasses(ArrayList<Class> list) {
		//Iterate through all the classes and print its content
		for(Class classSample: list) {
			System.out.println(" ______________________");
			printRow(classSample.name, 0, false);
			System.out.println("|======================|");
			
			classSample.attributes.add(new Attribute("Gold"));
			
			for(Attribute attr: classSample.attributes) {
				printRow(attr.name, 1, false);
			}
			
			System.out.println("|______________________|");
		}
	}
	
	//List relationships: List all the exiting relationships
	
	public static void listRelationships(ArrayList<Relationship> list) {
		//Iterate through all the relationships and print its content
		for(Relationship relationshipSample: list) {
			System.out.println(" ______________________                                        ______________________");
			printRelationship(relationshipSample.src, relationshipSample.dest, relationshipSample.relationshipName);
			System.out.println("|______________________|                                      |______________________|");
		}
	}
	
	//Auxiliar function to print the relationships
	public static void printRelationship(String class1, String class2, String relationshipName) {
		int dashes = 28;
		int remaining = dashes-relationshipName.length();
		printRow(class1, 0, true);
		System.out.print("    <");
		for(int i = 0; i < remaining/2; i++) {
			System.out.print("-");
		}
		System.out.print(relationshipName);
		for(int i=0; i<(remaining-remaining/2); i++) {
			System.out.print("-");
		}
		System.out.print(">    ");
		printRow(class2, 0, true);
		System.out.println("");
	}
	
	//Auxiliar function to print a row(either a class name or a attribute name)
	private static void printRow(String phrase, int isAttribute, boolean isRelationship) {
		int blankSpaces = 22;
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
