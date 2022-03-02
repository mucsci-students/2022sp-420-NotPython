/**
 * JUnit tests for Attribute
 *
 * @author Alex Lim
 *  tests attribute
 *
package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Attribute;
import UML.model.Class;
import UML.model.Diagram;

public class AttributeTest {
	/**
	 * 
	 *
	@Test
	public void testConstructor() {
		//Diagram s = new Diagram( );
        Attribute a = new Attribute("hello");
        
        assertTrue("Attribute not created", a != null);
		assertTrue("Attribute does not have correct name", a.name.equals("hello"));

	}

	@Test
	public void testRename(){
		Attribute a = new Attribute("hello");
		a.rename_attribute("test");

		assertTrue("Attribute rename failed", a.name.equals("test"));
	}

	@Test
	public void testDelete(){
		Diagram d = new Diagram( );
		d.createClass("x");
		Class c = d.getClass("x");
		d.createAttribute("x", "y");
		d.deleteAttribute("x", "y");

		assertTrue("Attribute deleted failed", d.getAttribute(c,"y") == null);
        d.deleteClass("x");
	}
	@Test
	public void testCreate(){
		Diagram d = new Diagram( );
		d.createClass("x");
		Class c = d.getClass("x");
		d.createAttribute("x", "y");
        d.createAttribute("x", "z");
		
		assertTrue("Attribute z not created", d.getAttribute(c,"z") != null);
		assertTrue("Attribute y not created", d.getAttribute(c,"y") != null);
		d.deleteClass("x");
        
        
	}
	@Test
	public void testRenameDC(){
		Diagram d = new Diagram( );
		d.createClass("x");
		Class c = d.getClass("x");
		d.createAttribute("x", "y");
        d.renameAttribute("x", "y", "z");
		
		assertTrue("Attribute y rename failed", d.getAttribute(c,"z") != null);
		d.deleteClass("x");

	}
}
	*/