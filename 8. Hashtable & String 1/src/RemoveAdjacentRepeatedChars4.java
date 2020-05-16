import java.lang.instrument.Instrumentation;
import java.util.*;

public class RemoveAdjacentRepeatedChars4 {
    public static void main(String[] args) {
        String input = "abbbaaccz";
        System.out.println(deDupWithStack(input));
    }

    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 1, fast = 1;
        while (fast < array.length) {
            if (slow == 0 || array[slow - 1] != array[fast]) {
                array[slow++] = array[fast];
            } else {
                slow--;
                while (fast + 1 < array.length && array[fast] == array[fast + 1]) {
                    fast++;
                }
            }
            fast++;
        }
        return new String(array, 0, slow);
    }

    public static String deDupWithStack(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        Deque<Character> mStack = new ArrayDeque<>();
        char[] array = input.toCharArray();
        int fast = 0;
        while (fast < array.length) {
            char cur = array[fast];
            if (!mStack.isEmpty() && mStack.peekFirst() == cur) {
                while (fast < array.length && array[fast] == cur) {
                    fast++;
                }
                mStack.pollFirst();
            } else {
                mStack.offerFirst(cur);
                fast++;
            }
        }
        int ansLen = mStack.size();
        int i = ansLen - 1;
        while (!mStack.isEmpty()) {
            array[i--] = mStack.pollFirst();
        }
        return new String(array, 0, ansLen);
    }
}
