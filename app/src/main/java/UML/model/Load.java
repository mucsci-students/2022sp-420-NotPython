package UML.model;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Load {
 

    //class constructor
    public Load () {}

    //loads json
    public Map<ArrayList<Class>, ArrayList<Relationship>> loadFile(String name)
    {
        Map <ArrayList<Class>, ArrayList<Relationship>> map = new HashMap<ArrayList<Class>, ArrayList<Relationship>>();
        try
        {
            if (name.contains(".json"))
            {
                map = loadJson(name);
            }
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map;
    }

    //loads json file
    private static Map<ArrayList<Class>, ArrayList<Relationship>> loadJson (String file)
    {
        //create Arraylists and maps for returning
        ArrayList <Relationship> relationshipList = new ArrayList<Relationship>();
        ArrayList <Class> classList = new ArrayList<Class>();
        Map <ArrayList<Class>, ArrayList<Relationship>> map = new HashMap<ArrayList<Class>, ArrayList<Relationship>>();

        String text;
        try
        {
            //read file into json object
            text = Files.readString(Paths.get(file));
            JSONObject jsonObj = new JSONObject(text);

            //create json arrays for classes, fields, parameters and methods
            JSONArray classesArray = (JSONArray) jsonObj.get("classes");
            JSONArray methodArray, paramArray, fieldArray; 
            
            //create json objects to hold classes, relationships, fields, methods, and parameters
            JSONObject classObject, relObject, fieldObject, methodObject, paramObject;

            //string variables for declarations
            String className, name, type;
            ArrayList <String> params = new ArrayList<String>();
            Class c;
            
            //read in all classes from file
            for (int i = 0; i < classesArray.length(); ++i)
            {
                //get name and create class
                classObject = (JSONObject) classesArray.getJSONObject(i);
                className = classObject.getString("name");
                c = new Class(className);

                //get the fields for the class from the file
                fieldArray = (JSONArray) classObject.get("fields");
                for (int j = 0; fieldArray != null && j < fieldArray.length(); ++j)
                {
                    fieldObject = fieldArray.getJSONObject(j);
                    name = fieldObject.getString("name");
                    type = fieldObject.getString("type");
                    c.fields.add(new Field(name, type));
                }

                //get the methods for the class from the file
                methodArray = (JSONArray) classObject.get("methods");
                for (int q = 0; fieldArray != null && q < methodArray.length(); ++q)
                {
                    methodObject = methodArray.getJSONObject(q);
                    name = methodObject.getString("name");
                    type = methodObject.getString("return_type");

                    //get the params for the method from the file
                    paramArray = (JSONArray) methodObject.get("params");
                    for (int p = 0; paramArray != null && p < paramArray.length(); ++p)
                    {
                        paramObject = paramArray.getJSONObject(p);
                        params.add(paramObject.getString("name"));
                        params.add(paramObject.getString("type"));
                    }
                    c.methods.add(new Method(type, name, params));
                    params.clear();
                }

                //get the location for the class from the file
                locationArray = (JSONArray) classObject.get("location");
                x = locationArray.getInt("x");
                y = locationArray.getInt("y");
                c.locationArray.add(new locationArray(x, y));
                
                //add the class
                classList.add(c);
            }
		    
            //get the relationships from the file
            JSONArray relArray = (JSONArray) jsonObj.get("relationships");
            String src, dest = "";
            for (int i = 0; relArray != null && i < relArray.length(); i++)
            {
                relObject = relArray.getJSONObject(i);
                src = relObject.getString("source");
                dest = relObject.getString("destination");
                type = relObject.getString("type");
                relationshipList.add(new Relationship(type, src, dest));
            }
        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        map.put(classList, relationshipList);
        return map;
    }   
}