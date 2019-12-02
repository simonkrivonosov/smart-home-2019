package ru.sbt.mipt.oop.adapter.CCSensorEventConverters.eventConverters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;

public class NullConverter implements CCSensorEventConverter {
    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        return null;
    }
}
