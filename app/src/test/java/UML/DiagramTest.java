package UML;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

import UML.model.Class;
import UML.model.Diagram;
import UML.model.Field;
import UML.model.Method;
import UML.model.Parameter;
import UML.model.Relationship;

public class DiagramTest {
    @Test
    public void cloneTest() {
        Diagram dg = new Diagram();

        dg.createClass("testClass");
        dg.createClass("relTestClass");
        dg.createRelationship("Composition", "testClass", "relTestClass");
        dg.createField("testClass", "testField", "int");

        ArrayList<String> fakeParms = new ArrayList();
        dg.createMethod("testClass", "Type", "testMethod", fakeParms);

        try {
            Diagram cloned = dg.clone();
            assertFalse("Clone is still the old object", dg == cloned);
            // cloned.classList.get(0).rename("Bob");
            // assertFalse("Names same",
            // cloned.classList.get(0).name.equals(dg.classList.get(0).name) );
            assertFalse("Relationship List is pointing to the old object", dg.relationships == cloned.relationships);
            assertFalse("Class List is pointing to the old object" + dg.classList + cloned.classList,
                    dg.classList == cloned.classList);

            assertTrue("Different number of classes in clone versus the original",
                    dg.classList.size() == cloned.classList.size());
            assertTrue("Different number of relationships in clone versus the original",
                    dg.relationships.size() == cloned.relationships.size());

            for (int i = 0; i < cloned.classList.size(); ++i) {
                Class c1 = dg.classList.get(i);
                Class c2 = cloned.classList.get(i);

                assertFalse("Classes point to the same memory address", c1 == c2);
                assertTrue("Classes do not have the same name", c1.name.equals(c2.name));

                assertFalse("Field ArrayLists not separate objects", c1.fields == c2.fields);
                assertFalse("Method ArrayLists not separate objects", c1.methods == c2.methods);

                assertTrue("Field lists are of differing lengths", c1.fields.size() == c2.fields.size());
                assertTrue("Method lists are of differing lengths", c1.methods.size() == c2.methods.size());

                for (int j = 0; j < c2.fields.size(); ++j) {
                    Field f1 = c1.fields.get(j);
                    Field f2 = c2.fields.get(j);
                    assertTrue("Field names are not the same", f1.name.equals(f2.name));
                    assertTrue("Field types are not the same", f1.type.equals(f2.type));
                }

                for (int k = 0; k < c2.methods.size(); ++k) {
                    Method m1 = c1.methods.get(k);
                    Method m2 = c2.methods.get(k);

                    assertFalse("Parameter lists point to the same place", m1.parameters == m2.parameters);
                    assertTrue("Parameter lists are of differing lengths",
                            m1.parameters.size() == m2.parameters.size());

                    for (int c = 0; c < m2.parameters.size(); c++) {
                        Parameter p1 = m1.parameters.get(c);
                        Parameter p2 = m2.parameters.get(c);

                        assertFalse("Parameters point to the same memory location", p1 == p2);
                        assertTrue("Parameter names are not the same", p1.name.equals(p2.name));
                        assertTrue("Parameter types are not the same", p1.type.equals(p2.type));
                    }
                }
            }

            for (int k = 0; k < cloned.relationships.size(); ++k) {
                Relationship r1 = dg.relationships.get(k);
                Relationship r2 = cloned.relationships.get(k);

                assertFalse("Relationships point to the same locations in memory", r1 == r2);

                assertTrue("Relationship types not the same", r1.type.equals(r2.type));
                assertTrue("Relationship sources are not the same", r1.src.equals(r2.src));
                assertTrue("Relationship destinations are not the same", r1.dest.equals(r2.dest));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getRelationshipsTest() {
        Diagram dg = new Diagram();
        dg.createClass("name1");
        dg.createClass("name2");
        dg.createClass("name3");
        dg.createRelationship("Aggregation", "name1", "name2");
        dg.createRelationship("Composition", "name2", "name3");
        dg.createRelationship("Realization", "name3", "name1");

        assertTrue("Relationship not created", dg.getRelationship("name1", "name2") != null);
        assertTrue("Relationship not created", dg.getRelationship("name2", "name3") != null);
        assertTrue("Relationship not created", dg.getRelationship("name3", "name1") != null);

        ArrayList<Relationship> rels = dg.getRelationships();
        assertTrue("Relationship not copied", rels.get(0) != null);
        assertTrue("Relationship type not correct", rels.get(0).type.equalsIgnoreCase("Aggregation"));
        assertTrue("Relationship type not correct", rels.get(0).src.equals("name1"));
        assertTrue("Relationship type not correct", rels.get(0).dest.equals("name2"));

        assertTrue("Relationship not copied", rels.get(1) != null);
        assertTrue("Relationship type not correct", rels.get(1).type.equalsIgnoreCase("Composition"));
        assertTrue("Relationship type not correct", rels.get(1).src.equals("name2"));
        assertTrue("Relationship type not correct", rels.get(1).dest.equals("name3"));

        assertTrue("Relationship not copied", rels.get(2) != null);
        assertTrue("Relationship type not correct", rels.get(2).type.equalsIgnoreCase("Realization"));
        assertTrue("Relationship type not correct", rels.get(2).src.equals("name3"));
        assertTrue("Relationship type not correct", rels.get(2).dest.equals("name1"));

    }

    @Test
    public void fieldsToStringTest() {
        Diagram dg = new Diagram();
        dg.createClass("name");
        dg.createField("name", "fldName", "fldType");

        assertTrue("fields to string does not return what it returns",
                dg.fieldsToString("name").equals(" fldType fldName\n"));
    }

    @Test
    public void getFieldSizeTest() {
        Diagram dg = new Diagram();

        dg.createClass("name");
        dg.createField("name", "fldName", "fldType");
        dg.createField("name", "newName", "newType");

        assertTrue("This returns the wrong size", dg.getFieldSize("name") == 2);
    }

    @Test
    public void getMethodSizeTest() {
        Diagram dg = new Diagram();

        ArrayList<String> fakeParms = new ArrayList<String>();

        dg.createClass("name");
        dg.createMethod("name", "type", "methodName", fakeParms);
        dg.createMethod("name", "int", "dummy", fakeParms);

        assertTrue("This returns the wrong size", dg.getMethodSize("name") == 2);
    }

    @Test
    public void validation_checkTest() {
        Diagram dg = new Diagram();
        assertTrue(dg.validation_check("connor.field").equals("ERROR: . is an invalid character"));
    }

    @Test
    public void convertClassListArrayTest() {
        Diagram dg = new Diagram();
        dg.createClass("name");
        dg.createClass("name1");

        String[] classArr = dg.convertClassListArray();
        for (int i = 0; i < dg.classList.size(); ++i) {
            assertTrue("Class Array element does not equal class arraylist elem",
                    dg.classList.get(i).name.equals(classArr[i]));
        }
    }

    @Test
    public void convertFieldListArrayTest() {
        Diagram dg = new Diagram();
        dg.createClass("name");
        dg.createField("name", "fldName", "fldType");
        dg.createField("name", "newName", "type");

        String[] fieldArr = dg.convertFieldListArray("name");
        Class c = dg.getClass("name");
        for (int i = 0; i < c.fields.size(); ++i) {
            assertTrue("Field Array element does not equal Field arraylist elem",
                    c.fields.get(i).name.equals(fieldArr[i]));
        }
    }

    @Test
    public void convertMethodListArrayTest() {
        Diagram dg = new Diagram();
        ArrayList<String> fakeParms = new ArrayList<String>();
        dg.createClass("name");
        dg.createMethod("name", "fldName", "fldType", fakeParms);
        dg.createMethod("name", "newName", "type", fakeParms);

        String[] methodArr = dg.convertMethodListArray("name");
        Class c = dg.getClass("name");
        for (int i = 0; i < c.methods.size(); ++i) {
            assertTrue("Field Array element does not equal Field arraylist elem",
                    c.methods.get(i).name.equals(methodArr[i]));
        }
    }

    @Test
    public void convertMethodParamsListArrayTest() {
        Diagram dg = new Diagram();
        ArrayList<String> fakeParms = new ArrayList<String>();
        fakeParms.add("name1");
        fakeParms.add("type1");
        fakeParms.add("name2");
        fakeParms.add("type2");

        dg.createClass("name");
        dg.createMethod("name", "fldType", "fldName", fakeParms);
        Method m = dg.getMethod("name", "fldName");
        String[] methodArr = dg.convertMethodParamsListArray("name", "fldName");
        for (int i = 0; i < m.parameters.size(); ++i) {
            assertTrue("Should be equal", m.parameters.get(i).name.equals(methodArr[i]));
        }
    }

    @Test
    public void copyGUILocationsTest() {
        HashMap<String, String> fake = new HashMap<String, String>();
        Diagram dg = new Diagram();
        dg.copyGUILocations(fake);

        assertTrue("These should be equal", dg.locations == fake);
    }

    @Test
    public void getLocationsTest() {
        Diagram dg = new Diagram();
        assertTrue("This should equal locations", dg.getLocations() == dg.locations);
    }

    @Test
    public void methodsToStringTest() {
        Diagram dg = new Diagram();
        dg.createClass("name");
        ArrayList<String> fakeParms = new ArrayList<String>();
        fakeParms.add("parmName");
        fakeParms.add("parmType");
        fakeParms.add("dumb");
        fakeParms.add("int");
        dg.createMethod("name", "type", "methodName", fakeParms);

        String toString = dg.methodsToString("name");
        assertTrue("v" + toString + "v", toString.equals("  type methodName (parmType parmName, int dumb)  \n"));
    }
}
