package ru.sbt.mipt.oop;

public class OpenDoor implements Action {
    private String doorId;
    private String roomName;

    public OpenDoor(String doorId, String roomName) {
        this.doorId = doorId;
        this.roomName = roomName;
    }

    @Override
    public void run(Object object) {
        String objectId = ((Door) object).getId();
        if (doorId.equals(objectId) || doorId.equals("all")) {
            ((Door) object).setOpen(true);
            System.out.println("Door " + objectId + " in room " + roomName + " was opened.");
        }
    }
}