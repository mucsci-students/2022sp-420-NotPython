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

    //loads json and yaml
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

    private static Map<ArrayList<Class>, ArrayList<Relationship>> loadJson (String file)
    {
        ArrayList <Relationship> relationshipList = new ArrayList<Relationship>();
        ArrayList <Class> classList = new ArrayList<Class>();
        Map <ArrayList<Class>, ArrayList<Relationship>> map = new HashMap<ArrayList<Class>, ArrayList<Relationship>>();

        String text;
        try
        {
            text = Files.readString(Paths.get(file));
            JSONObject jsonObj = new JSONObject(text);
            JSONArray classesArray = (JSONArray) jsonObj.get("classes");
            JSONObject classObject;
            JSONObject relObject;
            JSONArray attrArray;
            JSONObject attrObject;
            String [] attrNames = new String [100];
            String className;
            String attribute;
            Class c;
            
            for (int i = 0; i < classesArray.length(); i++)
            {
                classObject = (JSONObject) classesArray.getJSONObject(i);
                className = (String) classObject.get("name");
                attrArray = (JSONArray) classObject.get("attributes");
                attrObject = (JSONObject) attrArray.get(0);
                attrNames = JSONObject.getNames(attrObject);
                c = new Class(className);
                
                for (int j = 0; attrNames != null && j < attrNames.length; ++j)
                {
                    attribute = (String) attrObject.get(attrNames[j]);
                    c.attributes.add(new Attribute(attribute));
                }
                
                classList.add(c);
            }
		    
            JSONArray relArray = (JSONArray) jsonObj.get("relationships");
            String src, dest, type = "";
            for (int i = 0; i < relArray.length(); i++)
            {
                relObject = (JSONObject) relArray.getJSONObject(i);
                src = (String) relObject.get("src");
                dest = (String) relObject.get("dest");
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