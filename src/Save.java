import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

public class Save {
 
    //class constructor
    public Save ()
    {
      
    }

    //pass both class and atrribute objects, then iterate through attributes, make relationships whole other section
    public void saveFile(String fileName, ArrayList <Class> classes, ArrayList <Relationship> relations)
    { 
      try{
   
       	  FileWriter writer = new FileWriter(fileName);
          String contents = ""; 


      if (fileName.contains(".json"))
        {
      	   contents = saveJson(classes, relations);
        }
         else
         {
             contents = saveYaml(classes, relations);
         }
         writer.write(contents);
         writer.close();
         

    } catch (IOException e) 
        {

      		System.out.println("An error occurred.");
     		 e.printStackTrace();
        }
    }

//creates json string
    private static String saveJson(ArrayList <Class> classes, ArrayList <Relationship> relations)
    {
        String text =  "";
      for(Class c : classes)
      {
          text += "\"class\": \"" + c.name +"\"" + "{\n";

          for(Attribute a: c.attributes)
          {
            text += "\t\"attribute\":\"" + a.name + "\" ,\n";
          }
          text += "}\n";
        
      }
      
      for (Relationship r : relations)
      {
        text += "\"relationship\":\"" + r.name + "\"{\n";

        text += "\t\"class\": \"" + r.class1 +"\"" + "\n";
        text += "\t\"class\": \"" + r.class2 +"\"" + "\n}";
      }
      
      return text;
    }
 //creates yaml string
 
    private static String saveYaml(ArrayList <Class> classes, ArrayList <Relationship> relations)
    {
        String text =  "";
        text += "classes: \n";
        for(Class c : classes)
      {
          text += "\tclass:\n";
          text += "\t\tname: " + c.name + "\n";

          text += "\t\tattributes:\n";

          for(Attribute a : c.attributes)
          {
            text += "\t\t\tattribute: " + a.name + "\n";
          }
        
      }
      text += "relationships: \n";
      for (Relationship r : relations)
      {
        text += "\trelationship:" + r.name + "\n";
        text+= "\t\tClass: " + r.class1 + "\n";
        text+= "\t\tClass: " + r.class2 + "\n";
      }
      return text;
    }



}
