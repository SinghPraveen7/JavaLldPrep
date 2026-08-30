package org.practice.design.memento;

// represent snapshot
public class Memento {
    final String text;

    public Memento(String text) {
        this.text = text;
    }
    public String getSavedText() {
        return text;
    }
}
