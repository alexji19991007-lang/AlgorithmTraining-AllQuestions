import java.util.*;

public class AllUniqueChars2 {
    public static void main(String[] args) {
        System.out.println(allUniqueChars2("abcd/[0_1/"));
    }

    // int[] dic = new int[8];
    // Each int has 32 bits
    // 8 × 32 = 256 bits total
    // Enough to cover all ASCII characters --> So dic is basically a bitset
    // char cur = word.charAt(i);
    // int row = cur / 32; row → which integer in the array
    // int col = cur % 32; col → which bit inside that integer
    // 'a' = 97 → row = 97 / 32 = 3, col = 97 % 32 = 1
    // 'b' = 98 → row = 3, col = 2
    public static boolean allUniqueChars2(String word) {
        if (word.length() <= 1) {
            return true;
        }
        int[] dic = new int[8];
        for (int i = 0; i < word.length(); ++i) {
            char cur = word.charAt(i);
            int row = cur / 32;
            int col = cur % 32;
            int bitChecker = 1 << col; // Bit-tester!
            if ((dic[row] & bitChecker) != 0) {
                return false;
            }
            dic[row] |= bitChecker;
        }
        return true;
    }
}
