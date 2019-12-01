package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorIsOpenAdapter implements CCSensorEventAdapter {
    private CCSensorEventAdapter nextEventAdapter;

    public DoorIsOpenAdapter(CCSensorEventAdapter nextEventAdapter){
        this.nextEventAdapter = nextEventAdapter;
    }

    @Override
    public SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("DoorIsOpen"))
            return new SensorEvent(DOOR_OPEN, ccSensorEvent.getObjectId());
        return nextEventAdapter.adapt(ccSensorEvent);
    }
}
