package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Alarm;

public class SetDangerModeCommand implements Command {
    private Alarm alarm;

    public SetDangerModeCommand(Alarm alarm) {

        this.alarm = alarm;
    }

    public void execute() {
        alarm.setDangerMode();
    }
}
