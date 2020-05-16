public class AddBinary {
    public static void main(String[] args) {
        String a = "111";
        String b = "111";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        // Write your solution here
        int n = a.length(), m = b.length();
        if (n < m) {
            return addBinary(b, a);
        }
        int L = Math.max(n, m);

        StringBuilder sb = new StringBuilder();
        int carry = 0, j = m - 1;
        for (int i = L - 1; i > -1; --i) {
            if (a.charAt(i) == '1') {
                ++carry;
            }
            if (j > -1 && b.charAt(j) == '1'){
                ++carry;
            }
            j--;
            if (carry % 2 == 1) {
                sb.append('1');
            }
            else {
                sb.append('0');
            }
            carry /= 2;
        }
        if (carry == 1) {
            sb.append('1');
        }
        sb.reverse();
        return sb.toString();
    }
}
