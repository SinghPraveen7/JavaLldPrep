package org.practice.design.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class History {

    private Deque<Memento> undoStack = new ArrayDeque<>();
    private Deque<Memento> redoStack = new ArrayDeque<>();

    public History() {
        Memento emptyState = new Memento("");
        undoStack.push(emptyState);
    }

    public void save(Memento memento) {
        undoStack.push(memento);
    }

    //undo stack contains current state at top, so to undo we need to return prev state
    public Memento undo() {
        if (undoStack.size() > 1) {
            Memento currentState = undoStack.pop();
            redoStack.push(currentState);
            return undoStack.peek();
        }
        System.out.println("Can't undo more, don't have saved state further!");
        return new Memento("");
    }

    //we need to push prev state in undo as anyone can also undo the redo
    public Memento redo() {
        if (!redoStack.isEmpty()) {
            Memento prevState = redoStack.pop();
            undoStack.push(prevState);
            return prevState;
        }
        System.out.println("Can't redo more, don't have saved state further!");
        return undoStack.peek();
    }

}
