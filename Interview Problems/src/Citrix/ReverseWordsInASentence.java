package Citrix;

public class ReverseWordsInASentence {
    public String reverseWords(String input) {
        char[] array = input.toCharArray();
        reverseHelper(array, 0, array.length - 1);
        int slow = 0, fast = 0;
        while (fast < array.length) {
            if (fast == array.length - 1 || array[fast + 1] == ' ') {
                reverseHelper(array, slow, fast);
                fast += 2;
                slow = fast;
            } else {
                fast++;
            }
        }
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
