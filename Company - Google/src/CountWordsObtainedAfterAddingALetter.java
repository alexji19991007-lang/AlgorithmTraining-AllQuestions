import java.util.HashSet;
import java.util.Set;

// LeetCode 2135
public class CountWordsObtainedAfterAddingALetter {
    // TC: O(M * C + N * (C + 26)) = O((M + N) * C) where M & N are two array's length and C is the length of the longest word
    // SC: O(M)
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> startWordsBinary = new HashSet<>();
        for (String s : startWords) {
            startWordsBinary.add(toBinaryForm(s));
        }
        int res = 0;
        for (String target : targetWords) {
            int targetBinary = toBinaryForm(target);
            for (int i = 0; i < 26; ++i) {
                int bitMask = 1 << i;
                if ((bitMask & targetBinary) > 0) {
                    int transformedTargetBinary = targetBinary & (~bitMask);
                    if (startWordsBinary.contains(transformedTargetBinary)) {
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    public int toBinaryForm(String s) {
        int binaryForm = 0;
        for (char c : s.toCharArray()) {
            binaryForm |= (1 << (c - 'a'));
        }
        return binaryForm;
    }
}
