package UML.view;

import UML.view.*;
import java.util.Stack;

public class GUIUndoRedo {
    // singleton instance of GUI undo redo object
    private static GUIUndoRedo GUIUndoRedoSingleton = null;

    // stack of GUI objects for undoing
    private Stack<GUI> undo;

    // stack of GUI objects for redoing
    private Stack<GUI> redo;

    // private constructor for singleton design pattern
    private GUIUndoRedo() {
        undo = new Stack<GUI>();
        redo = new Stack<GUI>();
    }

    // get static instance of singleton
    // if it exists already then return it
    // if not then create the instance
    public static GUIUndoRedo getInstance() {
        if (GUIUndoRedoSingleton == null) {
            GUIUndoRedoSingleton = new GUIUndoRedo();
        }
        return GUIUndoRedoSingleton;
    }

    // since we can't new anymore we now have to clear the
    // stacks when loading
    public void reset() {
        undo.clear();
        redo.clear();
    }

    // take snapshot of the GUI object and store the
    // current state in the undo stack
    public void snapshotGUI(GUI state) {
        undo.push(state);
        redo.clear();
    }

    // get the current GUI state and push it into the redo stack
    // get the previous GUI state and return it
    public GUI undo(GUI state) {
        GUI dg = undo.pop();
        redo.push(state);
        return dg;
    }

    // get the current GUI state and push it into the undo stack
    // get the redone state and return it
    public GUI redo(GUI state) {
        GUI dg = redo.pop();
        undo.push(state);
        return dg;
    }

    // check if the stack has anything and we can undo
    public boolean canUndo() {
        return !undo.isEmpty();
    }

    // check if the stack has anything and we can redo
    public boolean canRedo() {
        return !redo.isEmpty();
    }
}
