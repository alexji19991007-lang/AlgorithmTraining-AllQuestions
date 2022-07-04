import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter {
    private Map<String, Integer> timeLimit;

    public LoggerRateLimiter() {
        this.timeLimit = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!timeLimit.containsKey(message)) {
            timeLimit.put(message, timestamp + 10);
            return true;
        }
        int nextTime = timeLimit.get(message);
        if (nextTime <= timestamp) {
            timeLimit.put(message, timestamp + 10);
            return true;
        }
        return false;
    }
}
