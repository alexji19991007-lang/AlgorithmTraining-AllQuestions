import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char x = s.charAt(i);
            // If we encounter a digit, continue reading until we finish reading the whole number
            if (Character.isDigit(x)) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = 10 * count + (s.charAt(i) - '0');
                    i++;
                }
                // push to the count stack
                countStack.push(count);
            } else if (x == '[') {
                // if we encounter a right bracket, that means an new subword begins, so we push
                // the previous word to the stack
                stringStack.push(res);
                res = "";
                i++;
            } else if (x == ']') {
                // 3[a2[c]] -> 3[acc]
                // In this case, there is an 'a' before our 2 'c's, so we must not forget a.
                // Thus, we initialize our string builder with stringStack.pop()
                // Notice that 'a' is on the top of our stringStack right now.
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int n = countStack.pop();
                // repeat n times
                for (int j = 0; j < n; ++j) {
                    temp.append(res);
                }
                res = temp.toString();
                i++;
            } else {
                res += x;
                i++;
            }
        }
        return res;
    }
}
