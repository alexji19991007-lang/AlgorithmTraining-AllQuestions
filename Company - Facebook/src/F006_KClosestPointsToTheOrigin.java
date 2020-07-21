import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

// LeetCode 973
public class F006_KClosestPointsToTheOrigin {
    // Method 1: Max Heap
    // TC: klogk + (n-k)logk + klogk = O((n+k)logk)
    // SC: O(k)
    public int[][] kClosestMaxHeap(int[][] points, int K) {
        Queue<int[]> maxHeap = new PriorityQueue<>(K, (p1, p2) -> {
            long d1 = distance(p1);
            long d2 = distance(p2);
            if (d1 == d2) return 0;
            return d1 > d2 ? -1 : 1;
        });
        for (int i = 0; i < points.length; ++i) {
            int[] point = points[i];
            if (i < K) {
                maxHeap.offer(point);
            } else if (distance(maxHeap.peek()) > distance(point)) {
                maxHeap.poll();
                maxHeap.offer(point);
            }
        }
        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; --i) {
            int[] point = maxHeap.poll();
            res[i][0] = point[0];
            res[i][1] = point[1];
        }
        return res;
    }

    private long distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Method 2: Quick Select
    // TC: O(n) in average, O(n^2) in the worst case
    // SC: O(n)
    public int[][] kClosestQuickSelect(int[][] points, int K) {
        if (points.length <= K) {
            return points;
        }
        int low = 0;
        int high = points.length - 1;
        int[][] res = new int[K][2];
        while (low < high) {
            int j = partition(points, low, high);
            if (j < K) {
                low = j + 1;
            } else if (j > K) {
                high = j - 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < K; ++i) {
            res[i] = points[i];
        }
        return res;
    }

    public int partition(int[][] points, int low, int high) {
        Random rand = new Random();
        int pivot = low + rand.nextInt(high - low + 1);
        swap(points, pivot, high);
        int start = low, end = high - 1;
        while (start <= end) {
            if (distCloser(points[start], points[high])) {
                start++;
            } else {
                swap(points, start, end--);
            }
        }
        swap(points, start, high);
        return start;
    }

    public boolean distCloser(int[] point1, int[] point2) {
        return point1[0] * point1[0] + point1[1] * point1[1] < point2[0] * point2[0] + point2[1] * point2[1];
    }

    public void swap(int[][] points, int i, int j) {
        int temp0 = points[i][0];
        int temp1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = temp0;
        points[j][1] = temp1;
    }
}
