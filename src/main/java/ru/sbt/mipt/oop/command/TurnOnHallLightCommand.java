package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

public class TurnOnHallLightCommand implements Command {
    private SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                        if (object_new instanceof Light) {
                            Light light = (Light) object_new;
                            light.setOn(true);
                        }
                    });
                }
            }
        });
    }
}
