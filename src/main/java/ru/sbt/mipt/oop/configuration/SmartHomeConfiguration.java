package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters.*;
import ru.sbt.mipt.oop.adapter.ProcessorAdapter;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SmartHomeConfiguration {
    @Bean
    public SensorEventsManager sensorEventsManager(EventProcessor eventProcessor, SmartHome smartHome, CCSensorEventAdapter eventAdapter) {
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
    public EventProcessor eventProcessor(Collection<EventProcessor> eventProcessors) {
        return new EventProcessorDecorator(new MainHomeProcessor(eventProcessors));
    }

    @Bean
    public EventProcessor lightEventProcessor() {
        return new LightEventProcessor();
    }

    @Bean
    public EventProcessor doorEventProcessor() {
        return new DoorEventProcessor();
    }

    @Bean
    public EventProcessor hallEventProcessor() {
        return new HallDoorEventProcessor();
    }

    @Bean
    public EventProcessor alarmEventProcessor() {
        return new AlarmEventProcessor();
    }

    @Bean
    public CCSensorEventAdapter eventAdapter(LightIsOffAdapter lightIsOffAdapter) {
        return new LightIsOnAdapter(lightIsOffAdapter);
    }

    @Bean
    public LightIsOffAdapter lightIsOffAdapter(DoorIsOpenAdapter doorIsOpenAdapter) {
        return new LightIsOffAdapter(doorIsOpenAdapter);
    }

    @Bean
    public DoorIsOpenAdapter doorIsOpenAdapter(DoorIsClosedAdapter doorIsClosedAdapter) {
        return new DoorIsOpenAdapter(doorIsClosedAdapter);
    }

    @Bean
    public DoorIsClosedAdapter doorIsClosedAdapter(NullAdapter nullAdapter){
        return new DoorIsClosedAdapter(nullAdapter);
    }

    @Bean
    public NullAdapter nullAdapter() {
        return new NullAdapter();
    }
}


