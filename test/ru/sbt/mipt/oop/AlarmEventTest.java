package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;


public class AlarmEventTest {

    SmartHome getTestHome() throws IOException {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        Alarm initAlarm = new Alarm();
        initAlarm.activate("1234");
        SmartHome smarthome = reader.loadSmartHome();
        smarthome.setAlarm(initAlarm);
        return smarthome;
    }

    @Test
    public void successfulAlarmDeactivate() throws IOException {

        SmartHome smarthome = getTestHome();

        AlarmEventProcessor processor = new AlarmEventProcessor();

        SensorEvent testActivate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent testDeactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1234");

        processor.processEvent(smarthome, testActivate);
        Assert.assertTrue(smarthome.getAlarm().getAlarmState() instanceof ActiveAlarmState);

        processor.processEvent(smarthome, testDeactivate);
        Assert.assertTrue(smarthome.getAlarm().getAlarmState() instanceof DeActiveAlarmState);
    }

    @Test
    public void alarmSituation() throws IOException {

        SmartHome smarthome = getTestHome();
        EventProcessorDecorator processor = new EventProcessorDecorator(new AlarmEventProcessor());

        SensorEvent testActivate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent testDeactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1233");

        SensorEvent breakEvent =  new SensorEvent(DOOR_OPEN, "1");
        processor.processEvent(smarthome, testActivate);
        processor.processEvent(smarthome, testDeactivate);
        Assert.assertTrue(smarthome.getAlarm().getAlarmState() instanceof DangerAlarmState);

        processor.processEvent(smarthome, breakEvent);
        for (Room room: smarthome.getRooms()) {
            if (room.getName().equals("kitchen")) {
                for (Door door: room.getDoors()) {
                    if (door.getId().equals("1")) {
                        Assert.assertFalse(door.isOpen());
                    }
                }
            }
        }
    }

}