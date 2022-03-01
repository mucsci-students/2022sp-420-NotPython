/*
* Field.java
*
* field object constructor
*/
package UML.model;

public class Field {
    public String type;
    public String name;
    
    /*
    * attaches the field to the class
    */
    public Field (String tp, String fld)
    {
        this.type = tp;
        this.name = fld;
    }

    /*
    * renames a field
    */
    public void rename_field (String newName)
    {
        this.name = newName;
    }
}