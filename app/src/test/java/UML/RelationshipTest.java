/**
 * JUnit tests for Class
 *
 * @author NotPython
 *  tests relationship
 */

package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Relationship;
import UML.model.Diagram;



public class RelationshipTest {
    
    @Test
    public void testConstructorRelationship(){
        Relationship r = new Relationship("relationship", "tester");
        
        assertTrue("Relationship created successfully", r != null);
        assertTrue("Relationship source has the correct name", r.src.equals("relationship"));
        assertTrue("Relationship destination has the correct name", r.dest.equals("tester"));
    }

    @Test
    public void testCreateRelationship(){
        Diagram d = new Diagram( );
        d.createClass("x");
        d.createClass("y");

        d.createRelationship("x", "y");
        assertTrue("Relationship x,y created succesfully", d.getRelationship("x","y") != null);
        
        d.deleteClass("x");
        d.deleteClass("y");
    }

    @Test
    public void testDeleteRelationship(){
        Diagram d = new Diagram( );
        d.createClass("x");
        d.createClass("y");
        d.createRelationship("x", "y");

        d.deleteRelationship("x", "y");
        assertTrue("Relationship deleted succesfully", d.getRelationship("x","y") == null);
        
        d.deleteClass("x");
        d.deleteClass("y");
    }

}