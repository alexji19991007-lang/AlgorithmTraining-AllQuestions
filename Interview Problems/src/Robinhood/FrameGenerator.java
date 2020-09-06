package Robinhood;

public class FrameGenerator {
    public static void main(String[] args) {
        FrameGenerator test = new FrameGenerator();
        String[] arr = test.frameGenerator(8);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    public String[] frameGenerator(int n) {
        String[] res = new String[n];
        String firstLastRow = generateFirstLastRow(n);
        String remainingRow = generateRemainingRow(n);
        for (int i = 0; i < res.length; ++i) {
            res[i] = i == 0 || i == res.length - 1 ? firstLastRow : remainingRow;
        }
        return res;
    }

    public String generateFirstLastRow(int n) {
        return "*".repeat(n);
    }

    public String generateRemainingRow(int n) {
        return "*" +
                " ".repeat(Math.max(0, n - 2)) +
                "*";
    }
}
