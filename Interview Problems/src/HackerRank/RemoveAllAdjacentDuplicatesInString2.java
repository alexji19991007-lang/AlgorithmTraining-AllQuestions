package HackerRank;

import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 1209
public class RemoveAllAdjacentDuplicatesInString2 {
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Deque<Integer> countStack = new ArrayDeque<>();
        char[] array = s.toCharArray();
        int i = 0, j = 0;
        while (i < s.length()) {
            array[j] = array[i];
            if (j == 0 || array[j] != array[j - 1]) {
                countStack.offerFirst(1);
            } else {
                int curCount = countStack.pollFirst() + 1;
                if (curCount == k) {
                    j -= k;
                } else {
                    countStack.offerFirst(curCount);
                }
            }
            i++;
            j++;
        }
        return new String(array, 0, j);
    }
}
