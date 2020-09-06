package Robinhood;

public class PrefixString {
    public static void main(String[] args) {
        String[] a = {"one", "two", "four"};
        String[] b = {"one", "two"};
        PrefixString test = new PrefixString();
        System.out.println(test.prefixStrings(a, b));
    }

    public boolean prefixStrings(String[] a, String[] b) {
        for (String target : b) {
            int offset = 0;
            for (int i = 0; i < a.length && offset < target.length(); ++i) {
                if (!target.startsWith(a[i], offset)) {
                    return false;
                }
                offset += a[i].length();
            }
        }
        return true;
    }
}
