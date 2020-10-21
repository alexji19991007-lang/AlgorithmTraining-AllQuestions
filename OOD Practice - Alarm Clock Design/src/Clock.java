import java.util.concurrent.TimeUnit;

public class Clock {
    protected Indicator hourIndicator;
    protected Indicator minuteIndicator;
    protected Indicator secondIndicator;

    public Clock(int hour, int minute, int second) {
        this.hourIndicator = new Indicator(0, 11, hour);
        this.minuteIndicator = new Indicator(0, 59, minute);
        this.secondIndicator = new Indicator(0, 59, second);
    }

    public Clock() {
        this.hourIndicator = new Indicator(0,11, 0);
        this.minuteIndicator = new Indicator(0, 59, 0);
        this.secondIndicator = new Indicator(0, 59, 0);
    }

    public void setIndicators(int newHour, int newMinute, int newSecond) {
        hourIndicator.setValue(newHour);
        minuteIndicator.setValue(newMinute);
        secondIndicator.setValue(newSecond);
    }

    protected void advanceOneSecond() {
        secondIndicator.advance();
        if (secondIndicator.getValue() == 0) {
            minuteIndicator.advance();
            if (minuteIndicator.getValue() == 0) {
                hourIndicator.advance();
            }
        }
    }

    public void display() throws InterruptedException {
        while (true) {
            System.out.println("Time: " + hourIndicator.getValue() + ":" + minuteIndicator.getValue() + ":" + secondIndicator.getValue());
            advanceOneSecond();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
