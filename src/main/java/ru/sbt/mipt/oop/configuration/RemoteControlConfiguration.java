package ru.sbt.mipt.oop.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Alarm;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.command.*;

@Configuration
public class RemoteControlConfiguration {

    @Bean
    @Qualifier("remoteControlImplementation1")
    RemoteControl remoteControlImplementation(@Qualifier("turnOnAllLightCommand") Command turnOnAllLightCommand,
                                              @Qualifier("turnOffAllLightCommand") Command turnOffAllLightCommand,
                                              @Qualifier("activateAlarmCommand") Command activateAlarmCommand,
                                              @Qualifier("turnOnHallLightCommand") Command turnOnHallLightCommand,
                                              @Qualifier("setDangerModeCommand") Command setDangerModeCommand,
                                              @Qualifier("closeHallDoorCommand") Command closeHallDoorCommand,
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
    //@Qualifier("turnOnAllLightCommand")
    Command turnOnAllLightCommand(SmartHome smartHome) {
        return new TurnOnAllLightCommand(smartHome);
    }

    @Bean
    //@Qualifier("turnOffAllLightCommand")
    Command turnOffAllLightCommand(SmartHome smartHome) {
        return new TurnOffAllLightCommand(smartHome);
    }

    @Bean
    //@Qualifier("activateAlarmCommand")
    Command activateAlarmCommand(Alarm alarm) {
        return new ActivateAlarmCommand(alarm, "123");
    }

    @Bean
    //@Qualifier("turnOnHallLightCommand")
    Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    //@Qualifier("setDangerModeCommand")
    Command setDangerModeCommand(Alarm alarm) {
        return new SetDangerModeCommand(alarm);
    }

    @Bean
    //@Qualifier("closeHallDoorCommand")
    Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

}
