/*
* attributes.java
* 
* attribute object constructor
*/
public class Attribute {
    public String name;

    //Attribute constructor
    public Attribute (String attrib) 
    {
        name = attrib;
    }

    
    //rename_attribute: renames an attribute
    public void rename_attribute (String newName) 
    {
        name = newName;
    }
}
