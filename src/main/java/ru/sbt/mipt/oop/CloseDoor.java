package ru.sbt.mipt.oop;

public class CloseDoor implements Action {
    private String doorId;
    private String roomName;

    public CloseDoor(String doorId, String roomName) {
        this.doorId = doorId;
        this.roomName = roomName;
    }

    @Override
    public void run(Object object) {
        String objectId = ((Door) object).getId();
        if (doorId.equals(objectId) || doorId.equals("all")) {
            ((Door) object).setOpen(false);
            System.out.println("Door " + objectId + " in room " + roomName + " was closed.");

        }
    }
}
