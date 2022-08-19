package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockConsistency {
    public static void main(String[] args) {
        StockConsistency test = new StockConsistency();
        int[] prices = {1, -2, 1, 1, 3, 2, 1, -2};
        System.out.println(test.getKConsistency(prices, 3));
    }

    public int getKConsistency(int[] stockPrices, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < stockPrices.length; ++i) {
            int price = stockPrices[i];
            map.putIfAbsent(price, new ArrayList<>());
            map.get(price).add(i);
        }
        int res = 1;
        for (List<Integer> indexList : map.values()) {
            int curKConsistency = 0;
            int n = indexList.size();
            if (n == 1) {
                continue;
            }
            int left = 0, right = 1;
            int removalCount = indexList.get(right) - indexList.get(left) - 1;
            while (right < n) {
                if (right > left + 1) {
                    removalCount += indexList.get(right) - indexList.get(right - 1) - 1;
                }
                while (removalCount > k) {
                    left++;
                    removalCount -= indexList.get(left) - indexList.get(left - 1) - 1;
                }
                curKConsistency = Math.max(curKConsistency, right - left + 1);
                right++;
            }
            res = Math.max(res, curKConsistency);
        }
        return res;
    }
}
