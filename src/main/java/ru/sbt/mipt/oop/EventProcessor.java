package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent sensorEvent);
}
// массив всех  eventов для всех типов он должен хранить и обработать