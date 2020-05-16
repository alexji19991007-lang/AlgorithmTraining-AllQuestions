public class MaxProductOfCuttingRope {
    public int maxProduct(int length) {
        int[] M = new int[length + 1];
        for (int i = 1; i < length + 1; ++i) {
            for (int j = 1; j < i; ++j) {
                // 左大段 --> 怎么切？ --> 查表
                // 右小段 --> 不切 直接乘
                M[i] = Math.max(M[i], Math.max(j, M[j]) * (i - j));
            }
        }
        return M[length];
    }
}
