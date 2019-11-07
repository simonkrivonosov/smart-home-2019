package ru.sbt.mipt.oop;

public class TurnOffLight implements Action {

    private  String lightId;
    private  String roomName;

    public TurnOffLight(String objectId) {
        this.lightId = objectId;
        this.roomName = "none";
    }


    public void inspectRoom(Room room) {
        if (lightId.equals("all")) {
            roomName = room.getName();
        } else {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    roomName = room.getName();
                }
            }
        }
    }
    @Override
    public void run(Object object) {
        if(object instanceof Light) {
            String curId = ((Light) object).getId();
            if (lightId.equals(curId) || lightId.equals("all")) {
                ((Light) object).setOn(false);
                System.out.println("Light " + curId + " in room " + roomName + " was turned off.");
            }
        } else if (object instanceof Room && roomName.equals("none")) {
            inspectRoom((Room) object);
        }
    }

}
