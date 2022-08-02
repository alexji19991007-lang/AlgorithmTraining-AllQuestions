package Codility;

import java.util.HashMap;
import java.util.Map;

public class FreeStoreroomShelves {
    public static void main(String[] args) {
        FreeStoreroomShelves test = new FreeStoreroomShelves();
        int[] A = {2, 3, 1, 1, 2};
        System.out.println(test.freeShelves(A, 3));
    }

    public int freeShelves(int[] A, int R) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : A) {
            count.put(a, count.getOrDefault(a, 0) + 1);
        }
        int totalTypes = count.size();
        int maxRemaining = 0;
        for (int i = 0; i < A.length; ++i) {
            if (i >= R) {
                maxRemaining = Math.max(maxRemaining, totalTypes);
                int leftCount = count.get(A[i - R]);
                if (leftCount == 0) {
                    totalTypes++;
                }
                count.put(A[i - R], leftCount + 1);
            }
            int rightCount = count.get(A[i]);
            if (rightCount == 1) {
                totalTypes--;
            }
            count.put(A[i], rightCount - 1);
        }
        return Math.max(maxRemaining, totalTypes);
    }
}
