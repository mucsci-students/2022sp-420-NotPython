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


    private static String saveJson(ArrayList <Class> classes, ArrayList <Relationship> relations)
    {
      
        String text =  "{\n\t\"classes\": [\n\t{";
      for(Class c : classes)
      {  
          text += "\n\t\t\"class\": { \n\t\t\t\"name\": \"" + c.name +"\"" + ",\n";

          text += "\t\t\t\"attributes\": [\n\t\t\t{\n";
          for(Attribute a: c.attributes)
          {
           
            text += "\t\t\t\t\"attribute\":\"" + a.name + "\"";
            if(c.attributes.indexOf(a) != c.attributes.size() -1 )
            {
            text += ",";        
            }
            text += "\n";
          }
         
          text += "\t\t\t}\n\t\t]";
          text+= "\n\t\t}";
          if(classes.indexOf(c) != classes.size() -1)
          {
            text+= ",";
          }
          
      }
      
      text += "\n\t\t}\n";
      text += "\t]\n}\n";
      for (Relationship r : relations)
      {
        text += "\"relationship\":\"" + r.name + "\"{\n";

        text += "\t\"class\": \"" + r.src +"\"" + "\n";
        text += "\t\"class\": \"" + r.dest +"\"" + "\n}";
      }
      
      return text;
    }
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
        text+= "\t\tClass: " + r.src + "\n";
        text+= "\t\tClass: " + r.dest + "\n";
      }
      return text;
    }



}
