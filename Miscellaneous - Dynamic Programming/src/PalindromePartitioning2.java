public class PalindromePartitioning2 {
    public static void main(String[] args) {
        String x = "ACAACA";
        System.out.println(minCut(x));
    }

    public static int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        // isPal[i][j] means the substring from index i to index j is palindrome or not (head&tail-inclusive)
        boolean[][] isPal = new boolean[n][n];
        // cut[i] means the minimum number of cuts needed to cut the substring from index 0 to i (head&tail-inclusive)
        int[] cut = new int[n];
        for (int i = 0; i < n; ++i) {
            int minCut = i;
            for (int j = 0; j <= i; ++j) {
                // A string from j to i is a palindrome iff the starting character == the ending character
                // AND
                // (the substring from j + 1 to i - 1 is a palindrome) OR (the current string has length of 1, 2 or 3)
                if (c[j] == c[i] && (j + 1 >= i - 1 || isPal[j + 1][i - 1])) {
                    // if the current string is a palindrome, set the corresponding isPal position to true
                    isPal[j][i] = true;
                    // update the minCut.
                    // Suppose our string is:
                    // A   B   A   |   C   C
                    //        j-1      j   i
                    // We already know the minimum number of cuts need to properly cut the substring
                    // from 0 to j - 1, and this value can be directly read from the table cut
                    // (cut[j - 1]). So all we have to do is to put one more cut after j-1, before j.
                    // So the minCut for position j is cut[j - 1] + 1
                    // If j is 0, then we need no cut.
                    // 左大段右小段。左边已经process完成直接查表（cut[j - 1])，右边不用切因为已经是palindrome。只要在左大短和右小段之间再切一刀
                    minCut = j == 0 ? 0 : Math.min(minCut, cut[j - 1] + 1);
                }
            }
            cut[i] = minCut;
        }
        return cut[n - 1];
    }
}
