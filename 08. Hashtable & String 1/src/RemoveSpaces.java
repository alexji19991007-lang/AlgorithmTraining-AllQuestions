import java.util.*;

public class RemoveSpaces {
    public String removeSpaces(String input) {
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (array[fast] == ' ' && (fast == 0 || array[fast - 1] == ' ')) {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }
}
