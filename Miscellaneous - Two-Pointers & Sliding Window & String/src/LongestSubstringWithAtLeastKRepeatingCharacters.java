public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        String s = "aaabb";
        System.out.println(longestSubstring(s, 3));
    }

    public static int longestSubstring(String s, int k) {
        int res = 0;
        for (int n = 1; n <= Math.min(26, s.length()); ++n) {
            // for n = 1 : Math.min(26, s.length()), we are going to use sliding window to find the
            // "longest window which contains exactly n different characters, and each different
            // character repeats at least k times
            int possibleUpdate = longestSubstringWithNUnique(s, k, n);
            // if possibleUpdate = 0, then we can no longer find any substring with n or more unique
            // characters, each repeating at least k times, so directly return the current result
            if (possibleUpdate == 0) {
                return res;
            }
            res = Math.max(res, possibleUpdate);
        }
        return res;
    }


    public static int longestSubstringWithNUnique(String s, int k, int n) {
        int[] map = new int[26];
        int countUnique = 0, countNoLessThanK = 0;
        int begin = 0, end = 0;
        // 右边界(end)每次outer iteration都要往右移动，来控制下一个字母的读取
        // 左边界(begin)只在我们当前unique characters的数量多于n时移动，一直向右移动到unique characters恢复到n个为止
        int res = 0;
        while (end < s.length()) {
            int index = s.charAt(end) - 'a';
            if (map[index] == 0) {
                // We have found a new unique character
                countUnique++;
            }
            map[index]++;
            if (map[index] == k) {
                // a new character has appeared at least K times
                countNoLessThanK++;
            }
            end++;
            // The condition where we need to move right border (begin)
            while (countUnique > n) {
                index = s.charAt(begin) - 'a';
                if (map[index] == k) {
                    // we have one less character that has appeared at least K times
                    countNoLessThanK--;
                }
                map[index]--;
                if (map[index] == 0) {
                    // we have one less unique character
                    countUnique--;
                }
                begin++;
            }
            // we will only update res when we have exactly n unique characters and each repeats k times
            if (countUnique == n && countUnique == countNoLessThanK) {
                res = Math.max(end - begin, res);
            }
        }
        return res;
    }
}
