package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new JsonSmartHomeLoader();
    private static CommandSender commandSender = new ConcreteCommandSender();
    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader, CommandSender commandSender) {
        Application.smartHomeLoader = smartHomeLoader;
        Application.commandSender = commandSender;
    }


    public static void main(String... args) throws IOException {


        SmartHome smartHome = smartHomeLoader.loadSmartHome("smart-home-1.js");
        SmartHomeEventObserver.observe(smartHome);
    }
}

