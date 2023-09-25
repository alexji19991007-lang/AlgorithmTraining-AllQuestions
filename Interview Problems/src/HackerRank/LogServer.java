package HackerRank;

import java.util.Deque;
import java.util.LinkedList;

public class LogServer {
    static class Log {
        String logId;
        int timeStamp;

        public Log(String logId, int timeStamp) {
            this.logId = logId;
            this.timeStamp = timeStamp;
        }
    }

    private final Deque<Log> oneHourLog;
    private final Deque<Log> latestLogs;
    private final int latestM;

    public LogServer(int m) {
        this.latestM = m;
        this.oneHourLog = new LinkedList<>();
        this.latestLogs = new LinkedList<>();
    }

    public void recordLog(String logId, int timeStamp) {
        if (!oneHourLog.isEmpty() && oneHourLog.peekFirst().timeStamp > timeStamp) {
            return;
        }
        Log newLog = new Log(logId, timeStamp);

        latestLogs.offerLast(newLog);
        while (latestLogs.size() > latestM) {
            latestLogs.pollFirst();
        }

        oneHourLog.offerLast(newLog);
        int timeStampOneHourAgo = timeStamp - 3600;
        while (!oneHourLog.isEmpty() && oneHourLog.peekFirst().timeStamp < timeStampOneHourAgo) {
            oneHourLog.pollFirst();
        }
    }

    public String getLogs() {
        StringBuilder sb = new StringBuilder();
        for (Log log : latestLogs) {
            sb.append(log.logId);
        }
        return sb.toString();
    }

    public int getLogCount() {
        return oneHourLog.size();
    }
}
