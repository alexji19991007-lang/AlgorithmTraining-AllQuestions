import java.util.*;

// LeetCode 1606
public class FindTheBusiestServer {
    // TC: O(nlogn)
    // SC: O(n)
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        int max = 0;
        TreeSet<Integer> freeServers = new TreeSet<>();
        for (int i = 0; i < k; ++i) {
            freeServers.add(i);
        }
        Queue<int[]> busyServers = new PriorityQueue<>((s1, s2) -> {
            if (s1[0] == s2[0]) {
                return 0;
            }
            return s1[0] < s2[0] ? -1 : 1;
        });
        for (int i = 0; i < arrival.length; ++i) {
            int startTime = arrival[i];
            int endTime = startTime + load[i];
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= startTime) {
                int freedServer = busyServers.poll()[1];
                freeServers.add(freedServer);
            }
            if (freeServers.size() == 0) {
                continue;
            }
            Integer assignedServer = freeServers.ceiling(i % k);
            if (assignedServer == null) {
                assignedServer = freeServers.first();
            }
            count[assignedServer]++;
            max = Math.max(max, count[assignedServer]);
            freeServers.remove(assignedServer);
            busyServers.offer(new int[] {endTime, assignedServer});
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            if (count[i] == max) {
                res.add(i);
            }
        }
        return res;
    }
}
