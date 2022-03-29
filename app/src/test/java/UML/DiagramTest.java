package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import UML.model.Class;
import UML.model.Diagram;
import UML.model.Field;
import UML.model.Method;
import UML.model.Parameter;
import UML.model.Relationship;

public class DiagramTest {
    @Test
    public void cloneTest()
    {
        Diagram dg = new Diagram();

        dg.createClass("testClass");
        dg.createClass("relTestClass");
        dg.createRelationship("Composition", "testClass", "relTestClass");
        dg.createField("testClass", "testField", "int");

        ArrayList <String> fakeParms = new ArrayList();
        dg.createMethod("testClass", "Type", "testMethod", fakeParms);

        try
        {
            Diagram cloned = dg.copy();
            assertFalse("Clone is still the old object", dg == cloned);
            assertFalse("Class List is pointing to the old object" + dg.classList + cloned.classList, dg.classList == cloned.classList);
            assertFalse("Relationship List is pointing to the old object", dg.relationships == cloned.relationships);

            assertTrue("Different number of classes in clone versus the original", dg.classList.size() == cloned.classList.size());
            assertTrue("Different number of relationships in clone versus the original", dg.relationships.size() == cloned.relationships.size());

            for (int i = 0; i < cloned.classList.size(); ++i)
            {
                Class c1 = dg.classList.get(i);
                Class c2 = cloned.classList.get(i);

                assertFalse("Classes point to the same memory address", c1 == c2);
                assertTrue("Classes do not have the same name", c1.name.equals(c2.name));

                assertFalse("Field ArrayLists not separate objects", c1.fields == c2.fields);
                assertFalse("Method ArrayLists not separate objects", c1.methods == c2.methods);
            
                assertTrue("Field lists are of differing lengths", c1.fields.size() == c2.fields.size());
                assertTrue("Method lists are of differing lengths", c1.methods.size() == c2.methods.size());

                for (int j = 0; j < c2.fields.size(); ++j)
                {
                    Field f1 = c1.fields.get(j);
                    Field f2 = c2.fields.get(j);
                    assertTrue("Field names are not the same", f1.name.equals(f2.name));
                    assertTrue("Field types are not the same", f1.type.equals(f2.type));
                }

                for (int k = 0; k < c2.methods.size(); ++k)
                {
                    Method m1 = c1.methods.get(k);
                    Method m2 = c2.methods.get(k);

                    assertFalse("Parameter lists point to the same place", m1.parameters == m2.parameters);
                    assertTrue("Parameter lists are of differing lengths", m1.parameters.size() == m2.parameters.size());

                    for (int c = 0; c < m2.parameters.size(); c++)
                    {
                        Parameter p1 = m1.parameters.get(c);
                        Parameter p2 = m2.parameters.get(c);

                        assertFalse("Parameters point to the same memory location", p1 == p2);
                        assertTrue("Parameter names are not the same", p1.name.equals(p2.name));
                        assertTrue("Parameter types are not the same", p1.type.equals(p2.type));
                    }
                }
            }

            for (int k = 0; k < cloned.relationships.size(); ++k)
            {
                Relationship r1 = dg.relationships.get(k);
                Relationship r2 = cloned.relationships.get(k);

                assertFalse("Relationships point to the same locations in memory", r1 == r2);

                assertTrue("Relationship types not the same", r1.type.equals(r2.type));
                assertTrue("Relationship sources are not the same", r1.src.equals(r2.src));
                assertTrue("Relationship destinations are not the same", r1.dest.equals(r2.dest));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        
    }
}
