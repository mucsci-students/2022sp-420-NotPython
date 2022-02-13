/**
 * JUnit tests for Class
 *
 * @author Alex Lim
 *  tests attribute
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;


public class ClassTest {
	/**
	 * 
	 */
	@Test
	public void testConstructor() {
		//DiagramController s = new DiagramController( );
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
		DiagramController d = new DiagramController( );
		d.createClass("1");

        d.deleteClass("1");
        assertTrue("Class failed to delete", d.getClass("1") == null);
	}

	@Test
	public void testCreate(){
		DiagramController d = new DiagramController( );
		d.createClass("2");
        d.createClass("3");
        
		assertTrue("Class 2 not created", d.getClass("2") != null);
		assertTrue("Class 3 not created",  d.getClass("3") != null);
		d.deleteClass("2");
        d.deleteClass("3");
        
	}
	@Test
	public void testRenameDC(){
		DiagramController d = new DiagramController( );
		d.createClass("x");
        d.renameClass("x", "y");
		
		assertTrue("Class rename failed", d.getClass("y") != null);
		d.deleteClass("y");

	}
}
	