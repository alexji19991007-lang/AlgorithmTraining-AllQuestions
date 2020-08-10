package CodeSignal;

public class ReverseDigitsByPair {
    public static void main(String[] args) {
        ReverseDigitsByPair test = new ReverseDigitsByPair();
        System.out.println(test.reverseDigits(123456));
    }

    public int reverseDigits(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int i = 0;
        while (i < s.length - 1) {
            swap(s, i, i + 1);
            i += 2;
        }
        return Integer.parseInt(new String(s));
    }

    private void swap(char[] s, int i , int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
