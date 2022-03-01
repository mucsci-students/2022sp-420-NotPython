package UML.model;

import java.util.ArrayList;

public class Class 
{
    public String name;
    public ArrayList <Attribute> attributes;

    //class constructor
    public Class (String initName)
    {
        name = initName;
        attributes = new ArrayList<Attribute>();
    }

    //rename class
    public void rename(String newName)
    {
        name = newName;
    }
}