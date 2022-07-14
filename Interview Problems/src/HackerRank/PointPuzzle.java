package HackerRank;

import java.util.HashMap;
import java.util.Map;

// LeetCode 740 - Delete And Earn
public class PointPuzzle {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        Map<Integer, Integer> occurrence = new HashMap<>();
        for (int i : nums) {
            max = Math.max(max, i);
            occurrence.put(i, occurrence.getOrDefault(i, 0) + 1);
        }
        int twoCaseBefore = 0, oneCaseBefore = occurrence.getOrDefault(1, 0);
        for (int i = 2; i <= max; ++i) {
            int temp = oneCaseBefore;
            oneCaseBefore = Math.max(oneCaseBefore, occurrence.getOrDefault(i, 0) * i + twoCaseBefore);
            twoCaseBefore = temp;
        }
        return oneCaseBefore;
    }
}
