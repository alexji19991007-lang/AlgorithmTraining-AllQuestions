import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        System.out.println(combinationSum3(k, n));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(res, temp, k, n, 1);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> temp, int k, int target, int start) {
        if (target < 0) {
            return;
        }
        if (temp.size() > k) {
            return;
        }
        if (temp.size() == k && target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= 9; ++i) {
            temp.add(i);
            backtrack(res, temp, k, target - i, i + 1);
            temp.remove(temp.size() - 1);
        }
//        for (int i = index; i <= k; ++i) {
//            temp.add(i);
//            backtrack(res, temp, k, target - i, i + 1);
//            // not i + 1 since we can use one item multiple times
//            temp.remove(temp.size() - 1);
//        }
    }
}
