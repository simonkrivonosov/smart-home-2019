package ru.sbt.mipt.oop;

public class TurnOnLight implements Action {
    private String lightId;
    private String roomName;

    public TurnOnLight(String lightId, String roomName) {
        this.lightId = lightId;
        this.roomName = roomName;
    }

    @Override
    public void run(Object object) {
        String curId = ((Light) object).getId();
        if (lightId.equals(curId) || lightId.equals("all")) {
            ((Light) object).setOn(true);
            System.out.println("Light " + curId + " in room " + roomName + " was turned on.");
        }
    }
}