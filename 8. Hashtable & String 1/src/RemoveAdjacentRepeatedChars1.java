public class RemoveAdjacentRepeatedChars1 {
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 1;
        int fast = 1;
        while (fast < array.length) {
            if (array[fast] != array[slow - 1]) {
                array[slow++] = array[fast];
            }
            fast++;
        }
        return new String(array, 0, slow);
    }
}
