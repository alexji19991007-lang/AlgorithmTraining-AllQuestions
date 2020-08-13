package Robinhood;

import java.util.HashMap;
import java.util.Map;

class DegreeOfArray {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 2, 1, 2, 3, 2};
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6};
        int[] arr3 = new int[]{};
        System.out.println(minLen(arr1));
        System.out.println(minLen(arr2));
        System.out.println(minLen(arr3));
    }

    public static int minLen(int[] arr) {
        Map<Integer, int[]> counter = new HashMap<>();
        int degree = 0;
        int minLen = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
			// info[0] = count, info[1] = startingPos, info[2] = endingPos
			int[] info = counter.getOrDefault(num, new int[3]);
            if (!counter.containsKey(num)) {
				info[0] = 1;
                info[1] = i;
			} else {
				info[0]++;
			}
			info[2] = i;
			degree = Math.max(degree, info[0]);
			counter.put(num, info);
		}

        for (int num : arr) {
            int[] info = counter.get(num);
            if (info[0] == degree) {
                minLen = Math.min(minLen, info[2] - info[1] + 1);
            }
        }
        return minLen;
    }
}