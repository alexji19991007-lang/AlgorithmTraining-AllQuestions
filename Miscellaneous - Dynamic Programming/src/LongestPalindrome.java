public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome test = new LongestPalindrome();
        System.out.println(test.longestPalindrome("abcbcbd"));
    }

    public String longestPalindrome(String input) {
        // Write your solution here
        if (input.length() <= 1) {
            return input;
        }
        int n = input.length();
        int resLeft = -1, resRight = -1;
        boolean[][] M = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                M[j][i] = (input.charAt(i) == input.charAt(j)) && (i - j <= 2 || M[j + 1][i - 1]);
                if (M[j][i] && (resLeft == -1 || i - j + 1 > resRight - resLeft + 1)) {
                    resLeft = j;
                    resRight = i;
                }
            }
        }
        return input.substring(resLeft, resRight + 1);
    }
}
