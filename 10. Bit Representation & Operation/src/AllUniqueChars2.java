import java.util.*;

public class AllUniqueChars2 {
    public static void main(String[] args) {
        System.out.println(allUniqueChars2("abcd/[0_1/"));
    }

    public static boolean allUniqueChars2(String word) {
        if (word.length() <= 1) {
            return true;
        }
        int[] dic = new int[8];
        for (int i = 0; i < word.length(); ++i) {
            char cur = word.charAt(i);
            int row = cur / 32;
            int col = cur % 32;
            int bitChecker = 1 << col;
            if ((dic[row] & bitChecker) != 0) {
                return false;
            }
            dic[row] |= bitChecker;
        }
        return true;
    }
}
