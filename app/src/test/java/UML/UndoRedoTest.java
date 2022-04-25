package UML;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import static org.junit.Assert.*;
import UML.model.Diagram;
import UML.model.UndoRedo;

public class UndoRedoTest {
    @Test
    public void singletonTest() {
        UndoRedo undoRedo = UndoRedo.getInstance();
        assertTrue("Singleton does not return an instance", undoRedo != null);
        assertTrue("undo redo is the wrong type", undoRedo instanceof UndoRedo);
    }

    @Test
    public void snapshotTest() {
        UndoRedo undoRedo = UndoRedo.getInstance();
        Diagram dg = new Diagram();
        undoRedo.snapshotDiagram(dg);

        assertTrue("check that we can undo after snapshotting", undoRedo.canUndo());
        undoRedo.undo(dg);
        undoRedo.snapshotDiagram(dg);
        assertTrue("We should not be able to redo here", !undoRedo.canRedo());
        undoRedo.reset();
    }

    @Test
    public void resetTest() {
        UndoRedo undoRedo = UndoRedo.getInstance();
        Diagram dg = new Diagram();
        undoRedo.snapshotDiagram(dg);
        undoRedo.snapshotDiagram(dg);
        undoRedo.snapshotDiagram(dg);

        assertTrue("check that we can undo and also that method works", undoRedo.canUndo());
        dg = undoRedo.undo(dg);
        assertTrue("check that we can redo after undo and also that method works", undoRedo.canRedo());
        undoRedo.reset();
        assertTrue("Reset doesn't work", !undoRedo.canUndo());
        assertTrue("Reset doesn't work", !undoRedo.canRedo());
    }

    // test undo in diagram
    @Test
    public void UndoTest() {
        Diagram dg = new Diagram();
        UndoRedo undoRedo = UndoRedo.getInstance();
        undoRedo.reset();
        String compStr = dg.undo();
        assertTrue(
                "Undo should throw an error here\n" + compStr + "\nERROR: No undoable operations have been completed\n",
                compStr.equals("ERROR: No undoable operations have been completed"));
        assertFalse("Can undo before doing any actions", undoRedo.canUndo());

        dg.createClass("name");
        dg.undo();
        assertTrue("Class still exists after undo", dg.getClass("name") == null);
    }

    // test redo in diagram
    @Test
    public void redoTest() {
        Diagram dg = new Diagram();
        UndoRedo undoRedo = UndoRedo.getInstance();
        undoRedo.reset();
        dg.createClass("name");
        String compStr = dg.redo();
        assertTrue("Can redo before undoing", compStr.equals("ERROR: No command has been undone"));

        compStr = dg.undo();

        assertTrue("Can't redo after undo", compStr.equals("Undo successful"));

        compStr = dg.redo();
        assertTrue("Redo was not successful", compStr.equals("Redo successful"));
        assertTrue("class exists after redo", dg.getClass("name") != null);
        dg.undoRedo = null;
    }
}
