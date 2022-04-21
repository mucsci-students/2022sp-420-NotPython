package UML.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Save {

  // class constructor
  public Save() {

  }

  // pass both class and atrribute objects, then iterate through attributes, make
  // relationships whole other section
  public void saveFile(String fileName, ArrayList<Class> classes, ArrayList<Relationship> relations, HashMap <String, String> locs) throws IOException{

    FileWriter writer = new FileWriter(fileName);

    if (fileName.contains(".json")) {
      writer.write(diagramJSON(classes, relations, locs));
    }

    writer.close();
  }

  //creates JSON diagram representation
  private String diagramJSON(ArrayList<Class> classes, ArrayList<Relationship> relations, HashMap <String, String> locs)
  {
    String diagramString = "{\n\t\"classes\": [";
    for (Class c: classes)
    {
      String loc = locs.get(c.name);
      String [] coords = loc.split(" ");
      diagramString += classJSON(c, coords[0], coords[1]);

      if (classes.indexOf(c) < classes.size()-1)
      {
        diagramString += ",";
      }
      else
      {
        diagramString += "\n\t";
      }
    }
    diagramString += "],";

    diagramString += "\n\t\"relationships\": [";
    for (Relationship r: relations)
    {
      diagramString += relationshipJSON(r);

      if (relations.indexOf(r) < relations.size()-1)
      {
        diagramString += ",";
      }
      else
      {
        diagramString += "\n\t";
      }
    }
    diagramString += "]\n}";
    return diagramString;
  }

  //creates json class representation
  private String classJSON(Class c, String x, String y)
  {
    String classString = "\n\t{\n\t\t";
    classString += "\"name\": \"" + c.name + "\",\n\t\t";
    classString += "\"fields\": [";

    for(Field f: c.fields)
    {
      classString += fieldJSON(f);
      if (c.fields.indexOf(f) < c.fields.size()-1)
      {
        classString += ",";
      }
      else
      {
        classString += "\n\t\t";
      }
    }
    classString += "],\n\t\t";
    
    classString += "\"methods\": [";
    for(Method m: c.methods)
    {
      classString += methodJSON(m);
      if (c.methods.indexOf(m) < c.methods.size()-1)
      {
        classString += ",";
      }
      else
      {
        classString += "\n\t\t";
      }
    }
    classString += "],";
    classString += locationJSON(x, y); 
    classString += "\n\t}";
    return classString;
  }

  private String locationJSON(String x, String y){
    String result = "\n\t\t\"location\":{";
    result += "\"x\":" + x + ",\"y\":" + y;
    result += "}";
    return result;
  }

  //creates json field representation
  private String fieldJSON(Field f)
  {
    String fieldString = "\n\t\t\t{ ";
    fieldString += "\"name\": \"" + f.name + "\", \"type\": \"" + f.type + "\"";
    fieldString += " }";
    return fieldString;
  }

  //creates json method representation
  private String methodJSON(Method m)
  {
    String methodString = "\n\t\t\t{\n\t\t\t\t";
    methodString += "\"name\": \"" + m.name + "\",\n\t\t\t\t";
    methodString += "\"return_type\": \"" + m.type + "\",\n\t\t\t\t";
    methodString += "\"params\": [";

    for(Parameter p: m.parameters)
    {
      methodString += paramJSON(p);
      if (m.parameters.indexOf(p) < m.parameters.size()-1)
      {
        methodString += ",";
      }
      else
      {
        methodString += "\n\t\t\t\t";
      }
    }
    methodString += "]\n\t\t\t";
    methodString += "}";
    return methodString;
  }

  //creates json parameter representation
  private String paramJSON(Parameter p)
  {
    String paramString = "\n\t\t\t\t\t{ ";
    paramString += "\"name\": \"" + p.name + "\", \"type\": \"" + p.type + "\"";
    paramString += " }";
    return paramString;
  }

  //creates the json relationship representation
  private String relationshipJSON(Relationship r)
  {
    String relationString = "\n\t\t{";
    relationString += "\n\t\t\t\"source\": \"" + r.src + "\",";
    relationString += "\n\t\t\t\"destination\": \"" + r.dest + "\",";
    relationString += "\n\t\t\t\"type\": \"" + r.type + "\"";
    relationString += "\n\t\t}";
    return relationString;
  }
}