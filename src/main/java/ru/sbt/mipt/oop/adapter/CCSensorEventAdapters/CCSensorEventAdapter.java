package ru.sbt.mipt.oop.adapter.CCSensorEventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public interface CCSensorEventAdapter {
    SensorEvent adapt(CCSensorEvent ccSensorEvent);
}
