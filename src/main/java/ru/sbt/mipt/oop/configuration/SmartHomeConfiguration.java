package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters.*;
import ru.sbt.mipt.oop.adapter.ProcessorAdapter;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SmartHomeConfiguration {
    @Bean
    public SensorEventsManager sensorEventsManager(EventProcessor eventProcessor, SmartHome smartHome, CCSensorEventConverter eventAdapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new ProcessorAdapter(eventProcessor, smartHome, eventAdapter));
        return sensorEventsManager;
    }

    @Bean
    public SmartHome smartHome(Alarm alarm) {
        SmartHome smartHome = new JsonSmartHomeReader("smart-home-1.js").loadSmartHome();
        smartHome.setAlarm(alarm);
        return smartHome;

    }
    @Bean
    public Alarm alarm() {
        return new Alarm();
    }

    @Bean
    @Primary
    public EventProcessor eventProcessor(Collection<EventProcessor> eventProcessors) {
        return new EventProcessorDecorator(new MainHomeProcessor(eventProcessors));
    }

    @Bean
    //@Qualifier("lightEventProcessor")
    public EventProcessor lightEventProcessor() {
        return new LightEventProcessor();
    }

    @Bean
    //@Qualifier("doorEventProcessor")
    public EventProcessor doorEventProcessor() {
        return new DoorEventProcessor();
    }

    @Bean
    //@Qualifier("hallEventProcessor")
    public EventProcessor hallEventProcessor() {
        return new HallDoorEventProcessor();
    }

    @Bean
    //@Qualifier("alarmEventProcessor")
    public EventProcessor alarmEventProcessor() {
        return new AlarmEventProcessor();
    }

    @Bean
    public CCSensorEventConverter eventAdapter(LightIsOffConverter lightIsOffAdapter) {
        return new LightIsOnConverter(lightIsOffAdapter);
    }

    @Bean
    public LightIsOffConverter lightIsOffAdapter(DoorIsOpenConverter doorIsOpenAdapter) {
        return new LightIsOffConverter(doorIsOpenAdapter);
    }

    @Bean
    public DoorIsOpenConverter doorIsOpenAdapter(DoorIsClosedConverter doorIsClosedAdapter) {
        return new DoorIsOpenConverter(doorIsClosedAdapter);
    }

    @Bean
    public DoorIsClosedConverter doorIsClosedAdapter(NullConverter nullAdapter){
        return new DoorIsClosedConverter(nullAdapter);
    }

    @Bean
    public NullConverter nullAdapter() {
        return new NullConverter();
    }
}


