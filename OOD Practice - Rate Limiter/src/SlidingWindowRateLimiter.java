import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter {
    private volatile Map<String, Queue<Long>> map;
    private final int limit;
    private final long timeWindow;

    public SlidingWindowRateLimiter(int limit, long timeWindow) {
        if (limit == 0 || timeWindow == 0 ) {
            throw new IllegalArgumentException();
        }
        this.map = new ConcurrentHashMap<>();
        this.limit = limit;
        this.timeWindow = timeWindow;
    }

    public synchronized boolean request(String id, long time) {
        Queue<Long> list = map.computeIfAbsent(id, k -> new ArrayDeque<>());
        if (list.size() < limit) {
            list.offer(time);
            return true;
        }
        long earliestTime = list.peek();
        if (time - earliestTime <= timeWindow) {
            return false;
        }
        list.poll();
        list.offer(time);
        return true;
    }
}
