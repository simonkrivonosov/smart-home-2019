package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.HashMap;

public class SmartHomeRemoteControl implements RemoteControl {
    HashMap<String, Command> commands;
    String rcId;

    public SmartHomeRemoteControl(String initRcId) {
        commands = new HashMap<>();
        this.rcId = initRcId;
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