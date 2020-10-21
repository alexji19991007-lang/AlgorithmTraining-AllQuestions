public class Test {
    public static void main(String[] args) throws InterruptedException {
//        Clock x = new Clock();
//        x.display();
        String[] alarms = {"0:0", "0:1"};
        AlarmClock y = new AlarmClock(alarms);
        y.display();
    }
}
