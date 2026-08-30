package org.practice.design.practice.notepad;

public class NotepadDriver {
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        TextEditor textEditor1 = notepad.createTextEditor("Editor-1");
        notepad.addText(textEditor1, "Hello");
        notepad.addText(textEditor1, " World");
        notepad.addText(textEditor1, " - Java");
        System.out.println(textEditor1.getTitle() + " text: " + notepad.getText(textEditor1));
        TextEditor textEditor2 = notepad.createTextEditor("Editor-2");
        notepad.addText(textEditor2, "A");
        notepad.addText(textEditor2, " B");
        notepad.addText(textEditor2, " C");
        System.out.println(textEditor2.getTitle() + " text: " + notepad.getText(textEditor2));
        notepad.undoText(textEditor1);
        System.out.println(textEditor1.getTitle() + " text after 1st Undo: " + notepad.getText(textEditor1));
        notepad.undoText(textEditor1);
        System.out.println(textEditor1.getTitle() + " text after 2nd Undo: " + notepad.getText(textEditor1));
        notepad.redoText(textEditor1);
        System.out.println(textEditor1.getTitle() + " text after 1st Redo: " + notepad.getText(textEditor1));
        notepad.undoText(textEditor2);
        System.out.println(textEditor2.getTitle() + " text after 1st Undo: " + notepad.getText(textEditor2));
        notepad.redoText(textEditor2);
        System.out.println(textEditor2.getTitle() + " text after 1st Redo: " + notepad.getText(textEditor2));
    }
}
