import java.util.Stack;

public class RemoveAllAdjacentDuplicates2 {
    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        System.out.println(removeDuplicates(s, 3));
    }

    public static String removeDuplicates(String s, int k) {
        int i = 0, j = 0;
        char[] res = s.toCharArray();
        while (j < s.length()) {
            res[i] = res[j];
            if (i >= k - 1 && duplicateExists(res, i, k)) {
                i -= k;
            }
            i++;
            j++;
        }
        return new String(res, 0, i);
    }

    public static boolean duplicateExists(char[] res, int end, int k) {
        for (int i = end; i > end - k; --i) {
            if (res[i] != res[end]) {
                return false;
            }
        }
        return true;
    }

    public static String removeDuplicatesByStack(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            // if it is the first character or the current character does not match the last
            // character, we can directly push the count(which is 1) onto the stack
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                // If the current character matches the last character, pop the count and update it
                int incremented = counts.pop() + 1;
                // if the count has reached the threshold, we should delete them
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    // since we have made the string shorter, we should go back.
                    i = i - k;
                    // i - k + 1 actually, but the +1 is done by the for loop.
                    // note that the string builder's length is also changed now.
                } else {
                    // if the count is still fine, just push the updated count onto the stack.
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }
}
