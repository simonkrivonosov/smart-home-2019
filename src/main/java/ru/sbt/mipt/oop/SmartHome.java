package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;
    Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        //alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        //alarm = new Alarm();
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Alarm getAlarm() {return alarm;}


    public void execute(Action action) {
        action.run(this);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
