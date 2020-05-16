public class LongestPalindrome {
    public String longestPalindrome(String input) {
        int n = input.length();
        String res = "";
        boolean[][] memo = new boolean[n][n];
        /*
              1 2 3 4 5
            1
            2
            3
            4
            5

            The following for-loops reads in the sequence:
            (5, 5)
            (4, 4) (4, 5)
            (3, 3) (3, 4) (3, 5)
            (2, 2) (2, 3) (2, 4) (2, 5)
            (1, 1) (1, 2) (1, 3) (1, 4) (1, 5)
         */
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                // memo[i][j] = true iff input[j] == input[i] && the substring between i and j are also palindromic
                memo[i][j] = (input.charAt(i) == input.charAt(j)) && (j - i <= 2 || memo[i + 1][j - 1]);
                // If it is palindromic, update the res when it should become longer
                if (memo[i][j] && (res.length() == 0 || j - i + 1 > res.length())) {
                    res = input.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
