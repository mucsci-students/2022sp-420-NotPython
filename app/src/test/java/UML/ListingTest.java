package UML;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import UML.model.Diagram;

public class ListingTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream capturer = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(capturer));
    }

    @Test
    public void tryTestingPrint(){
        System.out.print("diagram");
        assertEquals("diagram", capturer.toString());
        capturer.reset();
    }

    @Test
    public void TestListClass(){
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.listClass("dummy");
        String [] lines = capturer.toString().split("\n");
        assertTrue("\"" + lines[0] + "\"", lines[0].equals(" _______________\r"));
        assertTrue("\"" + lines[1] + "\"", lines[1].equals("|   <> dummy    |\r"));
        assertTrue("\"" + lines[2] + "\"", lines[2].equals("|===============|\r"));
        assertTrue("\"" + lines[3] + "\"", lines[3].equals("|_______________|\r"));
        capturer.reset();
    }

    @Test
    public void TestListClasses(){
        Diagram dg = new Diagram();
        dg.createClass("dummy");
        dg.createClass("nClass");
        dg.listClasses();
        String [] lines = capturer.toString().split("\n");
        assertTrue("\"" + lines[0] + "\"", lines[0].equals(" _______________\r"));
        assertTrue("\"" + lines[1] + "\"", lines[1].equals("|   <> dummy    |\r"));
        assertTrue("\"" + lines[2] + "\"", lines[2].equals("|===============|\r"));
        assertTrue("\"" + lines[3] + "\"", lines[3].equals("|_______________|\r"));
        assertTrue("\"" + lines[4] + "\"", lines[4].equals(" ________________\r"));
        assertTrue("\"" + lines[5] + "\"", lines[5].equals("|   <> nClass    |\r"));
        assertTrue("\"" + lines[6] + "\"", lines[6].equals("|================|\r"));
        assertTrue("\"" + lines[7] + "\"", lines[7].equals("|________________|\r"));
        capturer.reset();
    }

    @Test
    public void TestListRelationships(){
        Diagram dg = new Diagram();
        dg.createClass("src");
        dg.createClass("dest");
        dg.createRelationship("Aggregation", "src", "dest");
        dg.listRelationships();
        String [] lines = capturer.toString().split("\n");
        assertTrue("\"" + lines[0] + "\"", lines[0].equals(" _____________                                        ______________\r"));
        assertTrue("\"" + lines[1] + "\"", lines[1].equals("|   <> src    |     --------Aggregation-------->     |   <> dest    |\r"));
        assertTrue("\"" + lines[2] + "\"", lines[2].equals("|_____________|                                      |______________|\r"));
        capturer.reset();
    }


    @After
    public void tearDown(){
        System.setOut(stdOut);
    }
}
