package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.*;

public class CloseHallDoorCommand implements Command {
    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                                Door door = (Door) object_new;
                                door.setOpen(false);
                            }
                    );
                }
            }
        });
    }
}
