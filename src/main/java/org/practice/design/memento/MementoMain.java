package org.practice.design.memento;

/**
 * The Memento Pattern is a behavioral design pattern used to capture and store an object's internal state so that
 * it can be restored later without violating encapsulation. It is commonly used to implement undo/redo functionality.
 * The pattern consists of three participants: the Originator, which creates and restores snapshots; the Memento,
 * which stores the object's state; and the Caretaker, which manages the saved snapshots.
 * A classic example is a text editor where each edit creates a snapshot, and pressing Ctrl+Z restores a previous state.
 * The main advantages are support for undo functionality and preservation of encapsulation,
 * while the main disadvantage is increased memory usage when many snapshots are stored.
 */
public class MementoMain {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        History history = new History();
        textEditor.addText("Hello");
        history.save(textEditor.saveText());
        System.out.println("1st saved text: " + textEditor.getText());
        textEditor.addText(" World");
        history.save(textEditor.saveText());
        System.out.println("2nd saved text: " + textEditor.getText());
        textEditor.addText(" Java");
        history.save(textEditor.saveText());
        System.out.println("3rd saved text: " + textEditor.getText());
        textEditor.restoreText(history.undo());
        System.out.println("After 1st undo: " + textEditor.getText());
        textEditor.restoreText(history.undo());
        System.out.println("After 2nd undo: " + textEditor.getText());
        textEditor.restoreText(history.undo());
        System.out.println("After 3nd undo: " + textEditor.getText());
        textEditor.restoreText(history.undo());
        System.out.println("After 4th undo: " + textEditor.getText());
        textEditor.restoreText(history.redo());
        System.out.println("After 1st redo: " + textEditor.getText());
        textEditor.restoreText(history.redo());
        System.out.println("After 2nd redo: " + textEditor.getText());
        textEditor.restoreText(history.undo());
        System.out.println("After 1st undo: " + textEditor.getText());
        textEditor.restoreText(history.redo());
        System.out.println("After 1st redo: " + textEditor.getText());
        textEditor.restoreText(history.redo());
        System.out.println("After 2nd redo: " + textEditor.getText());
        textEditor.restoreText(history.redo());
        System.out.println("After 3rd redo: " + textEditor.getText());
    }
}
