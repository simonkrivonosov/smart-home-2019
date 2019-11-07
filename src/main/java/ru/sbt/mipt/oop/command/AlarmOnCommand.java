package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Alarm;

public class AlarmOnCommand implements Command {
    private Alarm alarm;

    public AlarmOnCommand(Alarm alarm) {

        this.alarm = alarm;
    }

    public boolean execute() {
        alarm.alarm();
        return true;
    }
}
