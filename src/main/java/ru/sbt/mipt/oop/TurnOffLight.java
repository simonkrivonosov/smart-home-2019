package ru.sbt.mipt.oop;

public class TurnOffLight implements Action {

    private  String lightId;
    private  String roomName;


    public TurnOffLight(String lightId, String roomName) {
        this.lightId = lightId;
        this.roomName = roomName;
    }

    @Override
    public void run(Object object) {
        String curId = ((Light) object).getId();
        if (lightId.equals(curId) || lightId.equals("all")) {
            ((Light) object).setOn(false);
            System.out.println("Light " + curId + " in room " + roomName + " was turned off.");
        }
    }
}
