package UML.model;

import java.io.FileWriter;
import java.io.IOException;
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
      int attributeCounter;
      //int classCounter = 1;
      String text =  "{\n\t\"classes\": [";
      for(Class c : classes)
      {  
          text += "\n\t{ \n\t\t\"name\": \"" + c.name +"\"" + ",\n";

          text += "\t\t\"attributes\": [\n\t\t{\n";
          
          attributeCounter = 1;
          /*for(Attribute a: c.attributes)
          {
            
            text += "\t\t\t\"attribute" + attributeCounter + "\":\"" + a.name + "\"";
            if(c.attributes.indexOf(a) != c.attributes.size() -1 )
            {
            text += ",";        
            }
            text += "\n";
            ++attributeCounter;
          }*/
         
          text += "\t\t}\n\t\t]";
          text+= "\n\t}";
          if(classes.indexOf(c) != classes.size() -1)
          {
            text+= ",";
          }
      }
      
      text += "\n";
      text += "\t],\n";
      text +=  "\t\"relationships\": [\n";
      for (Relationship r : relations)
      {
        //add typing here or somewhere
        text += "\t{\n"; //\t\t\"name\": \"" + r.name +"\"" + ",\n";

        text += "\t\t\"src\": \"" + r.src +"\"" + ",\n";
        
        text += "\t\t\"dest\": \"" + r.dest +"\"" + "\n\t}";
        if(relations.indexOf(r) != relations.size() -1)
          {
            text+= ",";
          }
          text+= "\n";
      }
      text+= "\t]\n}";
      return text;
    }
    
}