package org.practice.design.practice.file_system;

import java.util.Map;
import java.util.TreeMap;

public class Directory extends Content {
    public Map<String, Content> getDirectoryContent() {
        return directoryContent;
    }

    public void setDirectoryContent(Map<String, Content> directoryContent) {
        this.directoryContent = directoryContent;
    }

    Map<String, Content> directoryContent;

    public Directory() {
        directoryContent = new TreeMap<>();
    }

    @Override
    boolean isFile() {
        return false;
    }
}
