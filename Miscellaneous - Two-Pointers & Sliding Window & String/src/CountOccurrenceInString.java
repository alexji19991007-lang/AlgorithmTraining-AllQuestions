public class CountOccurrenceInString {
    private String s;
    //int[] countA;
    int[][] countAll;

    public CountOccurrenceInString(String s) {
        initialize(s);
    }

    public void initialize(String s) {
        this.s = s;
        int n = s.length();
//        countA = new int[n + 1];
//        for (int i = 1; i < countA.length; ++i) {
//            countA[i] = s.charAt(i - 1) == 'A' ? countA[i - 1] + 1 : countA[i - 1];
//        }
        countAll = new int[26][n + 1];
        for (int j = 1; j < n + 1; ++j) {
            for (int i = 0; i < 26; ++i) {
                countAll[i][j] = s.charAt(j - 1) == (char)('A' + i) ? countAll[i][j - 1] + 1 : countAll[i][j - 1];
            }
        }
    }

    //  AAAAAAAA
    // 012345678
    public int countNumber(int start, int end) {
        if (start > end || start >= s.length() || end >= s.length()) {
            return 0;
        }
        return countAll[0][end + 1] - countAll[0][start];
    }

    public char mostOccurrence(int start, int end) {
        int max = countAll[0][end + 1] - countAll[0][start];
        char maxChar = 'A';
        for (int i = 0; i < 26; ++i) {
            int curCount = countAll[i][end + 1] - countAll[i][start];
            if (max < curCount) {
                max = curCount;
                maxChar = (char)('A' + i);
            }
        }
        return maxChar;
    }

    public static void main(String[] args) {
        CountOccurrenceInString test = new CountOccurrenceInString("ABCAADCDCDCAA");
        System.out.println(test.countNumber(4, 9));
        System.out.println(test.mostOccurrence(0, 10));
    }
}
