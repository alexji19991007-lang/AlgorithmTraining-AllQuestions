import java.util.*;

public class RightShiftByNCharacters {
    public String rightShift(String input, int n) {
        if (input.length() == 0 || n % input.length() == 0) {
            return input;
        }
        n %= input.length();
        char[] array = input.toCharArray();
        reverseHelper(array, 0, array.length - 1);
        reverseHelper(array, 0, n - 1);
        reverseHelper(array, n, array.length - 1);
        return new String(array);
    }

    public void reverseHelper(char[] input, int left, int right) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            swap(input, left++, right--);
        }
    }

    public void swap(char[] input, int left, int right) {
        char temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}
