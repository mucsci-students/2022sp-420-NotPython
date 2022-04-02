package UML;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Diagram;

public class UndoRedoTest {
    @Test
    public void UndoTest() {
        Diagram dg = new Diagram();
        assertTrue("Undo should throw an error here",
                dg.undo().equals("ERROR: No undoable operations have been completed"));
        assertFalse("Can undo before doing any actions", dg.undoRedo.canUndo());

        dg.createClass("name");
        assertTrue("Can't undo after completing redoable action", dg.undoRedo.canUndo());

        dg.undo();
        assertTrue("Class still exists after undo", dg.getClass("name") == null);
    }

    @Test
    public void redoTest() {
        Diagram dg = new Diagram();
        assertFalse("Can redo before doing any actions", dg.undoRedo.canRedo());

        dg.createClass("name");
        assertFalse("Can redo before undoing", dg.undoRedo.canRedo());

        dg.undo();
        assertTrue("Can't redo after undo", dg.undoRedo.canRedo());

        dg.redo();
        assertTrue("class exists after redo", dg.getClass("name") != null);
    }
}
