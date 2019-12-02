package ru.sbt.mipt.oop.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Alarm;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.command.*;

@ComponentScan
@Configuration
public class RemoteControlConfiguration {

    @Bean
    @Qualifier("myRemoteControl")
    RemoteControl remoteControlImplementation(Command turnOnAllLightCommand,
                                              Command turnOffAllLightCommand,
                                              Command activateAlarmCommand,
                                              Command turnOnHallLightCommand,
                                              Command setDangerModeCommand,
                                              Command closeHallDoorCommand,
                                              RemoteControlRegistry remoteControlRegistry) {
        SmartHomeRemoteControl remoteControl = new SmartHomeRemoteControl();
        remoteControl.setCommandToButton("A", turnOnAllLightCommand);
        remoteControl.setCommandToButton("B", turnOffAllLightCommand);
        remoteControl.setCommandToButton("C", activateAlarmCommand);
        remoteControl.setCommandToButton("D", turnOnHallLightCommand);
        remoteControl.setCommandToButton("1", setDangerModeCommand);
        remoteControl.setCommandToButton("2", closeHallDoorCommand);
        remoteControlRegistry.registerRemoteControl(remoteControl, "1");
        return remoteControl;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    Command turnOnAllLightCommand(SmartHome smartHome) {
        return new TurnOnAllLightCommand(smartHome);
    }
    @Bean
    Command turnOffAllLightCommand(SmartHome smartHome) {
        return new TurnOffAllLightCommand(smartHome);
    }

    @Bean
    Command activateAlarmCommand(Alarm alarm) {
        return new ActivateAlarmCommand(alarm, "123");
    }

    @Bean
    Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    Command setDangerModeCommand(Alarm alarm) {
        return new SetDangerModeCommand(alarm);
    }

    @Bean
    Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

}
