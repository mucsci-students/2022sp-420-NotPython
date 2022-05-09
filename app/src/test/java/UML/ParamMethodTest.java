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

import UML.model.Diagram;
import UML.model.Class;
import UML.model.Method;
import UML.model.Parameter;

public class ParamMethodTest {
    
    @Test
    public void ParameterConstructorTest(){
        Parameter p = new Parameter("name", "type");
        
        assertTrue("Parameter not created", p != null);
        assertTrue("The type didn't get assigned correctly", p.type.equalsIgnoreCase("type"));
        assertTrue("Parameter name not assigned correctly", p.name.equals("name"));
    }

    @Test
    public void MethodConstructorTest()
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
    public void DiagramAddMethodWithParamsTest()
    {
        Diagram dg = new Diagram();
        dg.createClass("testClass");
        ArrayList <String> parms = new ArrayList<String>();
        parms.add("name1");
        parms.add("type1");
        parms.add("name2");
        parms.add("type2");
        parms.add("name3");
        parms.add("type3");
        parms.add("name4");
        parms.add("type4");
        dg.createMethod("testClass", "return_type", "method_name", parms);

        // Method m = dg.getMethod("testClass", "method_name");
        // assertTrue("Method return type not set correctly", m.type.equals("return_type"));
        // assertTrue("Method name not set correctly", m.name.equals("method_name"));

        parms.add("name!");
        String retStr = dg.createMethod("testClass", "int", "name", parms);
        assertTrue("Incorrect parameters", retStr.equals("ERROR: Parameter list length is incorrect"));

        parms.add("type!");
        dg.createMethod("testClass", "int", "name", parms);
        
        parms.clear();
        parms.add("name1!");
        parms.add("type1");
        retStr = dg.createMethod("testClass", "int", "dummy", parms);
        //assertTrue("Incorrect parameter name", retStr.equals("\"retStr\" in method parameter name"));

        parms.clear();
        parms.add("name1");
        parms.add("type1!");
        retStr = dg.createMethod("testClass", "int", "dummy", parms);
        //assertTrue("Incorrect parameter type", retStr.equals("\"retStr\" in method parameter type"));

        parms.clear();
        parms.add("name1");
        parms.add("type1");
        parms.add("name1");
        parms.add("type1");
        retStr = dg.createMethod("testClass", "int", "dummy", parms);
        assertTrue("Duplicate parameters", retStr.equals("ERROR: Duplicate parameter name \"name1\" in parameter list"));

        retStr = dg.createMethod("dummy", "int", "name", parms);
        assertTrue("Class shouldn't exist", retStr.equals("ERROR: Class with name \"dummy\" does not exist"));
  
        dg.deleteClass("testClass");
    }

    @Test
    public void DiagramAddMethodNoParamsTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        
        assertTrue("Method with no parameters not created", dg.getMethod("dummy", "name") != null);
        assertTrue("Method has parameters that shouldn't exist", dg.getMethod("dummy", "name").parameters.size() == 0);
        
        String retStr = dg.createMethod("dummy", "int", "method:name", parms);
        assertTrue("Error was not thrown for bad method name", retStr.equals("ERROR: : is an invalid character in method name"));

        retStr = dg.createMethod("dummy", "method:int", "name", parms);
        assertTrue("Error was not thrown for bad method type", retStr.equals("ERROR: : is an invalid character in method type"));

        retStr = dg.createMethod("dummy", "construct", "dummy", parms);
        assertTrue("constructor class created", retStr.equals("Method with name \"dummy\" added to class \"dummy\""));


        dg.createMethod("dummy", "int", "mname", parms);
        retStr = dg.createMethod("dummy", "int", "mname", parms);
        assertTrue(retStr, retStr.equals("ERROR: method already exists"));
        
        retStr = dg.createMethod("dummy1", "int", "name", parms);
        assertTrue("Class shouldn't exist", retStr.equals("ERROR: Class with name \"dummy1\" does not exist"));
  
