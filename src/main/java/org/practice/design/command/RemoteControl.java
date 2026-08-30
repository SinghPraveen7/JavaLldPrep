package org.practice.design.command;

public class RemoteControl {

    public void pressButton(Command command) {
        command.execute();
    }

}
