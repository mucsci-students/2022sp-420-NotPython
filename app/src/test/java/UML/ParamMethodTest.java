/**
 * JUnit tests for Class
 *
 * @author NotPython
 *  tests parameters and methods
 */

package UML;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import UML.model.Parameter;
import UML.model.Method;
import UML.model.Diagram;

public class ParamMethodTest {
    
    @Test
    public void testParameterConstructor(){
        Parameter p = new Parameter("name", "type");
        
        assertTrue("Parameter not created", p != null);
        assertTrue("The type didn't get assigned correctly", p.type.equalsIgnoreCase("type"));
        assertTrue("Parameter name not assigned correctly", p.name.equals("name"));
    }

    @Test
    public void testMethodConstructor()
    {
        ArrayList <String> parms = new ArrayList<String>();
        parms.add("name1");
        parms.add("type1");
        parms.add("name2");
        parms.add("type2");
        parms.add("name3");
        parms.add("type3");
        parms.add("name4");
        parms.add("type4");

        Method m = new Method("return_type", "method_name", parms);
        assertTrue("method return type not set correctly", m.type.equals("return_type"));
        assertTrue("method name not set correctly", m.name.equals("method_name"));

        int j = 0;
        for (int i = 0; i < parms.size()-1; i+=2)
        {
            assertTrue("Parameter name not assigned correctly", m.parameters.get(j).name.equals(parms.get(i)));
            assertTrue("Parameter type not assigned correctly", m.parameters.get(j).type.equals(parms.get(i + 1)));
            ++j;
        }
    }

    @Test
    public void testDiagramAddMethodWithParams()
    {
        ArrayList <String> parms = new ArrayList<String>();
        parms.add("name1");
        parms.add("type1");
        parms.add("name2");
        parms.add("type2");
        parms.add("name3");
        parms.add("type3");
        parms.add("name4");
        parms.add("type4");
        Diagram dg = new Diagram();
        dg.createClass("testClass");
        dg.createMethod("testClass", "return_type", "method_name", parms);

        Method m = dg.getMethod("testClass", "method_name");
        assertTrue("method return type not set correctly", m.type.equals("return_type"));
        assertTrue("method name not set correctly", m.name.equals("method_name"));

        int j = 0;
        for (int i = 0; i < parms.size()-1; i+=2)
        {
            assertTrue("Parameter name not assigned correctly", m.parameters.get(j).name.equals(parms.get(i)));
            assertTrue("Parameter type not assigned correctly", m.parameters.get(j).type.equals(parms.get(i + 1)));
            ++j;
        }

        dg.deleteClass("testClass");
    }

    @Test
    public void TestDiagramAddMethodNoParams()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        assertTrue("Method with no parameters not created", dg.getMethod("dummy", "name") != null);
        assertTrue("Method has parameters that shouldn't exist", dg.getMethod("dummy", "name").parameters.size() == 0);
        dg.deleteMethod("dummy", "name");
        dg.deleteClass("dummy");
    }

    @Test
    public void TestDiagramDeleteMethod()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        dg.deleteMethod("dummy","name");
        assertTrue("Method not deleted", dg.getMethod("dummy", "name") == null);
        dg.deleteClass("dummy");
    }

    @Test
    public void TestDiagramRenameMethod()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        dg.renameMethod("dummy", "name", "newName");
        assertTrue("Method not renamed", dg.getMethod("dummy", "newName") != null);
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        dg.changeParameter("dummy", "name", "parmName", "newParmName", "String");
        assertTrue("Parameter not changed in method", dg.getParameter(m, "newParmName") != null);
        assertTrue("old parameter still exists", dg.getParameter(m, "parmName") == null);
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[10];
        newParms[4] = "name1";
        newParms[5] = "type1";
        newParms[6] = "name2";
        newParms[7] = "type2";
        newParms[8] = "name3";
        newParms[9] = "type3";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        dg.changeParameters("dummy", "name", newParms);
        assertTrue("old parameter still exists", dg.getParameter(m, "parmName") == null);
        
        int num = 1;
        for(int i = 0; i < m.parameters.size(); ++i)
        {
            assertTrue("Parameters not changed in method", dg.getParameter(m, ("name" + num)) != null);
            assertTrue("Parameters not changed in method", dg.getParameter(m, ("name" + num)).type.equals("type" + num));
            ++num;
        }

        dg.deleteClass("dummy");
    }

    @Test
    public void deleteParameterTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        dg.deleteParameter("dummy", "name", "parmName");
        assertTrue("parameter still exists", dg.getParameter(m, "parmName") == null);
        dg.deleteClass("dummy");
    }

    @Test
    public void deleteParametersTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        parms.add("parmName2");
        parms.add("parmType2");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        dg.deleteParameters("dummy", "name");
        assertTrue("old parameter still exists", dg.getParameter(m, "parmName") == null);
        assertTrue("old parameter still exists", dg.getParameter(m, "parmName2") == null);
        dg.deleteClass("dummy");
    }

    
}