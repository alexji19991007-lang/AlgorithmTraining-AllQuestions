import java.util.*;

// LeetCode 71
public class F067_SimplifyPath {
    // TC: O(n)
    // SC: O(n)
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        Set<String> skip = new HashSet<>(Arrays.asList(".", "..", ""));
        for (String str : path.split("/")) {
            if (str.equals("..") && !deque.isEmpty()) {
                deque.pollFirst();
            } else if (!skip.contains(str)) {
                deque.offerFirst(str);
            }
        }if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            res.append("/").append(deque.pollLast());
        }
        return res.toString();
    }
}
