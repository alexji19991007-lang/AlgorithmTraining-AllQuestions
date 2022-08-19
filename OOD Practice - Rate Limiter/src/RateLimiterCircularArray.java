import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterCircularArray {
    public static void main(String[] args) {
        RateLimiterCircularArray test = new RateLimiterCircularArray(5, 500);
        System.out.println(test.sendRequest(0)); // T
        System.out.println(test.sendRequest(100)); // T
        System.out.println(test.sendRequest(200)); // T
        System.out.println(test.sendRequest(300)); // T
        System.out.println(test.sendRequest(499)); // T
        System.out.println(test.sendRequest(500)); // F
        System.out.println(test.sendRequest(501)); // F
        System.out.println(test.sendRequest(502)); // F
        System.out.println(test.sendRequest(503)); // F
        System.out.println(test.sendRequest(504)); // F
        System.out.println(test.sendRequest(601)); // T
        System.out.println(test.sendRequest(700)); // F
        System.out.println(test.sendRequest(701)); // T
        System.out.println(test.sendRequest(801)); // T
        System.out.println(test.sendRequest(1000)); // T
        System.out.println(test.sendRequest(1002)); // T

    }

    private int limit;
    private long timeWindow;
    private long[] circularArray;
    private int head;
    private int tail;
    private int curSize = 0;

    public RateLimiterCircularArray(int limit, long timeWindow) {
        if (limit == 0 || timeWindow == 0) {
            throw new IllegalArgumentException();
        }
        this.limit = limit;
        this.timeWindow = timeWindow;
        this.circularArray = new long[limit];
        this.head = 0;
        this.tail = 0;
        this.curSize = 0;
    }

    public boolean sendRequest(long timeStamp) {
        tail = tail == circularArray.length ? 0 : tail;
        if (curSize < limit) {
            circularArray[tail++] = timeStamp;
            curSize++;
            return true;
        }
        long earliestTime = circularArray[head];
        if (timeStamp - earliestTime <= timeWindow) {
            return false;
        }
        head = head == circularArray.length - 1 ? 0 : head + 1;
        circularArray[tail++] = timeStamp;
        return true;
    }
}
