import java.util.ArrayDeque;
import java.util.Queue;

public class RateLimiter {
    public static final int TIME_FRAME = 1000000000;
    public static final int MAX_CALL = 10;
    Queue<Integer> pastRequests;

    public RateLimiter() {
        this.pastRequests = new ArrayDeque<>();
    }


    public boolean request(int time) {
        while (!pastRequests.isEmpty() && time - pastRequests.peek() >= TIME_FRAME) {
            pastRequests.poll();
        }
        if (pastRequests.size() < MAX_CALL) {
            pastRequests.add(time);
            return true;
        }
        return false;
    }
}
