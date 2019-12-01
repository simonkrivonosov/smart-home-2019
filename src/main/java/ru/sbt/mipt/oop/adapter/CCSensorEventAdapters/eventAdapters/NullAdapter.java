package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventAdapters.CCSensorEventAdapter;

public class NullAdapter implements CCSensorEventAdapter {
    @Override
    public SensorEvent adapt(CCSensorEvent ccSensorEvent) {
        return null;
    }
}
