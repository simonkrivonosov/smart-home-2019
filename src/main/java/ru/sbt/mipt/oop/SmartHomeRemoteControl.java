package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.HashMap;

public class SmartHomeRemoteControl implements RemoteControl {
    private final HashMap<String, Command> commands;

    public SmartHomeRemoteControl() {
        commands = new HashMap<>();
    }

    public void setCommandToButton(String button, Command command) {
        if (commands.containsKey(button)) {
            return;
        }

        commands.put(button, command);
    }

    public void onButtonPressed(String buttonCode, String rcId) {
        if(commands.containsKey(buttonCode)) {
            commands.get(buttonCode).execute();
        }
    }

}