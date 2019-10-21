package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

public class TurnOnLight implements Action {
    String light_id;
    String room_name;

    public TurnOnLight(String object_id) {
        light_id = object_id;
        room_name = "none";
    }

    public String GetRoom() {return room_name;}

    public void InspectRoom (Room room) {
        if (light_id.equals("all")) {
            room_name = room.getName();
        } else {
            for (Light light : room.getLights()) {
                if (light.getId().equals(light_id)) {
                    room_name = room.getName();
                }
            }
        }
    }

    public void run(Object object) {
        if(object instanceof Light) {
            String cur_id = ((Light) object).getId();
            if (light_id.equals(cur_id) || light_id.equals("all")) {
                ((Light) object).setOn(true);
                System.out
                        .println("Light " + cur_id + " in room " + room_name + " was turned on.");
            }
        } else if (object instanceof Room && room_name.equals("none")) {
            InspectRoom((Room) object);
        }
    }

}