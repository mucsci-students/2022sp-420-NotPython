/**
 * JUnit tests for Attribute
 *
 * @author Alex Lim
 *  tests attribute
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;


public class AttributeTest {
	/**
	 * 
	 */
	@Test
	public void testConstructor() {
		//DiagramController s = new DiagramController( );
        Attribute a = new Attribute("hello");
        
        assertTrue("Attribute created succesfully", a != null);
		assertTrue("Attribute has correct name", a.name.equals("hello"));

	}

	@Test
	public void testRename(){
		Attribute a = new Attribute("hello");
		a.rename_attribute("test");

		assertTrue("Attribute renamed succesfully", a.name.equals("test"));
	}

	@Test
	public void testDelete(){
		DiagramController d = new DiagramController( );
		d.createClass("x");
		Class c = d.classList.get(0);
		d.createAttribute("x", "y");
		d.deleteAttribute("x", "y");

		assertTrue("Attribute deleted succesfully", d.getAttribute(c,"y") == null);
        d.deleteClass("x");
	}
	@Test
	public void testCreate(){
		DiagramController d = new DiagramController( );
		d.createClass("x");
		Class c = d.classList.get(0);
		d.createAttribute("x", "y");
        d.createAttribute("x", "z");
		
		assertTrue("Attribute z created succesfully", d.getAttribute(c,"z") != null);
		assertTrue("Attribute y created succesfully", d.getAttribute(c,"y") != null);
		d.deleteClass("x");
        
        
	}
	@Test
	public void testRenameDC(){
		DiagramController d = new DiagramController( );
		d.createClass("x");
		Class c = d.classList.get(0);
		d.createAttribute("x", "y");
        d.renameAttribute("x", "y", "z");
		
		assertTrue("Attribute y renamed succesfully", d.getAttribute(c,"z") != null);
		d.deleteClass("x");

	}
}
	