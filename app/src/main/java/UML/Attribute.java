/*
* Attribute.java
* 
* attribute object constructor
*/
package UML;

public class Attribute {
    public String name;

    /*
    * Attribute: attaches the attribute to the class
    * 
    */
    public Attribute (String attrib) 
    {
        name = attrib; 
    }

    /*
    * rename_attribute: renames an attribute
    * 
    */
    public void rename_attribute (String newName) 
    {
        name = newName;
    }


} 
