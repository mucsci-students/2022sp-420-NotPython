/**
 * JUnit tests for Class
 *
 * @author Alex Lim
 *  tests attribute
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Class;
import UML.model.Diagram;

public class ClassTest {
	/**
	 * 
	 */
	@Test
	public void testConstructor() {
		//Diagram s = new Diagram( );
        Class c = new Class("hello");
        
        assertTrue("Class not created", c != null);
        assertTrue("Class does not have correct name", c.name.equals("hello"));
        
	}

	@Test
	public void testRename(){
        Class c = new Class("hello");
		c.rename("test");

		assertTrue("Class rename failed", c.name.equals("test"));
	}

	@Test
	public void testDelete(){
		Diagram d = new Diagram( );
		d.createClass("1");

        d.deleteClass("1");
        assertTrue("Class failed to delete", d.getClass("1") == null);

		String retStr = d.deleteClass("2");
		assertTrue("Deleted class that doesn't exist", retStr.equals("ERROR: Class with name \"2\" does not exist"));

		d.createClass("src");
		d.createClass("dest");
		d.createRelationship("Aggregation", "src", "dest");

		d.deleteClass("dest");
		assertTrue("Relationship still exists after deleting class", d.getRelationship("src", "dest") == null);
	}

	@Test
	public void testCreate(){
		Diagram d = new Diagram( );

		String retStr = d.createClass("class1:class2");
		assertTrue("Error was not thrown for bad className", retStr.equals("ERROR: : is an invalid character"));

		d.createClass("2");
		retStr = d.createClass("2");

		assertTrue("Class already exists but is not found", retStr.equals("ERROR: Class with name \"2\" already exists"));

        d.createClass("3");
        
		assertTrue("Class 2 not created", d.getClass("2") != null);
		assertTrue("Class 3 not created",  d.getClass("3") != null);
		d.deleteClass("2");
        d.deleteClass("3");
        
	}
	@Test
	public void testRenameDC(){
		Diagram d = new Diagram( );
		d.createClass("x");
        d.renameClass("x", "y");
		
		assertTrue("Class rename failed", d.getClass("y") != null);

		String retStr = d.renameClass("x", "y");
		assertTrue("Class that doesn't exist was renamed", retStr.equals("ERROR: Class \"x\" does not exist"));

		d.createClass("z");
		retStr = d.renameClass("y", "z");
		assertTrue("Can rename to class that exists", retStr.equals("ERROR: Class with name \"z\" already exists"));

		retStr = d.renameClass("y", "this.should.fail");
		assertTrue("Renamed to invalid class name", retStr.equals("ERROR: . is an invalid character"));
	}
}
	