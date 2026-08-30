package org.practice.design.practice.file_system;

abstract class Content {

    abstract boolean isFile();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
