package org.practice.design.practice.notepad;

import java.util.HashMap;
import java.util.Map;

public class Notepad {

    Map<String, History> textEditorHistoryMap;

    public Notepad() {
        textEditorHistoryMap = new HashMap<>();
    }

    public TextEditor createTextEditor(String title) {
        return new TextEditor(title);
    }

    public void addText(TextEditor textEditor, String text) {
        textEditor.addText(text);
        History history = textEditorHistoryMap.getOrDefault(textEditor.getTitle(), new History());
        history.save(textEditor.saveText());
        textEditorHistoryMap.put(textEditor.getTitle(), history);
    }

    public String getText(TextEditor textEditor) {
        return textEditor.getText();
    }

    public void undoText(TextEditor textEditor) {
        History history = textEditorHistoryMap.get(textEditor.getTitle());
        Memento memento = history.undo();
        textEditor.restoreText(memento);
    }

    public void redoText(TextEditor textEditor) {
        History history = textEditorHistoryMap.get(textEditor.getTitle());
        Memento memento = history.redo();
        textEditor.restoreText(memento);
    }

}
