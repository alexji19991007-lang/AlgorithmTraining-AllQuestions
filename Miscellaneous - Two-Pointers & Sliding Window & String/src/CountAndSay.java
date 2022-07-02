import java.util.LinkedList;
import java.util.List;

public class CountAndSay {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(countAndSay(n));
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        StringBuilder prev = new StringBuilder("11");
        for (int i = 3; i <= n; ++i) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            for (int j = 1; j <= prev.length(); ++j) {
                if (j == prev.length() || prev.charAt(j) != prev.charAt(j - 1)) {
                    temp.append(count).append(prev.charAt(j - 1));
                    count = 1;
                } else {
                    count++;
                }
            }
            prev = temp;
        }
        return prev.toString();
    }
}
