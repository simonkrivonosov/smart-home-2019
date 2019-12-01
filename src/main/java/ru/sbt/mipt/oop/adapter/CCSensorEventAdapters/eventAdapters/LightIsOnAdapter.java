package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightIsOnAdapter implements CCSensorEventAdapter {
    private CCSensorEventAdapter nextEventAdapter;

    public LightIsOnAdapter(CCSensorEventAdapter nextEventAdapter) {
        this.nextEventAdapter = nextEventAdapter;
    }

    @Override
    public SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOn"))
            return new SensorEvent(LIGHT_ON, ccSensorEvent.getObjectId());
        return nextEventAdapter.adapt(ccSensorEvent);
    }
}
