package ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class DoorIsClosedConverter implements CCSensorEventConverter {
    private CCSensorEventConverter nextEventConverter;

    public DoorIsClosedConverter(CCSensorEventConverter nextEventConverter){
        this.nextEventConverter = nextEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("DoorIsClosed"))
            return new SensorEvent(DOOR_CLOSED, ccSensorEvent.getObjectId());
        return nextEventConverter.convert(ccSensorEvent);
    }
}
