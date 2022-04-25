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
        Relationship r = new Relationship("Aggregation", "relationship", "tester");
        
        assertTrue("Relationship not created", r != null);
        assertTrue("The type didn't get assigned", r.type.equalsIgnoreCase("Aggregation"));
        assertTrue("Relationship source does not have the correct name", r.src.equals("relationship"));
        assertTrue("Relationship destination does not have the correct name", r.dest.equals("tester"));
    }

    @Test
    public void testCreateRelationship(){
        Diagram d = new Diagram( );
        d.createClass("x");
        d.createClass("y");

        d.createRelationship("Inheritance", "x", "y");
        assertTrue("Relationship x,y not created", d.getRelationship("x","y") != null);
        assertTrue("The type is incorrect or was not assigned", d.getRelationship("x","y").type.equalsIgnoreCase("Inheritance"));

        d.createRelationship("Aggregation", "y", "x");
        
        String retStr = d.createRelationship("Aggregation", "y", "y");
        assertTrue("Created a relationship between class and itself", retStr.equals("ERROR: relationship cannot exist between a class and itself"));

        retStr = d.createRelationship("Aggregation", "z", "2");
        assertTrue("Created relationship for src class that does not exist", retStr.equals("ERROR: Class with name \"z\" does not exist"));

        retStr = d.createRelationship("Aggregation", "x", "2");
        assertTrue("Created relationship for dest class that does not exist", retStr.equals("ERROR: Class with name \"2\" does not exist"));

        retStr = d.createRelationship("Aggregation", "y", "x");
        assertTrue("Created relationship that already exists", retStr.equals("ERROR: Relationship from y to x of type Aggregation already exists"));
    }

    @Test
    public void testDeleteRelationship(){
        Diagram d = new Diagram( );
        d.createClass("x");
        d.createClass("y");
        d.createRelationship("Composition", "x", "y");

        d.deleteRelationship("x", "y");
        assertTrue("Relationship deleted successfully", d.getRelationship("x","y") == null);
        
        String retStr = d.deleteRelationship("x", "y");
        assertTrue("Relationship should not exist", retStr.equals("ERROR: Relationship from x to y does not exist"));
    }

    @Test
    public void testRelationshipType(){
        Diagram d = new Diagram();
        
        d.createClass("1");
        d.createClass("2");
        d.createClass("3");
        d.createClass("4");
        d.createClass("5");

        d.createRelationship("type", "1", "2");
        assertTrue("Relationship with invalid type was created", d.getRelationship("1", "2") == null);

        d.createRelationship("Aggregation", "1", "2");
        assertTrue("Relationship of type aggregation was not created", d.getRelationship("1", "2") != null);

        d.createRelationship("composition", "2", "3");
        assertTrue("Relationship of type composition was not created", d.getRelationship("2", "3") != null);

        d.createRelationship("Inheritance", "3", "4");
        assertTrue("Relationship of type inheritance was not created", d.getRelationship("3", "4") != null);

        d.createRelationship("realization", "4", "5");
        assertTrue("Relationship of type realization was not created", d.getRelationship("4", "5") != null);

        d.deleteClass("1");
        d.deleteClass("2");
        d.deleteClass("3");
        d.deleteClass("4");
        d.deleteClass("5");
    }

}