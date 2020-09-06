package Robinhood;

public class RemoveOneChar {
    public static void main(String[] args) {
        RemoveOneChar instance = new RemoveOneChar();
        String S1 = "abcefe";
        String T1 = "abcefe";

        String S2 = "";
        String T2 = "";

        String S3 = "ade";
        String T3 = "a";

        String S4 = "a";
        String T4 = "ade";

        String S5 = "ad";
        String T5 = "af";
        System.out.println(instance.numOfWaysToRemove(S1, T1));
        System.out.println(instance.numOfWaysToRemove(S2, T2));
        System.out.println(instance.numOfWaysToRemove(S3, T3));
        System.out.println(instance.numOfWaysToRemove(S4, T4));
        System.out.println(instance.numOfWaysToRemove(S5, T5));
    }

    public int numOfWaysToRemove(String s, String t) {
        int[] numOfWays = new int[1];
        waysOfRemove(s, t, numOfWays);
        return numOfWays[0];
    }

    private void waysOfRemove(String S, String T, int[] numOfWays) {
        if (S.length() == 0 && T.length() == 0) {
            return;
        }
        if (S.length() == 0) {
            numOfWays[0] += T.length();
            return;
        }
        if (T.length() == 0) {
            return;
        }
        if (isValid(S.substring(1), T)) {
            numOfWays[0] += 1;
        }
        if (isValid(S, T.substring(1))) {
            numOfWays[0] += 1;
        }
        if (S.charAt(0) <= T.charAt(0)) {
            waysOfRemove(S.substring(1), T.substring(1), numOfWays);
        }
    }

    public boolean isValid(String S, String T) {
        int idx = 0;
        while (idx < S.length() && idx < T.length()) {
            if (S.charAt(idx) < T.charAt(idx)) {
                return true;
            } else if (S.charAt(idx) > T.charAt(idx)) {
                return false;
            } else {
                idx++;
            }
        }
        if (idx == S.length() && idx == T.length()) {
            return false;
        } else {
            return idx >= S.length();
        }
    }
}
