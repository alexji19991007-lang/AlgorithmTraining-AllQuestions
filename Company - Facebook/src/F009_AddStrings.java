// LeetCode 415
public class F009_AddStrings {
    // TC: O(max(m, n))
    // SC: O(max(m, n)) --> m, n are the lengths of num1 & num2, respectively
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int curVal1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int curVal2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int newVal = curVal1 + curVal2 + carry;
            carry = newVal >= 10 ? 1 : 0; // update carry
            newVal %= 10; // newVal should be < 10 after this statement
            sb.append((char)(newVal + '0'));
            i--;
            j--;
        }
        if (carry != 0) {
            sb.append((char)(carry + '0'));
        }
        return sb.reverse().toString();
    }
}
