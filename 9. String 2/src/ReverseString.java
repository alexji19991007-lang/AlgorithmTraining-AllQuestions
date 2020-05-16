import java.util.*;

public class ReverseString {
    public String reverse(String input) {
        char[] array = input.toCharArray();
        int left = 0, right = array.length - 1;
        while (left < right) {
            swap(array, left++, right--);
        }
        return new String(array);
    }

    public void swap(char[] input, int left, int right) {
        char temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}
