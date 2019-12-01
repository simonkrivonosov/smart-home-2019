package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightIsOffAdapter implements CCSensorEventAdapter {
    private CCSensorEventAdapter nextEventAdapter;

    public LightIsOffAdapter(CCSensorEventAdapter nextEventAdapter){
        this.nextEventAdapter = nextEventAdapter;
    }

    @Override
    public SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOff"))
            return new SensorEvent(LIGHT_OFF, ccSensorEvent.getObjectId());
        return nextEventAdapter.adapt(ccSensorEvent);
    }
}
