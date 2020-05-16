public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        return palindromeHelper(s, 0, s.length() - 1);
    }

    public static boolean palindromeHelper(String input, int i, int j) {
        if (i > j || i == j) {
            return true;
        }
        if (!((input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= '0' && input.charAt(i) <= '9'))) {
            return palindromeHelper(input, i + 1, j);
        }
        else if (!((input.charAt(j) >= 'a' && input.charAt(j) <= 'z') || (input.charAt(j) >= '0' && input.charAt(j) <= '9'))) {
            return palindromeHelper(input, i, j - 1);
        }
        else if (input.charAt(i) == input.charAt(j)) {
            return palindromeHelper(input, i + 1, j - 1);
        } else {
            return false;
        }
    }
}
