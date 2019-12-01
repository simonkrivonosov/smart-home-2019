package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Alarm;

public class ActivateAlarmCommand implements Command {

    private Alarm alarm;
    private String code;

    public ActivateAlarmCommand(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }


    public void execute() {
        alarm.activate(code);
    }
}
