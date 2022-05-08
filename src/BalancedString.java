public class BalancedString {
    public static void main(String[] args) {
        BalancedString test = new BalancedString();
        String s = "AcZCbaBz";
        System.out.println(test.solution(s));
    }

    public int solution(String S) {
        // write your code in Java SE 8
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); ++i) {
            int[] map = new int[128];
            int balance = 0;
            for (int j = i; j < S.length(); ++j) {
                char cur = S.charAt(j);
                ++map[cur];
                if (Character.isUpperCase(cur)) {
                    if (map[cur] == 1 && map[cur + 32] > 0) {
                        --balance;
                    } else if (map[cur + 32] == 0) {
                        ++balance;
                    }
                } else {
                    if (map[cur] == 1 && map[cur - 32] > 0) {
                        --balance;
                    } else if (map[cur - 32] == 0) {
                        ++balance;
                    }
                }
                if (balance == 0) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
