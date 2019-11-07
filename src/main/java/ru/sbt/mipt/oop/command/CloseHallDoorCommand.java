package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.*;

public class CloseHallDoorCommand implements Command {
    private SmartHome smarthome;

    public CloseHallDoorCommand(SmartHome smarthome) {

        this.smarthome = smarthome;
    }

    public boolean execute() {
        for(Room room: smarthome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    door.setOpen(false);
                }
            }

            for (Light light : room.getLights()) {
                light.setOn(false);
            }
        }
        return true;
    }
}
