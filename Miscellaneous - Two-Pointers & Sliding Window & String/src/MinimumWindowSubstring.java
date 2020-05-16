import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        int minSize = Integer.MAX_VALUE;
        int[] res = {0, 0};
        int[] temp = {0, 0};
        int n = s.length();
        Map<Character, Integer> countTarget = new HashMap<>();
        initializeCountTarget(countTarget, t);
        int start = 0;
        for (int i = 0; i < n; ++i) {
            char x = s.charAt(i);
            if (countTarget.containsKey(x)) {
                countTarget.put(x, countTarget.get(x) - 1);
            }
            if (allFound(countTarget)) {
                while (start < i) {
                    char first = s.charAt(start);
                    if (countTarget.containsKey(first)) {
                        int countWithoutFirst = countTarget.get(first) + 1;
                        if (countWithoutFirst > 0) {
                            break;
                        } else {
                            countTarget.put(first, countWithoutFirst);
                        }
                    }
                    start++;
                }
                temp[0] = start;
                temp[1] = i + 1;
                int curSize = temp[1] - temp[0];
                if (curSize < minSize) {
                    minSize = curSize;
                    res[0] = temp[0];
                    res[1] = temp[1];
                }
            }
        }
        return s.substring(res[0], res[1]);
    }

    public static void initializeCountTarget(Map<Character, Integer> countTarget, String t) {
        for (int i = 0; i < t.length(); ++i) {
            char x = t.charAt(i);
            countTarget.put(x, countTarget.getOrDefault(x, 0) + 1);
        }
    }

    public static boolean allFound(Map<Character, Integer> countTarget) {
        for (char x : countTarget.keySet()) {
            if (countTarget.get(x) > 0) {
                return false;
            }
        }
        return true;
    }
}
