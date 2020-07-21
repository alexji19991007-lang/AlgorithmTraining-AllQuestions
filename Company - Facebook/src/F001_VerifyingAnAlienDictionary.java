// LeetCode 953
public class F001_VerifyingAnAlienDictionary {
    // TC: O(C) where C is the total number of characters
    // SC: O(26) = O(1)
    public boolean isAlienSorted(String[] words, String order) {
        int[] sequence = new int[26];
        // sequence[i] means the current alphabetic order of the i-th character in the original
        // alphabet
        for (int i = 0; i < sequence.length; ++i) {
            sequence[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; ++i) {
            // the check length should be the minimal of two words
            int length = Math.min(words[i].length(), words[i + 1].length());
            int j = 0;
            while (j < length) {
                // check if two characters are different
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    // if the new alphabetic order for the first character is after that of the
                    // second character, return false immediately
                    if (sequence[words[i].charAt(j) - 'a'] > sequence[words[i + 1].charAt(j) - 'a']) {
                        return false;
                    } else {
                        // Otherwise no more need to check these two words since we already know
                        // they are in the correct order, so break the inner while loop
                        break;
                    }
                }
                j++;
            }
            // if we reached the end of the minimum length, we can only make sure that two words are
            // the same for the minimum length. Therefore, we still have to make sure that the first
            // word is shorter than the second
            if (j == length && words[i].length() > words[i + 1].length()) {
                return false;
            }
        }
        return true;
    }
}
