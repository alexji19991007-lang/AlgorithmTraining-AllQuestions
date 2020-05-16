import java.util.*;

public class FactorCombinations {
    public static void main(String[] args) {
        System.out.println(combinations(2523).toString());
    }

    public static List<List<Integer>> combinations(int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 1) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        List<Integer> factors = getFactors(target);
        helper(target, factors, 0, cur, res);
        return res;
    }

    public static void helper(int target, List<Integer> factors, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == factors.size()) {
            if (target == 1) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        helper(target, factors, index + 1, cur, res);
        int factor = factors.get(index);
        int size = cur.size();
        while (target % factor == 0) {
            cur.add(factor);
            target /= factor;
            helper(target, factors, index + 1, cur, res);
        }
        cur.subList(size, cur.size()).clear();
    }


    public static List<Integer> getFactors(int target) {
        List<Integer> factors = new ArrayList<>();
        for (int i = target / 2; i > 1; --i) {
            if (target % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
}
