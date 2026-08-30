package org.practice.design.practice.file_system;

public class File extends Content {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    String data;

    @Override
    boolean isFile() {
        return true;
    }
}
