import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class AlarmClock extends Clock {
    private Queue<AlarmTime> alarms;
    private final int duration;

    public AlarmClock(String[] alarms) {
        super();
        this.alarms = new LinkedList<>();
        for (String alarm : alarms) {
            String[] params = alarm.split(":");
            this.alarms.offer(new AlarmTime(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
        }
        this.duration = 10;
    }

    public AlarmClock(int hour, int minute, int second, String[] alarms) {
        super(hour, minute, second);
        this.alarms = new LinkedList<>();
        for (String alarm : alarms) {
            String[] params = alarm.split(":");
            this.alarms.offer(new AlarmTime(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
        }
        this.duration = 10;
    }

    private boolean shouldAlarm() {
        return !alarms.isEmpty() && alarms.peek().alarmHour  == hourIndicator.getValue() && alarms.peek().alarmMinute == minuteIndicator.getValue();
    }

    @Override
    public void display() throws InterruptedException {
        int i = 0;
        while (true) {
            if (shouldAlarm() && i < duration) {
                System.out.println("Alarm!!!");
                i++;
                if (i == duration) {
                    this.alarms.poll();
                }
            } else {
                if (secondIndicator.getValue() == 59) {
                    i = 0;
                }
                System.out.println("Time: " + hourIndicator.getValue() + ":" + minuteIndicator.getValue() + ":" + secondIndicator.getValue());
            }
            advanceOneSecond();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static class AlarmTime {
        int alarmHour;
        int alarmMinute;

        public AlarmTime(int alarmHour, int alarmMinute) {
            this.alarmHour = alarmHour;
            this.alarmMinute = alarmMinute;
        }
    }
}
