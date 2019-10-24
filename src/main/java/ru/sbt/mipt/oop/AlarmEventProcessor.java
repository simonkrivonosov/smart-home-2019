package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(event.getType().equals(ALARM_ACTIVATE)) {
            smartHome.getAlarm().getState().Activate(((SensorAlarmEvent) event).getCode());
        }

        if (event.getType().equals(ALARM_DEACTIVATE)) {
            smartHome.getAlarm().getState().Deactivate(((SensorAlarmEvent) event).getCode());
        }
    }

}
