package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Light;

public class TurnOnAllLightCommand implements Command {
    private SmartHome smartHome;

    public TurnOnAllLightCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
    }
}