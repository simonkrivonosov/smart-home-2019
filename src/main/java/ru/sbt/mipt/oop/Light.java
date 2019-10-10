package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light implements HomeEntity{
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void perform(SensorEvent event) {
        if (this.id.equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                this.setOn(true);
                System.out.println("Light " + this.getId() +  " was turned on.");
            }
            if (event.getType() == LIGHT_OFF){
                this.setOn(false);
                System.out.println("Light " + this.getId() +  " was turned off.");
            }
        }

    }
}
