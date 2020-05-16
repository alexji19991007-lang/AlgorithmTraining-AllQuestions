import java.util.Stack;

public class RemoveAllAdjacentDuplicates {
    public String removeDuplicates(String S) {
        int i = 0, j = 0;
        char[] array = S.toCharArray();
        while (j < S.length()) {
            array[i] = array[j];
            // if we find two consecutive characters, move i pointer back 2 steps, so the duplicates
            // will be overwritten in the following copy process
            if (i > 0 && array[i - 1] == array[i]) {
                i -= 2;
            }
            i++; // i actually moved back 1 step due to this ++;
            j++;
        }
        return new String(array, 0, i);
    }

    public String removeDuplicatesByStack(String S) {
        Stack<Character> s = new Stack<>();
        for (char x : S.toCharArray()) {
            if (!s.isEmpty() && s.peek() == x) {
                s.pop();
            } else {
                s.push(x);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char x : s) {
            sb.append(x);
        }
        return sb.toString();
    }
}
