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

        dg.deleteClass("dummy");
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
        dg.deleteClass("dummy");
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
    }
}
