import java.util.HashSet;
import java.util.Set;

// LeetCode 1554
public class StringsDifferByOneCharacter {
    // TC: O(m * n), m = wordLength, n = dictLength
    // SC: O(m * n)
    public boolean differByOne(String[] dict) {
        // This mod is to prevent overflow
        long mod = Long.MAX_VALUE;
        int wordLength = dict[0].length();
        long[] wordToHash = new long[dict.length];
        // Compute the hash of each word in the dict
        for (int i = 0; i < dict.length; ++i) {
            for (int j = 0; j < wordLength; ++j) {
                wordToHash[i] = (wordToHash[i] * 26 + dict[i].charAt(j) - 'a') % mod;
            }
        }
        long base = 1;
        Set<Long> set = new HashSet<>();
        for (int i = wordLength - 1; i >= 0; --i) {
            set.clear();
            // For all the words, remove the hash of one letter from its hash and see if there is a match.
            for (int j = 0; j < dict.length; ++j) {
                long newHash = (wordToHash[j] - base * (dict[j].charAt(i) - 'a')) % mod;
                if (!set.add(newHash)) {
                    return true;
                }
            }
            base = base * 26 % mod;
        }
        return false;
    }
}
