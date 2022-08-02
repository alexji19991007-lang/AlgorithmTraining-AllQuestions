package Codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumEvenSumSubsequenceOfSizeK {
    public int evenSumK(int[] array, int K) {
        if (array.length < K) {
            return -1;
        }
        int curSum = 0;
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int i : array) {
            if (i % 2 == 0) {
                even.add(i);
            } else {
                odd.add(i);
            }
        }
        Collections.sort(even);
        Collections.sort(odd);
        int evenPtr = even.size() - 1;
        int oddPtr = odd.size() - 1;
        while (K > 0) {
            if (K % 2 == 1) {
                if (evenPtr >= 0) {
                    curSum += even.get(evenPtr--);
                } else {
                    return -1;
                }
                K--;
            } else if (evenPtr >= 1 && oddPtr >= 1) {
                if (even.get(evenPtr) + even.get(evenPtr - 1) <= odd.get(oddPtr) + odd.get(oddPtr - 1)) {
                    curSum += odd.get(oddPtr) + odd.get(oddPtr - 1);
                    oddPtr -= 2;
                } else {
                    curSum += even.get(evenPtr) + even.get(evenPtr - 1);
                    evenPtr -= 2;
                }
                K -= 2;
            } else if (evenPtr >= 1) {
                curSum += even.get(evenPtr) + even.get(evenPtr - 1);
                evenPtr -= 2;
                K -= 2;
            } else if (oddPtr >= 1) {
                curSum += odd.get(oddPtr) + odd.get(oddPtr - 1);
                oddPtr -= 2;
                K -= 2;
            } else {
                return -1;
            }
        }
        return curSum;
    }
}
