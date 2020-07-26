import java.util.Arrays;

// LeetCode 621
public class F041_TaskScheduler {
    // TC: O(N) where N is the total number of types of tasks
    // SC: O(26) = O(1)
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for (char t : tasks) {
            frequencies[t - 'A']++;
        }
        Arrays.sort(frequencies);
        int maxFreq = frequencies[25];
        // maximum possible number of idle slots
        int idleTime = (maxFreq - 1) * n;
        for (int i = 24; i >= 0; --i) {
            if (idleTime <= 0) {
                break;
            }
            idleTime -= Math.min(frequencies[i], maxFreq - 1);
        }
        idleTime = Math.max(0, idleTime);
        return idleTime + tasks.length;
    }
}
