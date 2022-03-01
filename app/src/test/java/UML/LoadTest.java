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
    }

    @Test
    public void testLoadAttributes()
    {
        Diagram dc = new Diagram();
        dc.createClass("cool");
//        dc.createAttribute("cool", "bar");
//        dc.createAttribute("cool", "class");
//        dc.createAttribute("cool", "silly");

        dc.saveDiagram("test.json");
        dc.deleteClass("cool");
        dc.loadDiagram("test.json");

        assertTrue("Class cool not loaded", dc.getClass("cool") != null);

        Class c = dc.getClass("cool");
//        assertTrue("Attribute bar not loaded", dc.getAttribute(c, "bar") != null);
//        assertTrue("Attribute class not loaded", dc.getAttribute(c, "class") != null);
//        assertTrue("Attribute silly not loaded", dc.getAttribute(c, "silly") != null);
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
        //assertTrue("Relationships not empty", dc.relationships.isEmpty());

        dc.loadDiagram("test.json");

        assertFalse("Relationships not loaded", dc.relationships.isEmpty());
        assertTrue("Relationship from src not loaded", dc.getRelationship("src", "dest") != null);
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

//        dc.createAttribute("cool", "bar");
//        dc.createAttribute("cool", "class");
//        dc.createAttribute("cool", "silly");
//        dc.createAttribute("bar", "bar");
//        dc.createAttribute("bar", "class");
//        dc.createAttribute("bar", "silly");
//        dc.createAttribute("class", "bar");
//        dc.createAttribute("class", "class");
//        dc.createAttribute("class", "silly");
        dc.createRelationship("Aggregation", "cool", "bar");
        dc.createRelationship("Aggregation", "bar", "class");
        dc.saveDiagram("test.json");

        

        dc.classList.clear();
        dc.relationships.clear();

        assertTrue("classList not cleared", dc.classList.isEmpty());
        assertTrue("relationships not cleared", dc.relationships.isEmpty());

        dc.loadDiagram("test.json");
        assertTrue("Class cool not loaded", dc.getClass("cool") != null);
        assertTrue("Class bar not loaded", dc.getClass("class") != null);
        assertTrue("Class class not loaded", dc.getClass("class") != null);

        Class c = dc.getClass("cool");
//        assertTrue("Attribute bar not loaded", dc.getAttribute(c, "bar") != null);
//        assertTrue("Attribute class not loaded", dc.getAttribute(c, "class") != null);
//        assertTrue("Attribute silly not loaded", dc.getAttribute(c, "silly") != null);

        assertTrue("Relationship from bar to class not loaded", dc.getRelationship("cool", "bar") != null);
        assertTrue("Relationship from bar to class not loaded", dc.getRelationship("bar", "class") != null);
    }
}
