import java.util.ArrayList;
import java.util.List;

public class CoinCombination {
    public static void main(String[] args) {
        int target = 50;
        int[] coins = {1, 5, 10, 25};
        System.out.println(combinations(target, coins).toString());
    }

    public static List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        helper(target, coins, 0, solution, res);
        return res;
    }

    public static void helper(int remain, int[] coins, int index, List<Integer> sol, List<List<Integer>> res) {
        if (index == coins.length - 1) {
            if (remain % coins[index] == 0) {
                sol.add(remain / coins[index]);
                res.add(new ArrayList<>(sol));
                sol.remove(sol.size() - 1);
            }
            return;
        }
        int maxUse = remain / coins[index];
        for (int i = 0; i <= maxUse; ++i) {
            sol.add(i);
            helper(remain - i * coins[index], coins, index + 1, sol, res);
            sol.remove(sol.size() - 1);
        }
    }
}
