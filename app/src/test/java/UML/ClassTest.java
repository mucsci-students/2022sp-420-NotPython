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
        
        assertTrue("Class created succesfully", c != null);
        assertTrue("Class has correct name", c.name.equals("hello"));
        
	}

	@Test
	public void testRename(){
        Class c = new Class("hello");
		c.rename("test");

		assertTrue("Class renamed succesfully", c.name.equals("test"));
	}

	@Test
	public void testDelete(){
		DiagramController d = new DiagramController( );
		d.createClass("1");
		Class c = d.classList.get(0);

        d.deleteClass("1");
        assertTrue("Class deleted succesfully", d.getClass("1") == null);
	}
	@Test
	public void testCreate(){
		DiagramController d = new DiagramController( );
		d.createClass("2");
        d.createClass("3");

		Class c = d.classList.get(0);
        
		assertTrue("Class created succesfully", d.getClass("2") != null);
		assertTrue("Class created succesfully",  d.getClass("3") != null);
		d.deleteClass("2");
        d.deleteClass("3");
        
	}
	@Test
	public void testRenameDC(){
		DiagramController d = new DiagramController( );
		d.createClass("x");
		Class c = d.classList.get(0);
		
        d.renameClass("x", "y");
		
		assertTrue("Class x renamed succesfully", d.getClass("y") != null);
		d.deleteClass("y");

	}
}
	