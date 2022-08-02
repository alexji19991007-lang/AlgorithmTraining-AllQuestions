public class AngleOnClock {
    public static void main(String[] args) {
        AngleOnClock test = new AngleOnClock();
        System.out.println(test.calculateAngle(9, 30));
    }

    public int calculateAngle(int h, int m) {
        if (m == 60) {
            h++;
            m = 0;
        }
        if (h > 12) {
            h -= 12;
        }
        int hourAngle = (int) (0.5 * (h * 60 + m));
        int minuteAngle = 6 * m;
        int angle = Math.abs(hourAngle - minuteAngle);
        return Math.min(360 - angle, angle);
    }
}
