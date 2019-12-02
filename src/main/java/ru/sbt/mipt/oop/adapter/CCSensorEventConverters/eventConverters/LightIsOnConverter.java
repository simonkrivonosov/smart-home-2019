package ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightIsOnConverter implements CCSensorEventConverter {
    private CCSensorEventConverter nextEventConverter;

    public LightIsOnConverter(CCSensorEventConverter nextEventConverter) {
        this.nextEventConverter = nextEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOn"))
            return new SensorEvent(LIGHT_ON, ccSensorEvent.getObjectId());
        return nextEventConverter.convert(ccSensorEvent);
    }
}
