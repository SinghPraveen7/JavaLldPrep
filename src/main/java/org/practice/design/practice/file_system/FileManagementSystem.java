package org.practice.design.practice.file_system;

import java.util.*;

public class FileManagementSystem {

    private Directory root;

    public FileManagementSystem() {
        root = new Directory();
    }


    public Set<String> ls(String path) {
        List<String> pathList = Arrays.stream(path.split("/")).toList();
        Set<String> result = new HashSet<>();
        Map<String, Content> currentDirectory = root.getDirectoryContent();
        int i = 0;
        while (currentDirectory != null && i < (pathList.size() - 1)) {
            Directory currentContent = (Directory) currentDirectory.get(pathList.get(i++));
            if (currentContent == null) {
                System.out.println("Incorrect path!");
                currentDirectory = null;
                break;
            }
            currentDirectory = currentContent.getDirectoryContent();
        }
        if (currentDirectory == null) {
            return result;
        }
        if (currentDirectory.containsKey(pathList.get(i)) && currentDirectory.get(pathList.get(i)).isFile()) {
            if (currentDirectory.containsKey(pathList.get(i))) {
                result.add(pathList.get(i));
            }
        } else {
            Directory directory = (Directory) currentDirectory.get(pathList.get(i));
            result.addAll(directory.getDirectoryContent().keySet());
        }
        return result;
    }

    public void mkdir(String path) {
        List<String> pathList = Arrays.stream(path.split("/")).toList();
        Map<String, Content> currentDirectory = root.getDirectoryContent();
        int i = 0;
        while (i < pathList.size()) {
            Directory currentContent = (Directory) currentDirectory.get(pathList.get(i));
            if (currentContent == null) {
                currentContent = new Directory();
                currentContent.setName(pathList.get(i));
                currentDirectory.put(pathList.get(i), currentContent);
            }
            currentDirectory = currentContent.getDirectoryContent();
            i++;
        }
    }

    public void addContentToFile(String filePath, String content) {
        List<String> pathList = Arrays.stream(filePath.split("/")).toList();
        Map<String, Content> currentDirectory = root.getDirectoryContent();
        int i = 0;
        while (i < (pathList.size() - 1)) {
            Directory currentContent = (Directory) currentDirectory.get(pathList.get(i));
            if (currentContent == null) {
                currentContent = new Directory();
                currentContent.setName(pathList.get(i));
                currentDirectory.put(pathList.get(i), currentContent);
            }
            currentDirectory = currentContent.getDirectoryContent();
            i++;
        }
        if (currentDirectory != null && !currentDirectory.isEmpty() && currentDirectory.containsKey(pathList.get(i))) {
            File file = (File) currentDirectory.get(pathList.get(i));
            file.setData(content);
        } else {
            File file = new File();
            file.setData(content);
            file.setName(pathList.get(i));
            currentDirectory.put(pathList.get(i), file);
        }
    }

    public String readContentFromFile(String filePath) {
        List<String> pathList = Arrays.stream(filePath.split("/")).toList();
        Map<String, Content> currentDirectory = root.getDirectoryContent();
        int i = 0;
        while (i < (pathList.size() - 1)) {
            Directory currentContent = (Directory) currentDirectory.get(pathList.get(i));
            if (currentContent == null) {
                currentContent = new Directory();
                currentContent.setName(pathList.get(i));
                currentDirectory.put(pathList.get(i), currentContent);
            }
            currentDirectory = currentContent.getDirectoryContent();
            i++;
        }
        if (currentDirectory != null && currentDirectory.containsKey(pathList.get(i))) {
            File file = (File) currentDirectory.get(pathList.get(i));
            return file.getData();
        } else {
            System.out.println("File does not exist!");
            return null;
        }
    }


}
