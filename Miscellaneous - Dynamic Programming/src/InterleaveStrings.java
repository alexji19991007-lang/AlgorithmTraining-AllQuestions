public class InterleaveStrings {
    public static void main(String[] args) {
        String a = "acd", b = "be", c = "abcde";
        InterleaveStrings test = new InterleaveStrings();
        System.out.println(test.canMerge(a, b, c));
    }

    public boolean canMerge(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        boolean[][] M = new boolean[b.length() + 1][a.length() + 1];
        M[0][0] = true;
        for (int i = 0; i < M.length; ++i) {
            for (int j = 0; j < M[0].length; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    M[0][j] = M[0][j - 1] && a.charAt(j - 1) == c.charAt(j - 1);
                } else if (j == 0) {
                    M[i][0] = M[i - 1][0] && b.charAt(i - 1) == c.charAt(i - 1);
                } else {
                    if (M[i - 1][j]) {
                        M[i][j] = b.charAt(i - 1) == c.charAt(i + j - 1);
                    } else if (M[i][j - 1]) {
                        M[i][j] = a.charAt(j - 1) == c.charAt(i + j - 1);
                    }
                }
            }
        }
        return M[b.length()][a.length()];
    }
}
