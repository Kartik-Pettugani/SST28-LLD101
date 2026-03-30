package elevator;

public final class BuzzerAlarm implements Alarm {
    @Override
    public void play(String reason) {
        System.out.println("ALARM: " + reason);
    }
}
