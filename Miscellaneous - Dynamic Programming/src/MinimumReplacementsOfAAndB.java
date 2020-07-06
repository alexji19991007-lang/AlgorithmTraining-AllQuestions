public class MinimumReplacementsOfAAndB {
    public int minReplacements(String input) {
        // 我们想找到最后a和b的分界线应该在哪里，所以统计下每个位置左边的b数量，还有每个位置右边的a数量。
        // 最后找到（左边b数量 + 右边a数量）最小的位置。这个数量就是最小的replacement个数.
        if (input == null || input.length() <= 1) {
            return 0;
        }
        int[] bCount = new int[input.length()];
        for (int i = 1; i < bCount.length; ++i) {
            if (input.charAt(i - 1) == 'b') {
                bCount[i] = bCount[i - 1] + 1;
            } else {
                bCount[i] = bCount[i - 1];
            }
        }
        int[] aCount = new int[input.length()];
        for (int i = aCount.length - 2; i >= 0; --i) {
            if (input.charAt(i + 1) == 'a') {
                aCount[i] = aCount[i + 1] + 1;
            } else {
                aCount[i] = aCount[i + 1];
            }
        }
        int minReplace = Integer.MAX_VALUE;
        for (int i = 0; i < input.length(); ++i) {
            minReplace = Math.min(minReplace, aCount[i] + bCount[i]);
        }
        return minReplace;
    }
}
