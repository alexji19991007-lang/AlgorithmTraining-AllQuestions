import java.util.*;

public class MaximumOccurringCharacter {
    public static void main(String[] args) {
        MaximumOccurringCharacter test = new MaximumOccurringCharacter();
        String text = "helloworld";
        System.out.println(test.maxOccurrence(text));
    }

    public char maxOccurrence(String text) {
        if (text == null || text.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> occur = new LinkedHashMap<>();
        int maxOccur = 0;
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            int curCharOccurrence = occur.getOrDefault(c, 0) + 1;
            occur.put(c, curCharOccurrence);
            maxOccur = Math.max(maxOccur, curCharOccurrence);
        }
        for (Map.Entry<Character, Integer> entry : occur.entrySet()) {
            if (entry.getValue() == maxOccur) {
                return entry.getKey();
            }
        }
        // should not reach here.
        return ' ';
    }
}
