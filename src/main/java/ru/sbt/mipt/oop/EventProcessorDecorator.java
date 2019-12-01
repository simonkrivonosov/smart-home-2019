package ru.sbt.mipt.oop;

public class EventProcessorDecorator implements EventProcessor {

    private EventProcessor eventProcessor;

    public EventProcessorDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent (SmartHome smartHome, SensorEvent event) {
        if (smartHome.getAlarm().getAlarmState() instanceof ActiveAlarmState) {

            smartHome.getAlarm().setDangerMode();
            System.out.println("sending sms");

        } else if (smartHome.getAlarm().getAlarmState() instanceof DangerAlarmState) {
            System.out.println("sending sms");
        } else {
            eventProcessor.processEvent(smartHome, event);
        }

    }

}
