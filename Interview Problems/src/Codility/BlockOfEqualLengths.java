package Codility;

public class BlockOfEqualLengths {
    public int blockOfEqualLengths(String s) {
        int res = 0;
        int maxSize = 1, curSize = 1;
        for (int i = 1; i <= s.length(); ++i) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                maxSize = Math.max(maxSize, curSize);
                curSize = 1;
            } else {
                curSize++;
            }
        }
        for (int i = 1; i <= s.length(); ++i) {
            if (i == s.length() || s.charAt(i) == s.charAt(i - 1)) {
                res += maxSize - curSize;
                curSize = 1;
            } else {
                curSize++;
            }
        }
        return res;
    }
}
