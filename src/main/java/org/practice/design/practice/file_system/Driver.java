package org.practice.design.practice.file_system;

public class Driver {

    public static void main(String[] args) {
        FileManagementSystem fileManagementSystem = new FileManagementSystem();
        fileManagementSystem.mkdir("A/B/C");
        fileManagementSystem.mkdir("A/D/E");
        fileManagementSystem.mkdir("A/F/K");
        System.out.println(fileManagementSystem.ls("A"));
        System.out.println(fileManagementSystem.ls("A/D"));
        fileManagementSystem.addContentToFile("A/D/E/file1.txt", "file1 content");
        System.out.println(fileManagementSystem.readContentFromFile("A/D/E/file1.txt"));
        System.out.println(fileManagementSystem.ls("A/D/E/file1.txt"));
        System.out.println(fileManagementSystem.ls("A/X/E/file1.txt"));
    }

}
