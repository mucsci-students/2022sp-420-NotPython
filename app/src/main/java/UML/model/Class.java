package UML.model;

import java.util.ArrayList;
import UML.model.*;

public class Class 
{
    public String name;
    public ArrayList <Field> fields;
    public ArrayList <Method> methods;
    public ArrayList <Location> location;

    //class constructor
    public Class (String initName)
    {
        name = initName;
        fields = new ArrayList<Field>();
        methods = new ArrayList<Method>();
    }

    public Class (Class other)
    {
        this.name = other.name;
        this.fields = new ArrayList <Field>();
        this.methods = new ArrayList <Method>();
        this.location = new ArrayList <Location>();
    
        for (Field f : other.fields)
        {
            this.fields.add(new Field(f.name, f.type));
        }
            
        for(Method m : other.methods)
        {
            ArrayList <String> parms = new ArrayList<>();
            for (Parameter p : m.parameters)
            {
                parms.add(p.name);
                parms.add(p.type);
            }
            this.methods.add(new Method(m.type, m.name, parms));
        }

        this.location.add(new Location(other.x, other.y));
    
    }

    //rename class
    public void rename(String newName)
    {
        name = newName;
    }
}