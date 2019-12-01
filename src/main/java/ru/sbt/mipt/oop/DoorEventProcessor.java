package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.CloseDoor;
import ru.sbt.mipt.oop.OpenDoor;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute((objectNew) -> {
                        if (objectNew instanceof Door) {
                            Door door = (Door) objectNew;
                            if (event.getType() == DOOR_OPEN) {
                                Action action = new OpenDoor(event.getObjectId(), room.getName());
                                door.execute(action);
                            } else if (event.getType() == DOOR_CLOSED) {
                                Action action = new CloseDoor(event.getObjectId(), room.getName());
                                door.execute(action);
                            }
                        }
                    });
                }
            });
        }
    }
}
