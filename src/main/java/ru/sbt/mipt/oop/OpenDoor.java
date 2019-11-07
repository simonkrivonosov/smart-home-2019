package ru.sbt.mipt.oop;

public class OpenDoor implements Action {
    private String doorId;
    private String roomName;

    public OpenDoor(String objectId) {
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
                ((Door) object).setOpen(true);
                System.out.println("Door " + objectId + " in room " + roomName + " was opened.");

            }
        } else if (object instanceof Room && roomName.equals("none"))  {
            inspectRoom((Room) object);
        }
    }

}