package UML.model;

import UML.model.*;
import java.util.Stack;

public class UndoRedo {

    //singleton instance of undo redo object
    private static UndoRedo undoRedoSingleton = null;

    //stack of diagram objects for undoing
    private Stack <Diagram> undo;

    //stack of diagram objects for redoing
    private Stack <Diagram> redo;

    //private constructor for singleton design pattern
    private UndoRedo()
    {
        undo = new Stack<Diagram>();
        redo = new Stack<Diagram>();
    }

    //get static instance of singleton
    //if it exists already then return it
    //if not then create the instance
    public static UndoRedo getInstance(){
        if (undoRedoSingleton == null){
            undoRedoSingleton = new UndoRedo();
        }
        return undoRedoSingleton;
    }

    //since we can't new anymore we now have to clear the
    //stacks when loading
    public void reset(){
        undo.clear();
        redo.clear();
    }

    //take a snapshot of the current diagram state and put
    //it onto the undo stack
    public void snapshotDiagram(Diagram state)
    {
        undo.push(state);
        redo.clear();
    }

    //get the previous state from undo and then push the current state into the
    //redo stack
    //return the old state
    public Diagram undo(Diagram state)
    {
        Diagram dg = undo.pop();
        redo.push(state);
        return dg;
    }

    //gets the last redo snapshot that was pushed onto the stack from undoing
    //pushes the current state onto the undo stack and then returns the redo diagram
    public Diagram redo(Diagram state)
    {
        Diagram dg = redo.pop();
        undo.push(state);
        return dg;
    }

    //check if we can undo or redo
    public boolean canUndo()
    {
        return !undo.isEmpty();
    }

    public boolean canRedo()
    {
        return !redo.isEmpty();
    }
}
