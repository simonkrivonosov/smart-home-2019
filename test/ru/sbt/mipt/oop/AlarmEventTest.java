package ru.sbt.mipt.oop;

import static junit.framework.Assert.assertTrue;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AlarmEventTest {
    private SmartHome smartHome;

    @Before
    public void setUp() {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        Alarm initAlarm = new Alarm();
        this.smartHome = reader.loadSmartHome();
        this.smartHome.setAlarm(initAlarm);
    }

    @Test
    public void testSuccessfulDeactivation() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate("123");
        alarm.deactivate("123");
        assertTrue(alarm.getAlarmState() instanceof DeActiveAlarmState);
    }
    @Test
    public void testBadDeactivation() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate("123");
        alarm.deactivate("000");
        assertTrue(alarm.getAlarmState() instanceof DangerAlarmState);
    }
    @Test
    public void testDangerAlarmActivationFromActivated() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate("123");
        alarm.setDangerMode();
        assertTrue(alarm.getAlarmState() instanceof DangerAlarmState);
    }
    @Test
    public void testDangerAlarmSuccessfulDeactivation() {
        Alarm alarm = this.smartHome.getAlarm();
        alarm.activate("123");
        alarm.setDangerMode();
        alarm.deactivate("123");
        assertTrue(alarm.getAlarmState() instanceof DeActiveAlarmState);
    }

    @Test
    public void successfulAlarmDeactivate() {

        AlarmEventProcessor processor = new AlarmEventProcessor();

        SensorEvent testActivate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent testDeactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1234");

        processor.processEvent(this.smartHome, testActivate);
        Assert.assertTrue(this.smartHome.getAlarm().getAlarmState() instanceof ActiveAlarmState);

        processor.processEvent(this.smartHome, testDeactivate);
        Assert.assertTrue(this.smartHome.getAlarm().getAlarmState() instanceof DeActiveAlarmState);
    }

    @Test
    public void alarmSituation() {
        EventProcessorDecorator processor = new EventProcessorDecorator(new AlarmEventProcessor());

        SensorEvent testActivate = new SensorAlarmEvent(ALARM_ACTIVATE, "1234");
        SensorEvent testDeactivate = new SensorAlarmEvent(ALARM_DEACTIVATE, "1233");

        SensorEvent breakEvent =  new SensorEvent(DOOR_OPEN, "1");
        processor.processEvent(this.smartHome, testActivate);
        processor.processEvent(this.smartHome, testDeactivate);
        Assert.assertTrue(this.smartHome.getAlarm().getAlarmState() instanceof DangerAlarmState);

        processor.processEvent(this.smartHome, breakEvent);
        for (Room room: this.smartHome.getRooms()) {
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