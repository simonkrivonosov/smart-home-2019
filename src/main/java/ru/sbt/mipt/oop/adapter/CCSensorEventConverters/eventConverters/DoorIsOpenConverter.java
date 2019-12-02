package ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorIsOpenConverter implements CCSensorEventConverter {
    private CCSensorEventConverter nextEventConverter;

    public DoorIsOpenConverter(CCSensorEventConverter nextEventConverter){
        this.nextEventConverter = nextEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("DoorIsOpen"))
            return new SensorEvent(DOOR_OPEN, ccSensorEvent.getObjectId());
        return nextEventConverter.convert(ccSensorEvent);
    }
}
