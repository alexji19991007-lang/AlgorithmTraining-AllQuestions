package Codility;

import java.util.ArrayList;
import java.util.List;

public class MaximumNeighboringPairsInACircle {
    public int maximumPairs(int[] A) {
        List<Integer> newArray = new ArrayList<>();
        for (int a : A) {
            newArray.add(a);
        }
        newArray.add(A[0]);
        int notTakeFirstAndLast = countPairs(0, newArray.size() - 2, newArray);
        int takeFirstAndLast = countPairs(1, newArray.size() - 1, newArray);
        return Math.max(notTakeFirstAndLast, takeFirstAndLast);
    }

    public int countPairs(int left, int right, List<Integer> array) {
        int count = 0;
        while (left < right) {
            if ((array.get(left) + array.get(left + 1)) % 2 == 0) {
                count++;
                left++;
            }
            left++;
        }
        return count;
    }
}
