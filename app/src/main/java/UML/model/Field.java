/*
* Field.java
*
* field object constructor
*/

package UML.model;

public class Field {
    public String name;
    public String type;
    
    /*
    * attaches the field to the class
    */
    public Field (String fld, String tp)
    {
        this.name = fld;
        this.type = tp;
    }

    /*
    * renames a field
    */
    public void rename_field (String newName)
    {
        this.name = newName;
    }
}