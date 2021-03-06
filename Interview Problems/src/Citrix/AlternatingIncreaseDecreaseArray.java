package Citrix;

import java.util.Arrays;

public class AlternatingIncreaseDecreaseArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateArray("DDDIII")));
    }

    public static int[] generateArray(String str) {
        int n = str.length();
        int[] res = new int[n + 1];
        int start = 1, end = n + 1;
        for (int i = 0; i < n; ++i) {
            if (str.charAt(i) == 'I') {
                res[i] = start++;
            } else {
                res[i] = end--;
            }
        }
        res[n] = start;
        return res;
    }
}
