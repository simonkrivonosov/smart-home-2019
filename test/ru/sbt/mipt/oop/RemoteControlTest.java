package ru.sbt.mipt.oop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.command.SetDangerModeCommand;
import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.configuration.SmartHomeConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RemoteControlTest {
    @Test
    public void TestActivationAlarmState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlImplementation");

        remoteControl.onButtonPressed("C", "1");
        assertTrue(alarm.getAlarmState() instanceof ActiveAlarmState);
    }

    @Test
    public void TestLightOffAndOnWholeHouse() {
        boolean flag = true;
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlImplementation");
        remoteControl.onButtonPressed("A","1");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.isOn()) {
                    flag = false;
                    break;
                }
            }
        }
        assertTrue(flag);
        flag = true;
        remoteControl.onButtonPressed("B", "1");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.isOn()) {
                    flag = false;
                    break;
                }
            }
        }
        assertTrue(flag);
    }

    @Test
    public void TestHallDoorClosed() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlImplementation");
        remoteControl.onButtonPressed("2", "1");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Door door : room.getDoors()) {
                    if (door.isOpen()) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        assertFalse(flag);
    }

    @Test
    public void TestHallLightOn() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlImplementation");
        remoteControl.onButtonPressed("D", "1");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Light light : room.getLights()) {
                    if (!light.isOn()) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        assertFalse(flag);
    }

    @Test
    public void TestDangerAlarmState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlImplementation");

        remoteControl.onButtonPressed("1", "1");
        assertTrue(alarm.getAlarmState() instanceof DangerAlarmState);
    }

    @Test
    public void turnOnAlarmTest() {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smarthome = reader.loadSmartHome();
        SmartHomeRemoteControl controller = new SmartHomeRemoteControl();
        Alarm testAlarm = new Alarm();
        testAlarm.setAlarmCode("1234");
        smarthome.setAlarm(testAlarm);
        Command testCommand = new SetDangerModeCommand(smarthome.getAlarm());
        controller.setCommandToButton("B", testCommand);
        controller.onButtonPressed("B", "1234");
        assertTrue(smarthome.getAlarm().getAlarmState() instanceof DangerAlarmState);
    }


}
