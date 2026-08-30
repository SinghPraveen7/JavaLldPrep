package org.practice.design.memento;

public class TextEditor {

    String text = "";

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
