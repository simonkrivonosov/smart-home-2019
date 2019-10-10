package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeLoader implements SmartHomeLoader{
    @Override
    public SmartHome loadSmartHome(Object source) throws IOException {
        // считываем состояние дома из файла

        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(source.toString())));
        return gson.fromJson(json, SmartHome.class);
    }
}
