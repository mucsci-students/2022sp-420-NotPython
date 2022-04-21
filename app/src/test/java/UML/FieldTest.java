/**
 * JUnit tests for Field
 *
 * @author Alex Lim
 *  tests attribute
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Class;
import UML.model.Diagram;
import UML.model.Field;

public class FieldTest {
    @Test
    public void FieldConstructorTest()
    {
        Field f = new Field("name", "type");
        assertTrue("Field name not assigned correctly", f.name.equals("name"));
        assertTrue("Field type not assigned correctly", f.type.equals("type"));
    }

    @Test
    public void FieldObjectRenameTest()
    {
        Field f = new Field("name", "type");
        f.rename_field("newName");
        assertTrue("Field renamed incorrectly", f.name.equals("newName"));
    }

    @Test
    public void DiagramAddFieldTest()
    {
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createField("dummy", "speed", "int");
        
        Class c = dg.getClass("dummy");
        assertTrue("Field not created", dg.getField(c, "speed") != null);

        String retStr = dg.createField("dummy", "field:name", "int");
        assertTrue("Error was not thrown for bad field name", retStr.equals("ERROR: : is an invalid character"));

        retStr = dg.createField("dummy", "speed", "int");
        assertTrue("Error was not thrown for field that already exists", 
            retStr.equals("ERROR: Field with name \"speed\" for \"dummy\" already exists"));
        
        retStr = dg.createField("dummy1", "speed", "int");
        assertTrue("Class shouldn't exist", retStr.equals("ERROR: Class with name \"dummy1\" does not exist"));
    }

    @Test
    public void DiagramRenameFieldTest()
    {
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createField("dummy", "speed", "int");
        Class c = dg.getClass("dummy");
        assertTrue("Field not created", dg.getField(c, "speed") != null);

        dg.renameField("dummy", "speed", "number");

        assertTrue("Field not renamed", dg.getField(c, "number") != null);
        
        String retStr = dg.renameField("dummy1", "speed", "number");
        assertTrue("Field cannot be renamed for class that does not exist", retStr.equals("ERROR: Class \"dummy1\" does not exist"));

        retStr = dg.renameField("dummy", "speed", "number");
        assertTrue("Field should not exist", retStr.equals("ERROR: Field with name \"speed\" does not exist"));

        dg.createField("dummy", "stupid", "string");
        assertTrue("Field not created", dg.getField(c, "stupid") != null);

        retStr = dg.renameField("dummy", "number", "stupid");
        assertTrue("New Field conflicts with currently existing field", retStr.equals("ERROR: Field with name \"stupid\" already exists"));

        retStr = dg.renameField("dummy", "number", "stupid?");
        assertTrue("Field name was valid?", retStr.equals("ERROR: ? is an invalid character"));
    }

    @Test
    public void DiagramDeleteFieldTest()
    {
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createField("dummy", "speed", "int");
        Class c = dg.getClass("dummy");
        assertTrue("Field not created", dg.getField(c, "speed") != null);
        dg.deleteField("dummy", "speed");
        assertFalse("Field not deleted", dg.getField(c, "speed") != null);

        String retStr = dg.deleteField("dummy", "speed");
        assertTrue("Field shouldn't exist", retStr.equals("ERROR: Field with name \"speed\" for \"dummy\" does not exist"));

        retStr = dg.deleteField("dummy1", "speed");
        assertTrue("Class shouldnt exist", retStr.equals("ERROR: Class with name \"dummy1\" does not exist"));
    }
}
