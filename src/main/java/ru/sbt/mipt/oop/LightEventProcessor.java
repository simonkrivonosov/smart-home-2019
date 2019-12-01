package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.TurnOffLight;
import ru.sbt.mipt.oop.TurnOnLight;

public class LightEventProcessor implements EventProcessor{
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.execute(object -> {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute((objectNew) -> {
                        if (objectNew instanceof Light) {
                            Light light = (Light) objectNew;
                            if (event.getType() == LIGHT_ON) {
                                Action action = new TurnOnLight(event.getObjectId(), room.getName());
                                light.execute(action);
                            } else if (event.getType() == LIGHT_OFF) {
                                Action action = new TurnOffLight(event.getObjectId(), room.getName());
                                light.execute(action);
                            }
                        }
                    });
                }
            });
        }
    }
}
