public class CuttingWood {
    public int minCost(int[] cuts, int length) {
        int[] helper = new int[cuts.length + 2];
        // 在原本的array前后加上0和L（代表起始和末尾）
        helper[0] = 0;
        for (int i = 0; i < cuts.length; ++i) {
            helper[i + 1] = cuts[i];
        }
        helper[helper.length - 1] = length;
        // minCost[i][j] = the minimum total cost of cutting the wood piece between i-th marker
        // and j-th marker
        int[][] minCost = new int[helper.length][helper.length];
        for (int i = 1; i < helper.length; ++i) {
            // 如果是相邻的两个marker，中间那一段不需要切，cost为0，所以我们可以从j = i - 2开始
            for (int j = i - 2; j >= 0; --j) {
                minCost[j][i] = Integer.MAX_VALUE;
                for (int k = j + 1; k <= i - 1; ++k) {
                    // 左大段minCost[j][k] + 右大段minCost[k][j]
                    // j<-->k<-->i
                    // Adjust k to make the sum as small as possible
                    minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
                }
                minCost[j][i] += helper[i] - helper[j];
            }
        }
        return minCost[0][helper.length - 1];
    }
}
