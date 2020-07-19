public class AddOne {
    public static void main(String[] args) {
        String orig = "979";
        System.out.println(addOne(orig));
    }

    // String Version
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

    // String Version -- Better
    public static String addOne(String num) {
        int n = num.length();
        char[] array = num.toCharArray();
        for (int i = n - 1; i >= 0; --i) {
            int curVal = array[i] - '0';
            if (curVal == 9) {
                array[i] = '0';
            } else {
                array[i] = (char)(array[i] + 1);
                return new String(array);
            }
        }
        // Case where we need to put an extra 1 to the left of the original array
        return "1" + new String(array);
    }

    // Array Version
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        // Case where we need to put an extra 1 to the left of the original array
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
