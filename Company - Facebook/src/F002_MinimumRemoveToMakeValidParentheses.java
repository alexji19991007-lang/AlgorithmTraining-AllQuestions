import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// LeetCode 1249
public class F002_MinimumRemoveToMakeValidParentheses {
    // TC: O(n)
    // SC: O(n)
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexRemove = new HashSet<>();
        Deque<Integer> indexLeftBracket = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                indexLeftBracket.offerFirst(i);
            } else if (s.charAt(i) == ')') {
                if (!indexLeftBracket.isEmpty()) {
                    indexLeftBracket.pollFirst();
                } else {
                    indexRemove.add(i);
                }
            }
        }
        while (!indexLeftBracket.isEmpty()) {
            indexRemove.add(indexLeftBracket.pollFirst());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!indexRemove.contains(i)) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
