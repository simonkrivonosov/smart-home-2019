package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

public class TurnOffAllLightCommand implements Command {
    private SmartHome smarthome;

    public TurnOffAllLightCommand(SmartHome smarthome) {
        this.smarthome = smarthome;
    }


    public boolean execute() {
        for(Room room: smarthome.getRooms()) {
            for(Light light: room.getLights()) {
                light.setOn(false);
            }
        }
        return true;
    }
}
