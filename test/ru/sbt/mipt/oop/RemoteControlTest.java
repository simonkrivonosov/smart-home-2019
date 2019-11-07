package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.command.ActivateAlarmCommand;
import ru.sbt.mipt.oop.command.AlarmOnCommand;
import ru.sbt.mipt.oop.command.CloseHallDoorCommand;
import ru.sbt.mipt.oop.command.Command;


import java.io.IOException;

public class RemoteControlTest {
    @Test
    public void closeHallDoorTest() throws IOException {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smarthome = reader.loadSmartHome();
        SmartHomeRemoteControl controller = new SmartHomeRemoteControl("1234");
        Command testCommand = new CloseHallDoorCommand(smarthome);
        controller.setCommandToButton("A", testCommand);
        controller.onButtonPressed("A", "1234");
        for (Room room: smarthome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door: room.getDoors()) {
                    Assert.assertFalse(door.isOpen());
                }
            }
            for (Light light: room.getLights()) {
                Assert.assertFalse(light.isOn());
            }
        }
    }

    @Test
    public void noSuchCommandTest() throws IOException {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smarthome = reader.loadSmartHome();
        SmartHomeRemoteControl controller = new SmartHomeRemoteControl("1234");
        Command testCommand = new ActivateAlarmCommand(smarthome.getAlarm(), "0000");
        controller.setCommandToButton("B", testCommand);
        controller.onButtonPressed("1", "1234");
        Assert.assertFalse(smarthome.getAlarm().getState() instanceof ActiveState);
    }

    @Test
    public void turnOnAlarmTest() throws IOException {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smarthome = reader.loadSmartHome();
        SmartHomeRemoteControl controller = new SmartHomeRemoteControl("1234");
        Alarm testAlarm = new Alarm("1121");
        smarthome.setAlarm(testAlarm);
        Command testCommand = new AlarmOnCommand(smarthome.getAlarm());
        controller.setCommandToButton("B", testCommand);
        controller.onButtonPressed("B", "1234");
        Assert.assertTrue(smarthome.getAlarm().getState() instanceof AlarmState);
    }


}
