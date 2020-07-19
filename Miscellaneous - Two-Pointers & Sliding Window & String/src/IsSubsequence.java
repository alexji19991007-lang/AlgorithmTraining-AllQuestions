public class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence test = new IsSubsequence();
        System.out.println(test.isSubsequence("axc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int sLeft = 0, tLeft = 0;
        while (sLeft < n && tLeft < m) {
            if (s.charAt(sLeft) == t.charAt(tLeft)) {
                sLeft++;
            }
            tLeft++;
        }
        return sLeft == n;
    }
}
