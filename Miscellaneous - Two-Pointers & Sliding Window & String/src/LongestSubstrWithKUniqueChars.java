public class LongestSubstrWithKUniqueChars {

    // return the size of the longest substring of s that contains exactly k unique characters
    // only alphabetic characters are allowed
    public static int[] kUniques(String s, int k) {
        if (s.length() < k) {
            return new int[]{-1, -1};
        }
        int numUnique = 0;
        int n = s.length();
        int[] count = new int[26];
        // first loop through the string s to find how many unique characters there are.
        for (int i = 0; i < n; ++i) {
            char x = s.charAt(i);
            if (count[x - 'a'] == 0) {
                numUnique++;
            }
            count[x - 'a']++;
        }
        // immediately exit if the # of unique characters is less than k
        if (numUnique < k) {
            return new int[]{-1, -1};
        }
        // set up the sliding window
        int start = 0, end = 0;
        int maxSize = 0, maxSizeStart = 0;
        count = new int[26];
        count[s.charAt(0) - 'a']++;

        for (int i = 1; i < n; ++i) {
            // increment the corresponding count by 1
            count[s.charAt(i) - 'a']++;
            end++; // move the right pointer every loop
            // we will move the left pointer until the string becomes valid again
            while (!isValid(count, k)) {
                count[s.charAt(start) - 'a']--;
                start++;
            }
            // update the maxSize if necessary
            if (end - start + 1 > maxSize) {
                maxSize = end - start + 1;
                maxSizeStart = start;
            }
        }
        return new int[]{maxSize, maxSizeStart};
    }

    public static boolean isValid(int[] count, int k) {
        int val = 0;
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0) {
                val++;
            }
        }
        return k >= val;
    }
}
