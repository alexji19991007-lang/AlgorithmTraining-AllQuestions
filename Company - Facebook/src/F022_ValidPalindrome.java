// LeetCode 125
public class F022_ValidPalindrome {
    // TC: O(n)
    // SC: O(1)
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        String input = s.toLowerCase();
        while (i < j) {
            if (!((input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= '0' && input.charAt(i) <= '9'))) {
                i++;
            } else if (!((input.charAt(j) >= 'a' && input.charAt(j) <= 'z') || (input.charAt(j) >= '0' && input.charAt(j) <= '9'))) {
                j--;
            } else if (input.charAt(i) == input.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
