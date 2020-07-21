import java.util.*;

public class RemoveCertainCharacters {
    public String remove(String input, String t) {
        char[] array = input.toCharArray();
        Set<Character> occur = new HashSet<>();
        for (int i = 0; i < t.length(); ++i) {
            occur.add(t.charAt(i));
        }
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (!occur.contains(array[fast])) {
                array[slow++] = array[fast];
            }
            fast++;
        }
        return new String(array, 0, slow);
    }
}
