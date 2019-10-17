package ru.sbt.mipt.oop;

public class SmartHomeEventObserver {
    public static void observe(SmartHome smartHome){
        SensorEventProvider randomSensorEventProvider = new RandomSensorEventProvider();
        SensorEvent event = randomSensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            smartHome.perform(event);
            //smartHome.eventPerformer.perform();
            event = randomSensorEventProvider.getNextSensorEvent();
        }
    }
}
