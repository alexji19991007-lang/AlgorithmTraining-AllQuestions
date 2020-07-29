// LeetCode 767
public class F057_ReorganizeString {
    // TC: O(n) where n is the length of the string
    // SC: O(A) where A is the length of the alphabet, actually O(1)
    public String reorganizeString(String S) {
        int n = S.length();
        int[] counts = new int[26];
        int maxCount = 0;
        char maxChar = ' ';
        for (char c : S.toCharArray()) {
            counts[c - 'a'] += 1;
            if (maxCount < counts[c- 'a']) {
                maxCount = counts[c - 'a'];
                maxChar = c;
            }
        }
        if (maxCount > (n + 1) / 2) {
            return "";
        }
        char[] res = new char[n];
        int index = 0;
        for (int i = 0; i < maxCount; ++i) {
            res[index] = maxChar;
            index += 2;
        }
        for (int i = 0; i < 26; ++i) {
            if (counts[i] == 0 || (char)(i + 'a') == maxChar) {
                continue;
            }
            char cur = (char)(i + 'a');
            for (int j = 0; j < counts[i]; ++j) {
                if (index >= n) {
                    index = 1;
                }
                res[index] = cur;
                index += 2;
            }
        }
        return new String(res);
    }
}
