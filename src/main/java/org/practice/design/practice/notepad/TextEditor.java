package org.practice.design.practice.notepad;

public class TextEditor {

    private final String title;

    String text = "";

    public TextEditor(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addText(String text) {
        this.text += text;
    }

    public String getText() {
        return text;
    }

    public Memento saveText() {
        return new Memento(text);
    }

    public void restoreText(Memento memento) {
        text = memento.getSavedText();
    }

}