        dg.deleteMethod("dummy", "name");
        dg.deleteClass("dummy");
    }

    @Test
    public void DiagramDeleteMethodTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        
        String retStr = dg.deleteMethod("dummy", "name");
        assertTrue("Cannot delete a method that doesn't exist", retStr.equals("ERROR: Method with name \"name\" for \"dummy\" does not exist"));
        
        dg.createMethod("dummy", "int", "name", parms);
        dg.deleteMethod("dummy","name");
        assertTrue("Method not deleted", dg.getMethod("dummy", "name") == null);
       
        dg.deleteClass("dummy");
        
        retStr = dg.deleteMethod("dummy1", "speed");
        assertTrue("Method cannot be deleted for class that does not exist", retStr.equals("ERROR: Class with name \"dummy1\" does not exist"));
    }
    
    @Test
    public void DiagramRenameMethodTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        dg.createMethod("dummy", "int", "name2", parms);
        
        dg.renameMethod("dummy", "name", "newName");
        assertTrue("Method rename failed", dg.getMethod("dummy", "newName") != null);

        String retStr = dg.renameMethod("dummy", "name11", "newName");
		assertTrue("Method that doesn't exist was renamed", retStr.equals("ERROR: Method with name \"name11\" for \"dummy\" does not exist"));

		retStr = dg.renameMethod("dummy", "newName", "name2");
		assertTrue("Can rename to method that already exists", retStr.equals("ERROR: Method \"name2\" already exists"));

		retStr = dg.renameMethod("dummy", "name2", "this.should.fail");
        assertTrue("Renamed to invalid method name", retStr.equals("ERROR: . is an invalid character"));
        
        dg.deleteClass("dummy");
     
        retStr = dg.renameMethod("dummy1", "name", "name3");
        assertTrue("Method cannot be renamed for class that does not exist", retStr.equals("ERROR: Class \"dummy1\" does not exist"));
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

    @Test
    public void deleteParametersClassNotExistsTest()
    {
        Diagram dg = new Diagram();
        String returned = dg.deleteParameters("notExistsClass", "name");
        assertTrue("className does not exist", returned.equals("ERROR: Class with name \"notExistsClass\" does not exist"));
    }

    @Test
    public void deleteParametersMethodNotExistsTest()
    {
        Diagram dg = new Diagram();
        dg.createClass("class");
        String returned = dg.deleteParameters("class", "notExistsMethod");
        assertTrue("methodName does not exist", returned.equals("ERROR: Method with name \"notExistsMethod\" does not exist"));
        dg.deleteClass("class");
    }

    @Test
    public void deleteParameterClassNotExistsTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.deleteParameter("notExistsClass", "name", "param");
        assertTrue("className does not exist", dg.getClass("notExistsClass") == null);
    }

    @Test
    public void deleteParameterMethodNotExistsTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("class");
        dg.deleteParameter("class", "notExistsMethod", "param");
        assertTrue("methodName does not exist", dg.getMethod("class", "notExistsMethod") == null);
        dg.deleteClass("class");
    }

    @Test
    public void deleteParameterParamNotExistsTest()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters created", m != null);
        dg.deleteParameter("dummy", "name", "parmName");
        assertTrue("parameter doesnt exist", dg.getParameter(m, "parmName") == null);
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError1Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[6];
        newParms[4] = "name1?";
        newParms[5] = "type1";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("name not valid", returned.equals("ERROR: ? is an invalid character in method parameter name"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError2Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[6];
        newParms[4] = "name1";
        newParms[5] = "type1?";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("type not valid", returned.equals("ERROR: ? is an invalid character in method parameter type"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError3Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[8];
        newParms[4] = "name1";
        newParms[5] = "type1";
        newParms[6] = "name1";
        newParms[7] = "type2";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("duplicate parameter name", returned.equals("ERROR: Duplicate parameter name \"name1\" in parameter list"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError4Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[7];
        newParms[4] = "name1";
        newParms[5] = "type1";
        newParms[6] = "name2";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("incorrect parameter list length", returned.equals("ERROR: Parameter list length is incorrect"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError5Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[7];
        newParms[4] = "name1";
        newParms[5] = "type1";
        newParms[6] = "name2";
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("method does not exist", returned.equals("ERROR: Method with name \"name\" does not exist"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParametersError6Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        String [] newParms = new String[7];
        newParms[4] = "name1";
        newParms[5] = "type1";
        newParms[6] = "name2";
        Diagram dg = new Diagram();
        String returned = dg.changeParameters("dummy", "name", newParms);
        assertTrue("class does not exist", returned.equals("ERROR: Class with name \"dummy\" does not exist"));
    }

    @Test
    public void changeParameterError1Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        String returned = dg.changeParameter("dummy", "name", "parmName", "newParmName?", "String");
        assertTrue("name not valid", returned.equals("ERROR: ? is an invalid character in new parameter name"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterError2Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        String returned = dg.changeParameter("dummy", "name", "parmName", "newParmName", "String?");
        assertTrue("type not valid", returned.equals("ERROR: ? is an invalid character in new parameter type"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterError3Test()
    {
        ArrayList <String> parms = new ArrayList<String>();
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        parms.add("parmName");
        parms.add("parmType");
        parms.add("newParmName");
        parms.add("parmType2");
        dg.createMethod("dummy", "int", "name", parms);
        Method m = dg.getMethod("dummy", "name");
        assertTrue("Method with no parameters not created", m != null);
        assertTrue("Parameter not created in method", dg.getParameter(m, "parmName") != null);
        String returned = dg.changeParameter("dummy", "name", "parmName", "newParmName", "String");
        //assertTrue(returned, false);
        assertTrue("duplicate parameter name", returned.equals("ERROR: Parameter with name: \"newParmName\" already exists"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterError4Test()
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
        String returned = dg.changeParameter("dummy", "name", "parmName2", "newParmName", "String");
        assertTrue("old parameter does not exist", returned.equals("ERROR: Parameter with name \"parmName2\" does not exist"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterError5Test()
    {
        
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        String returned = dg.changeParameter("dummy", "name", "parmName", "newParmName", "String");
        assertTrue("method does not exist", returned.equals("ERROR: Method with name \"name\" does not exist"));
        dg.deleteClass("dummy");
    }

    @Test
    public void changeParameterError6Test()
    {
        
        Diagram dg = new Diagram();
        String returned = dg.changeParameter("dummy", "name", "parmName", "newParmName", "String");
        assertTrue("class does not exist", returned.equals("ERROR: Class with name \"dummy\" does not exist"));
    }
    
}