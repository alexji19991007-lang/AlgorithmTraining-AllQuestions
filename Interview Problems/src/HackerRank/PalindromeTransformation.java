package HackerRank;

// Given a string, determine if it is an palindrome. If yes, replace any character in the string with letters a-z such
// that the new string is less than the original and is not a palindrome

public class PalindromeTransformation {
    public static void main(String[] args) {
        PalindromeTransformation test = new PalindromeTransformation();
        System.out.println(test.transform("zzzzazzzz"));
        System.out.println(test.transform("abccba"));
    }

    public String transform(String s) {
        char[] array = s.toCharArray();
        if (isPalindrome(array)) {
            return replaceChar(array);
        }
        return s;
    }

    public boolean isPalindrome(char[] array) {
        if (array.length <= 1) {
            return true;
        }
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (array[i++] != array[j--]) {
                return false;
            }
        }
        return true;
    }

    public String replaceChar(char[] array) {
        int i = 0;
        while (i < array.length / 2.0) {
            if (array[i] < 'z') {
                array[i] += 1;
                break;
            }
            i++;
        }
        return new String(array);
    }
}
