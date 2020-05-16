public class DecodeWays {
    public static void main(String[] args) {
        String input = "1121";
        System.out.println(numDecodings(input));
    }

    private static int numDecodings(String s) {
        // Three edge cases where we cannot decode the string
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[0] = 1;
        ways[1] = 1;
        // ways[i] means the total number of decode ways up to the (i - 1)th letter.
        for (int i = 1; i < len; ++i) {
            int one = Integer.parseInt(s.substring(i, i + 1));
            int two = Integer.parseInt(s.substring(i - 1, i + 1));
            // if the digit itself can be interpreted as a character, then we can decode it in ways[i] # of ways
            if (one > 0 && one < 10) {
                ways[i + 1] += ways[i];
            }
            // if the digit and the digit before together can be interpreted as a character, then we can decode in another ways[i - 1] # of ways.
            if (two > 9 && two < 27) {
                ways[i + 1] += ways[i - 1];
            }
        }
        return ways[len];
    }
}
