public class EditDistance {
    public static void main(String[] args) {
        String one = "ab", two = "dbbabc";
        System.out.println(minDistance(one, two));
    }

    public static int minDistance(String one, String two) {
        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        // Always make sure one is the shorter one.
        if (one.length() > two.length()) {
            String temp = two;
            two = one;
            one = temp;
        }
        int[] prevRow = new int[one.length() + 1];
        int[] curRow = new int[one.length() + 1];
        for (int i = 0; i < prevRow.length; ++i) {
            prevRow[i] = i;
        }
        for (int i = 1; i <= two.length(); ++i) {
            for (int j = 0; j <= one.length(); ++j) {
                if (j == 0) {
                    curRow[0] = i;
                    continue;
                }
                if (one.charAt(j - 1) == two.charAt(i - 1)) {
                    curRow[j] = prevRow[j - 1];
                } else {
                    int replace = 1 + prevRow[j - 1];
                    int delete = 1 + prevRow[j];
                    int insert = 1 + curRow[j - 1];
                    curRow[j] = Math.min(replace, Math.min(delete, insert));
                }
            }
            // Swap prevRow & curRow so curRow becomes prevRow, and curRow points to a new array
            int[] temp = prevRow;
            prevRow = curRow;
            curRow = temp;
        }
        return prevRow[one.length()];
    }
}
