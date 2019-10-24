package ru.sbt.mipt.oop;

public class EventProcessorDecorator implements EventProcessor {

    private EventProcessor eventProcessor;

    public EventProcessorDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent (SmartHome smartHome, SensorEvent event) {
        if (smartHome.getAlarm().getState() instanceof ActiveState) {

            smartHome.getAlarm().alarm();
            System.out.println("sending sms");

        } else if (smartHome.getAlarm().getState() instanceof AlarmState) {
            System.out.println("sending sms");
        } else {
            eventProcessor.processEvent(smartHome, event);
        }

    }

}
