/**
 * JUnit tests for Load
 *
 * @authors: notPython
 *
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import UML.model.Class;
import UML.model.Diagram;
import UML.model.Field;
import UML.model.Method;

public class LoadTest
{
    @Test
    public void testLoadClasses()
    {
        Diagram dc = new Diagram();
        dc.createClass("cool");
        dc.createClass("bar");
        dc.createClass("class");

        dc.saveDiagram("test.json");
        dc.deleteClass("cool");
        dc.deleteClass("bar");
        dc.deleteClass("class");
        dc.loadDiagram("test.json");

        assertFalse("Classes not Loaded", dc.classList.isEmpty());
        assertTrue("Class foo not loaded", dc.getClass("cool") != null);
        assertTrue("Class bar not loaded", dc.getClass("bar") != null);
        assertTrue("Class class not loaded", dc.getClass("class") != null);

        dc.classList.clear();
        dc.relationships.clear();
        dc.saveDiagram("test.json");
    }

    @Test
    public void testLoadFields()
    {
        Diagram dc = new Diagram();
        dc.createClass("cool");
        dc.createField("cool", "bar", "int");
        dc.createField("cool", "class", "string");
        dc.createField("cool", "silly", "double");

        dc.saveDiagram("test.json");
        dc.deleteClass("cool");
        dc.loadDiagram("test.json");

        assertTrue("Class cool not loaded", dc.getClass("cool") != null);

        Class c = dc.getClass("cool");
        assertTrue("Field bar not loaded", dc.getField(c, "bar") != null);
        assertTrue("Field class not loaded", dc.getField(c, "class") != null);
        assertTrue("Field silly not loaded", dc.getField(c, "silly") != null);

        dc.classList.clear();
        dc.relationships.clear();
        dc.saveDiagram("test.json");
    }

    @Test
    public void testLoadMethods()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        assertTrue("Method not created", dg.getMethod("dummy", "name") != null);

        dg.saveDiagram("test.json");
        dg.deleteClass("dummy");
        dg.loadDiagram("test.json");

        assertTrue("Method not loaded", dg.getMethod("dummy", "name") != null);
        assertTrue("Method has parameters that shouldn't exist", dg.getMethod("dummy", "name").parameters.size() == 0);
        dg.deleteMethod("dummy", "name");
        dg.deleteClass("dummy");

        dg.classList.clear();
        dg.relationships.clear();
        dg.saveDiagram("test.json");
    }

    @Test
    public void testLoadRelationships()
    {
        Diagram dc = new Diagram();
        dc.createClass("src");
        dc.createClass("dest");
        dc.createClass("Parent");
        dc.createClass("Child");
        dc.createRelationship("Aggregation","src", "dest");
        dc.createRelationship("Inheritance", "Parent", "Child");

        dc.saveDiagram("test.json");
        dc.deleteClass("src");
        dc.deleteClass("dest");
        dc.deleteClass("Parent");
        dc.deleteClass("Child");
        
        //commented out because other tests are interfering
        assertTrue("Relationships not empty", dc.relationships.isEmpty());

        dc.loadDiagram("test.json");

        assertFalse("Relationships not loaded", dc.relationships.isEmpty());
        assertTrue("Relationship from src not loaded", dc.getRelationship("src", "dest") != null);

        dc.classList.clear();
        dc.relationships.clear();
        dc.saveDiagram("test.json");
    }

    @Test
    public void testLoadParameters()
    {
        ArrayList <String> parms = new ArrayList<String>();
        parms.add("name1");
        parms.add("type1");
        parms.add("name2");
        parms.add("type2");
        parms.add("name3");
        parms.add("type3");
        parms.add("name4");
        parms.add("type4");
        Diagram dg = new Diagram();
        dg.createClass("testClass");
        dg.createMethod("testClass", "return_type", "method_name", parms);

        dg.saveDiagram("test.json");
        dg.deleteClass("testClass");
        dg.loadDiagram("test.json");

        Method m = dg.getMethod("testClass", "method_name");
        assertTrue("method not loaded", m.type.equals("return_type"));
        assertTrue("method name not set correctly", m.name.equals("method_name"));

        int j = 0;
        for (int i = 0; i < parms.size()-1; i+=2)
        {
            assertTrue("Parameter name not assigned correctly", m.parameters.get(j).name.equals(parms.get(i)));
            assertTrue("Parameter type not assigned correctly", m.parameters.get(j).type.equals(parms.get(i + 1)));
            ++j;
        }

        dg.deleteClass("testClass");
        dg.classList.clear();
        dg.relationships.clear();
        dg.saveDiagram("test.json");
    }

    @Test
    public void testLoadEverything()
    {
        Diagram dc = new Diagram();
        
        dc.classList.clear();
        dc.relationships.clear();

        dc.createClass("cool");
        dc.createClass("bar");
        dc.createClass("class");

        dc.createField("cool", "bar", "int");
        dc.createField("cool", "class", "string");
        dc.createField("cool", "silly", "float");
        dc.createField("bar", "bar", "double");
        dc.createField("bar", "class", "long");
        dc.createField("bar", "silly", "map");
        dc.createField("class", "bar", "class");
        dc.createField("class", "class", "vector");
        dc.createField("class", "silly", "custom");
        dc.createRelationship("Aggregation", "cool", "bar");
        dc.createRelationship("Aggregation", "bar", "class");

        ArrayList <String> parms = new ArrayList<String>();
        parms.add("name1");
        parms.add("type1");
        parms.add("name2");
        parms.add("type2");
        parms.add("name3");
        parms.add("type3");
        parms.add("name4");
        parms.add("type4");
        Diagram dg = new Diagram();
        dg.createMethod("cool", "return_type", "method_name", parms);

        dc.saveDiagram("test.json");

        dc.classList.clear();
        dc.relationships.clear();

        assertTrue("classList not cleared", dc.classList.isEmpty());
        assertTrue("relationships not cleared", dc.relationships.isEmpty());

        dc.loadDiagram("test.json");

        Class cool = dc.getClass("cool");
        Class bar = dc.getClass("bar");
        Class clas = dc.getClass("class");

        assertTrue("Class cool not loaded", cool != null);
        assertTrue("Class bar not loaded", bar != null);
        assertTrue("Class class not loaded", clas != null);

        assertTrue("Field bar not loaded", dc.getField(cool, "bar") != null);
        assertTrue("Field class not loaded", dc.getField(cool, "class") != null);
        assertTrue("Field silly not loaded", dc.getField(cool, "silly") != null);
        assertTrue("Field bar not loaded", dc.getField(bar, "bar") != null);
        assertTrue("Field class not loaded", dc.getField(bar, "class") != null);
        assertTrue("Field silly not loaded", dc.getField(bar, "silly") != null);
        assertTrue("Field bar not loaded", dc.getField(clas, "bar") != null);
        assertTrue("Field class not loaded", dc.getField(clas, "class") != null);
        assertTrue("Field silly not loaded", dc.getField(clas, "silly") != null);

        Method m = dg.getMethod("cool", "method_name");
        assertTrue("method not loaded", m.type.equals("return_type"));
        assertTrue("method name not set correctly", m.name.equals("method_name"));

        int j = 0;
        for (int i = 0; i < parms.size()-1; i+=2)
        {
            assertTrue("Parameter name not assigned correctly", m.parameters.get(j).name.equals(parms.get(i)));
            assertTrue("Parameter type not assigned correctly", m.parameters.get(j).type.equals(parms.get(i + 1)));
            ++j;
        }

        assertTrue("Relationship from bar to class not loaded", dc.getRelationship("cool", "bar") != null);
        assertTrue("Relationship from bar to class not loaded", dc.getRelationship("bar", "class") != null);

        dc.classList.clear();
        dc.relationships.clear();
        dc.saveDiagram("test.json");
    }
}

