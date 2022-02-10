import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.array;

public class Save {

    //pass both class and atrribute objects, then iterate through attributes, make relationships whole other section
    public static void saveFile(String fileName, ArrayList <Class> classes, ArrayList <Relationship> relations)
    {

    
      try{
   
     FileWriter writer = new FileWriter(fileName);
     String contents = ""; 


      if (newFile.contains(".json"))
        {
          contents = saveJson(classes, relations);
        }
         else
         {
             contents = saveYaml(classes, relations);
         }
         writer.write(contents);
         writer.close();
         System.out.println("Successfully wrote to the file.");

    } catch (IOException e) 
        {

      System.out.println("An error occurred.");
      e.printStackTrace();
        }
    }


    private static String saveJson(ArrayList <Class> classes, ArrayList <Relationship> relations)
    {
        String text =  "";
      for(class : classes)
      {
          text += "\"class\": \"" + class.name +"\"" + "{\n";

          for(attribute : class.attributes)
          {
            text += "\t\"attribute\":\"" + attribute.name + "\"" ",\n";
          }
          text += "}";
        
      }
      
      for (r : relations)
      {
        text += "relationship:" + r.name + "\n";
      }
      return text;
    }
    private static String saveYaml(ArrayList <Class> classes, ArrayList <Relationship> relations)
    {
        String text =  "";
        text += "classes: \n";
        for(class : classes)
      {
          text += "\tclass:\n";
          text += "\t\tname: " + class.name + "\n";

          text += "\t\tattributes:\n";

          for(attribute : class.attributes)
          {
            text += "\t\t\tattribute: " + attribute.name + "\n";
          }
        
      }
      text += "relationships: \n";
      for (r : relations)
      {
        text += "\trelationship:" + r.name + "\n";
      }
      return text;
    }



}