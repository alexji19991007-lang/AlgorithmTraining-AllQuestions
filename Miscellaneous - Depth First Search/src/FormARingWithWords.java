public class FormARingWithWords {
    public static void main(String[] args) {
        String[] input = {"abc", "efg", "cde", "ghi", "ija"};
        System.out.println(formRing(input));
    }

    public static boolean formRing(String[] input) {
        return canFormRing(input, 0, input[0].charAt(0));
    }

    public static boolean canFormRing(String[] input, int index, char firstChar) {
        if (index == input.length - 1) {
            String str = input[index];
            return firstChar == str.charAt(str.length() - 1);
        }
        String str = input[index];
        char lastChar = str.charAt(str.length() - 1);
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

    public static void swap(String[] input, int one, int two) {
        String temp = input[one];
        input[one] = input[two];
        input[two] = temp;
    }
}
