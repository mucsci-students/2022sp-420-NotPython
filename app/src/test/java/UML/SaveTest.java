/**
 * JUnit tests for Save
 *
 * @author Alex Lim
 *  tests attribute
 */
package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SaveTest {
	/**
	 * 
	 */
	@Test
	public void testSave() {
		//DiagramController s = new DiagramController( );
        DiagramController d = new DiagramController( );
		d.createClass("foo");
        d.createAttribute("foo", "bar");
        d.saveDiagram("foobar.json");
        
        File file = new File("foobar.json");


    try {

        Scanner sc = new Scanner(file);

       
            assertTrue("Save succesful", sc.hasNextLine());
        
        
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
      
	}

}
	