package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader {
    private  String path;

    public JsonSmartHomeReader(String path) {
        this.path = path;
    }

    @Override
    public SmartHome loadSmartHome() {
        // считываем состояние дома из файла
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
