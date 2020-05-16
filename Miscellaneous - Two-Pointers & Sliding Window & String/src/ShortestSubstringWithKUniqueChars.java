import java.util.HashMap;
import java.util.Map;

public class ShortestSubstringWithKUniqueChars {
    public static void main(String[] args) {
        String s = "aabbbccc";
        System.out.println(shortest(s, 4));
    }

    public static String shortest(String input, int k) {
        // Write your solution here
        if (input == null || input.length() == 0 || k == 0) {
            return "";
        }
        boolean hasKUnique = false;
        int minSize = Integer.MAX_VALUE;
        int start = 0, minStart = 0;
        int n = input.length();
        char[] array = input.toCharArray();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char x = input.charAt(i);
            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.size() == k) {
                hasKUnique = true;
                int newCount = count.get(input.charAt(start)) - 1;
                while (newCount > 0) {
                    count.put(input.charAt(start), newCount);
                    start++;
                    newCount = count.get(input.charAt(start)) - 1;
                }
                int curSize = i - start + 1;
                if (curSize < minSize) {
                    minSize = curSize;
                    minStart = start;
                }
                count.remove(input.charAt(start));
                start++;
            }
        }
        return hasKUnique ? new String(array, minStart, minSize) : "";
    }
}
