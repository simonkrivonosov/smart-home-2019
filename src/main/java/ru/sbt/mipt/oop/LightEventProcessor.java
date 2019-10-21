package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.TurnOffLight;
import ru.sbt.mipt.oop.TurnOnLight;

public class LightEventProcessor implements EventProcessor{
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(event.getType() == LIGHT_ON) {
            Action action = new TurnOnLight(event.getObjectId());
            smartHome.execute(action);
        } else if (event.getType() == LIGHT_OFF) {
            Action action = new TurnOffLight(event.getObjectId());
            smartHome.execute(action);
        }
    }
}
