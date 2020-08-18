public class ScreenSentenceFitting {
    public static final String SPACE = "-";

    public static void main(String[] args) {
        String[] sentence = {"I", "had", "apple", "pie"};
        ScreenSentenceFitting test = new ScreenSentenceFitting();
        System.out.println(test.wordsTyping(sentence, 4, 5));
    }

    // Brute Force
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int res = 0;
        int idx = 0;
        for (int i = 0; i < rows; ++i) {
            int curLen = 0;
            while (curLen < cols) {
                String curWord = sentence[idx];
                if (curLen + curWord.length() > cols) {
                    break;
                }
                curLen += curWord.length();
                res += idx == sentence.length - 1 ? 1 : 0;
                idx = (idx + 1) % sentence.length;
                if (curLen < cols) {
                    curLen++;
                }
            }
        }
        return res;
    }

    // DP
    public int wordsTyping_dp(String[] sentence, int rows, int cols) {
        // nextWord[i] = if the current row starts with sentence[i], what will the next row starts with?
        int[] nextWord = new int[sentence.length];
        // times[i] = how many prints are completed in this row, i.e. how many times the last word in
        // sentences appears in this row
        int[] times = new int[sentence.length];
        for (int i = 0; i < sentence.length; ++i) {
            // 以sentence[i]开头的row
            int curWordPtr = i, curLen = 0, curTime = 0;
            while (curLen + sentence[curWordPtr].length() <= cols) {
                curLen += sentence[curWordPtr++].length() + 1;
                if (curWordPtr == sentence.length) {
                    curTime++;
                    curWordPtr = 0;
                }
            }
            // 这一个row已经filled了，下一行应该从哪个word开始
            nextWord[i] = curWordPtr;
            // 这一行完成的print次数（有多少次e的出现）
            times[i] = curTime;
        }
        int totalTimes = 0;
        // 第一行肯定放sentence[0]
        int start = 0;
        for (int i = 0; i < rows; ++i) {
            totalTimes += times[start];
            // 下一行由哪一个word开始？
            start = nextWord[start];
        }
        return totalTimes;
    }
}
