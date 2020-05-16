import java.util.*;

public class LargestProductOfLength {
    public int largestProduct(String[] dict) {
        int n = dict.length;
        int[] bitMaskRecord = new int[n];
        for (int i = 0; i < n; ++i) {
            int bitMask = 0;
            for (char x : dict[i].toCharArray()) {
                int index = x - 'a';
                bitMask |= (1 << index);
            }
            bitMaskRecord[i] = bitMask;
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((bitMaskRecord[i] & bitMaskRecord[j]) == 0) {
                    res = Math.max(res, dict[i].length() * dict[j].length());
                }
            }
        }
        return res;
    }
    // Time Complexity: O(n^2 + L) --> L is the total length of all words together.
    // Space Complexity: O(n) --> To keep an arrays of record
}
