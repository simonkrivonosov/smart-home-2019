package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;


public class AlarmEventTest {

    SmartHome GetTestHome() throws IOException {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        Alarm init_alarm = new Alarm("1234");
        SmartHome smarthome = reader.loadSmartHome();
        smarthome.set_alarm(init_alarm);
        return smarthome;
    }

    @Test
    public void SuccessfulAlarmDeactivate() throws IOException {

        SmartHome smarthome = GetTestHome();

        AlarmEventProcessor processor = new AlarmEventProcessor();

        SensorEvent test_activate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent test_deactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1234");

        processor.processEvent(smarthome, test_activate);
        Assert.assertTrue(smarthome.getAlarm().getState() instanceof ActiveState);

        processor.processEvent(smarthome, test_deactivate);
        Assert.assertTrue(smarthome.getAlarm().getState() instanceof DeActiveState);
    }

    @Test
    public void AlarmSituation() throws IOException {

        SmartHome smarthome = GetTestHome();
        EventProcessorDecorator processor = new EventProcessorDecorator(new AlarmEventProcessor());

        SensorEvent test_activate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent test_deactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1233");

        SensorEvent break_event =  new SensorEvent(DOOR_OPEN, "1");
        processor.processEvent(smarthome, test_activate);
        processor.processEvent(smarthome, test_deactivate);
        Assert.assertTrue(smarthome.getAlarm().getState() instanceof AlarmState);

        processor.processEvent(smarthome, break_event);
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