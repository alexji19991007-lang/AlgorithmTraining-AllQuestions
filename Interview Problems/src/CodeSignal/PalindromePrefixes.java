package CodeSignal;

public class PalindromePrefixes {
    public static void main(String[] args) {
        PalindromePrefixes test = new PalindromePrefixes();
        System.out.println(test.palindromePrefixes("ccccooddeesignal"));
    }

    public String palindromePrefixes(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int n = s.length();
        // isPal[i][j] means the substring from index i to index j is palindrome or not (head&tail-inclusive)
        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (j + 1 >= i - 1 || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;
                }
            }
        }
        int left = 0, right = n - 1;
        int i = 0, j = n - 1;
        while (left < right) {
            while (i < j) {
                if (!isPal[i][j]) {
                    j--;
                } else {
                    left = j + 1;
                    i = left;
                    j = right;
                    break;
                }
            }
            if (i == j) {
                break;
            }
        }
        return s.substring(left, right + 1);
    }
}
