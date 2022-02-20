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
import UML.model.*;

public class SaveTest {
	/**
	 * 
	 */
	@Test
	public void testSave() {
        Diagram d = new Diagram( );
		d.createClass("foo");
        d.createAttribute("foo", "bar");
        d.saveDiagram("foobar.json");
        
        File file = new File("foobar.json");

    try {

        Scanner sc = new Scanner(file);
        assertTrue("Save failed", sc.hasNextLine());

        sc.close();
        assertTrue("File not deleted", file.delete());
        
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
      
	}

}
	