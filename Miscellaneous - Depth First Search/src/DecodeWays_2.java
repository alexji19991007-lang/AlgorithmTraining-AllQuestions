import java.util.ArrayList;
import java.util.List;

public class DecodeWays_2 {
    private static final char[] decode = {'0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
        String input = "1121";
        List<String> arrList = allPossibleWays(input);
        for (String x : arrList) {
            System.out.println(x);
        }
    }

    private static List<String> allPossibleWays(String s) {
        List<String> output = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return output;
        }
        char[] array = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        dfs(array, output, sb, 0);
        return output;
    }

    private static void dfs(char[] array, List<String> output, StringBuilder sb, int index) {
        if (index >= array.length) {
            output.add(sb.toString());
            return;
        }
        int cur = array[index] - '0';
        if (cur != 0) {
            sb.append(decode[cur]);
            dfs(array, output, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (index + 1 < array.length) {
            int temp = array[index + 1] - '0';
            cur = cur * 10 + temp;
            if (cur <= 26 && cur >= 10) {
                sb.append(decode[cur]);
                dfs(array, output, sb, index + 2);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}