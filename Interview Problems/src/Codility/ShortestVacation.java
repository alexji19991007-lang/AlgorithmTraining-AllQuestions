package Codility;

import java.util.HashMap;
import java.util.Map;

public class ShortestVacation {
    public static void main(String[] args) {
        ShortestVacation test = new ShortestVacation();
        System.out.println(test.shortestVacation(new int[]{7, 3, 7, 3, 1, 3, 4, 1}));
        System.out.println(test.shortestVacation(new int[]{7, 5, 2, 7, 2, 7, 4, 7}));
    }

    public int shortestVacation(int[] A) {
        Map<Integer, Integer> locations = new HashMap<>();
        for (int location : A) {
            locations.put(location, 0);
        }
        int minSize = Integer.MAX_VALUE;
        int slow = 0;
        int numMatched = 0;
        for (int i = 0; i < A.length; ++i) {
            int rightLocation = A[i];
            if (locations.containsKey(rightLocation)) {
                int count = locations.get(rightLocation);
                if (count == 0) {
                    numMatched++;
                }
                locations.put(rightLocation, count + 1);
            }
            while (numMatched == locations.size()) {
                int leftLocation = A[slow];
                if (locations.containsKey(leftLocation)) {
                    int curCount = locations.get(leftLocation);
                    if (curCount == 1) {
                        numMatched--;
                        int curSize = i - slow + 1;
                        minSize = Math.min(minSize, curSize);
                    }
                    locations.put(leftLocation, curCount - 1);
                }
                slow++;
            }
        }
        return minSize;
    }
}
