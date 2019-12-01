package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class DoorIsClosedAdapter implements CCSensorEventAdapter {
    private CCSensorEventAdapter nextEventAdapter;

    public DoorIsClosedAdapter(CCSensorEventAdapter nextEventAdapter){
        this.nextEventAdapter = nextEventAdapter;
    }

    @Override
    public SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("DoorIsClosed"))
            return new SensorEvent(DOOR_CLOSED, ccSensorEvent.getObjectId());
        return nextEventAdapter.adapt(ccSensorEvent);
    }
}
