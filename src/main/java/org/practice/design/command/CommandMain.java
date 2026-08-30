package org.practice.design.command;

/**
 * The Command Pattern is a behavioral design pattern that encapsulates a request as an object.
 * This decouples the sender of a request from the receiver that executes it.
 * It enables features such as undo/redo, request queueing, logging, and scheduling.
 * The pattern consists of a Command interface, Concrete Command implementations, a Receiver that performs the actual work,
 * and an Invoker that triggers command execution. A classic Java example is Runnable used with ExecutorService,
 * where tasks are encapsulated as objects and executed later.
 * Command Pattern is widely used in task schedulers, UI actions, and event-driven systems.
 */
public class CommandMain {

    public static void main(String[] args) {
        TV tv = new TV();
        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.pressButton(turnOn);
        remoteControl.pressButton(turnOff);

    }

}
