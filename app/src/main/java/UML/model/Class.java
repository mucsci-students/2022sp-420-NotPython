package UML.model;

import java.util.ArrayList;

public class Class 
{
    public String name;
    public ArrayList <Field> fields;
    public ArrayList <Method> methods;

    //class constructor
    public Class (String initName)
    {
        name = initName;
        fields = new ArrayList<Field>();
        methods = new ArrayList<Method>();
    }

    //rename class
    public void rename(String newName)
    {
        name = newName;
    }
}