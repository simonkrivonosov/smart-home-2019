package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door implements HomeEntity{
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void perform(SensorEvent event) {
        if (this.id.equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                this.setOpen(true);
                System.out.println("Door " + this.getId() +  " was opened.");
            }
            if (event.getType() == DOOR_CLOSED){
                this.setOpen(false);
                System.out.println("Door " + this.getId() +  " was closed.");
            }
        }
    }
}
