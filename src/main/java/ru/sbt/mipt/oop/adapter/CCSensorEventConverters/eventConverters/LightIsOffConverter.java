package ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightIsOffConverter implements CCSensorEventConverter {
    private CCSensorEventConverter nextEventConverter;

    public LightIsOffConverter(CCSensorEventConverter nextEventConverter){
        this.nextEventConverter = nextEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOff"))
            return new SensorEvent(LIGHT_OFF, ccSensorEvent.getObjectId());
        return nextEventConverter.convert(ccSensorEvent);
    }
}
