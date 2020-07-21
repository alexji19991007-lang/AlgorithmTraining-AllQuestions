import java.util.*;

public class FormARingWithWords {
    public boolean formRing(String[] input) {
        return canFormRing(input, 0, input[0].charAt(0));
    }

    public boolean canFormRing(String[] input, int index, char firstChar) {
        if (index == input.length - 1) {
            String last = input[input.length - 1];
            return firstChar == last.charAt(last.length() - 1);
        }
        String cur = input[index];
        char lastChar = cur.charAt(cur.length() - 1);
        for (int i = index + 1; i < input.length; ++i) {
            if (lastChar == input[i].charAt(0)) {
                swap(input, index + 1, i);
                if (canFormRing(input, index + 1, firstChar)) {
                    return true;
                } else {
                    swap(input, index + 1, i);
                }
            }
        }
        return false;
    }

    public void swap(String[] input, int one, int two) {
        String temp = input[one];
        input[one] = input[two];
        input[two] = temp;
    }
}
