package HackerRank;

import java.util.HashSet;
import java.util.Set;

public class Inversions {
    public static void main(String[] args) {
        Inversions test = new Inversions();
        int[] arr = {3, 4, 3, 4, 2, 1};
        System.out.println(test.getInversionCount(arr, 6));
    }

    public int getInversionCount(int[] arr, int n) {
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            int smallerThanCurOnTheRight = 0;
            Set<Integer> visited = new HashSet<>();
            for (int j = i + 1; j < n; ++j) {
                if (visited.add(arr[j]) && arr[i] > arr[j]) {
                    smallerThanCurOnTheRight++;
                }
            }
            int largerThanCurOnTheLeft = 0;
            visited.clear();
            for (int j = i - 1; j >= 0; --j) {
                if (visited.add(arr[j]) && arr[i] < arr[j]) {
                    largerThanCurOnTheLeft++;
                }
            }
            res += smallerThanCurOnTheRight * largerThanCurOnTheLeft;
        }
        return res;
    }
}
