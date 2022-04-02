package UML.model;

import UML.model.*;
import java.util.Stack;

public class UndoRedo {
    private Stack <Diagram> undo;

    private Stack <Diagram> redo;

    public UndoRedo()
    {
        undo = new Stack<Diagram>();
        redo = new Stack<Diagram>();
    }

    public void snapshotDiagram(Diagram state)
    {
        undo.push(state);
        redo.clear();
    }

    public Diagram undo(Diagram state)
    {
        Diagram dg = undo.pop();
        redo.push(state);
        return dg;
    }

    public Diagram redo(Diagram state)
    {
        Diagram dg = redo.pop();
        undo.push(state);
        return dg;
    }

    public boolean canUndo()
    {
        return !undo.isEmpty();
    }

    public boolean canRedo()
    {
        return !redo.isEmpty();
    }
}
