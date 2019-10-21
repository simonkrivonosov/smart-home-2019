package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;

public class CloseDoor implements Action {
    String door_id;
    String room_name;

    public CloseDoor(String object_id) {
        door_id = object_id;
        room_name = "none";
    }

    public String GetRoom() {return room_name;}

    public void InspectRoom (Room room) {
        if (door_id.equals("all")) {
            room_name = room.getName();
        } else {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(door_id)) {
                    room_name = room.getName();
                }
            }
        }
    }

    public void run(Object object) {
        if(object instanceof Door) {
            String object_id = ((Door) object).getId();
            if (door_id.equals(object_id) || door_id.equals("all")) {
                ((Door) object).setOpen(false);
                System.out.println("Door " + object_id + " in room " + room_name + " was closed.");

            }
        } else if (object instanceof Room && room_name.equals("none"))  {
            InspectRoom((Room) object);
        }
    }
}
