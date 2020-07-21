// LeetCode 680
public class F003_ValidPalindrome2 {
    // TC: O(n)
    // SC: O(1)
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return validHelper(s, left + 1, right) || validHelper(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean validHelper(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
