package UML.view;

import UML.view.*;
import java.util.Stack;

public class GUIUndoRedo {
    private Stack <GUI> undo;

    private Stack <GUI> redo;

    public GUIUndoRedo()
    {
        undo = new Stack<GUI>();
        redo = new Stack<GUI>();
    }

    public void snapshotGUI(GUI state)
    {
        undo.push(state);
        redo.clear();
    }

    public GUI undo(GUI state)
    {
        GUI dg = undo.pop();
        redo.push(state);
        return dg;
    }

    public GUI redo(GUI state)
    {
        GUI dg = redo.pop();
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
