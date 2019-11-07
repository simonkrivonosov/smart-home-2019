package ru.sbt.mipt.oop;

public class CloseDoor implements Action {
    private String doorId;
    private String roomName;

    public CloseDoor(String objectId) {
        this.doorId = objectId;
        this.roomName = "none";
    }


    public void inspectRoom(Room room) {
        if (doorId.equals("all")) {
            roomName = room.getName();
        } else {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    roomName = room.getName();
                }
            }
        }
    }
    @Override
    public void run(Object object) {
        if(object instanceof Door) {
            String objectId = ((Door) object).getId();
            if (doorId.equals(objectId) || doorId.equals("all")) {
                ((Door) object).setOpen(false);
                System.out.println("Door " + objectId + " in room " + roomName + " was closed.");

            }
        } else if (object instanceof Room && roomName.equals("none"))  {
            inspectRoom((Room) object);
        }
    }
}
