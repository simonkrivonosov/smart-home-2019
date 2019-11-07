package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.CloseDoor;
import ru.sbt.mipt.oop.OpenDoor;

public class HallDoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            for (Room room: smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door: room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            Action hallAction = new TurnOffLight("all");
                            smartHome.execute(hallAction);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}