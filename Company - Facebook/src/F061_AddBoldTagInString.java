// LeetCode 616
public class F061_AddBoldTagInString {
    // TC: O(n * k * n), where n is the length of the string and k is the length of the array.
    // SC: O(n)
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        int end = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    // end means the right bound (not inclusive) of the bold tagged word
                    // Suppose we have a string abbcc, and dict ["ab", "abbc"], i = 0.
                    // Then end will first be 2 and then be 4, meaning that every letter from i = 0
                    // until i == end (not inclusive) will be bold.
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (!bold[index]) {
                res.append(s.charAt(index++));
                continue;
            }
            res.append("<b>");
            int j = index;
            while (j < s.length() && bold[j]) {
                res.append(s.charAt(j++));
            }
            res.append("</b>");
            index = j;
        }
        return res.toString();
    }
}
