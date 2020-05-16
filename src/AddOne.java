public class AddOne {
    public static void main(String[] args) {
        String orig = "0";
        System.out.println(incrementOne(orig));
    }

    public static String incrementOne(String num) {
        StringBuilder sb = new StringBuilder();
        int carry = 1; // The number to be added
        for (int i = num.length() - 1; i >= 0; --i) {
            int curVal = num.charAt(i) - '0';
            int newVal = curVal + carry;
            carry = newVal >= 10 ? 1 : 0; // update carry
            newVal %= 10; // newVal should be < 10 after this statement
            sb.append((char)(newVal + '0'));
        }
        // If the first char in the string is 9 and we have 1 more carry, we have to append another 1
        if (carry == 1) {
            sb.append('1');
        }
        // Since we are always appending new chars to the StringBuilder, we have to reverse it in order to get the
        // final solution.
        return sb.reverse().toString();
    }
}
