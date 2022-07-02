import java.util.*;

public class LargestSumOfValidNumbers {
    public int largestSum(int[][] matrix) {
        List<boolean[]> configs = getAllConfigs(8);
        Map<boolean[], List<boolean[]>> agrees = getAllAgrees(configs); // 两个相邻不冲突的config就是agree的
        Map<boolean[], Integer> dp = new HashMap<>();
        for (boolean[] config : configs) {
            dp.put(config, sum(config, matrix[0]));
        } //初始化，填充第一行, dp[x]：对于config x，从第一行到现在总共的sum是多少
        for (int row = 1; row < 8; row++) { //有了上一行，填现在这行
            Map<boolean[], Integer> dp2 = new HashMap<>(); // build next level
            for (boolean[] config : configs) {
                int curSum = sum(config, matrix[row]);
                int lastSum = Integer.MIN_VALUE;
                for (boolean[] lastCon : agrees.get(config)) {
                    lastSum = Math.max(dp.get(lastCon), lastSum);
                }
                dp2.put(config, lastSum + curSum);
            }
            dp = dp2;
        }
        int res = Integer.MIN_VALUE;
        for (Integer val : dp.values()) {
            res = Math.max(res, val);
        }
        return res;
    }

    private List<boolean[]> getAllConfigs(int n) {
        List<boolean[]> configs = new ArrayList<>();
        dfs(0, new boolean[n], configs);
        return configs;
    }

    private void dfs(int i, boolean[] config, List<boolean[]> configs) {
        if (i == config.length) {
            configs.add(Arrays.copyOf(config, config.length));
            return;
        }
        dfs(i + 1, config, configs);
        if (i == 0 || !config[i - 1]) {
            config[i] = true;
            dfs(i + 1, config, configs);
            config[i] = false;
        }
    }

    private Map<boolean[], List<boolean[]>> getAllAgrees(List<boolean[]> configs) {
        Map<boolean[], List<boolean[]>> res = new HashMap<>();
        for (boolean[] config : configs) {
            res.put(config, new ArrayList<boolean[]>());
        }
        for (boolean[] c1 : configs) {
            for (boolean[] c2 : configs) {
                if (agree(c1, c2)) {
                    res.get(c1).add(c2);
                }
            }
        }
        return res;
    }

    private boolean agree(boolean[] c1, boolean[] c2) {
        for (int i = 0; i < c1.length; i++) {
            if (!c1[i]) continue;
            if (c2[i]) return false;
            if (i > 0 && c2[i - 1]) return false;
            if (i + 1 < c2.length && c2[i + 1]) return false;
        }
        return true;
    }

    private int sum(boolean[] config, int[] row) {
        int ans = 0;
        for (int i = 0; i < row.length; i++) {
            if (config[i]) ans += row[i];
        }
        return ans;
    }


}
