package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SmartHomeLoader {
    SmartHome loadSmartHome(Object source) throws IOException;
}
