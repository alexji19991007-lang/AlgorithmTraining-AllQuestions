import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    // This is a simplified version of "longest substring with at most k unique characters"
    public int totalFruit(int[] tree) {
        if (tree.length <= 2) {
            return tree.length;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int start = 0, maxSize = 1;
        count.put(tree[0], 1);
        for (int i = 1; i < tree.length; ++i) {
            count.put(tree[i], count.getOrDefault(tree[i], 0) + 1);
            while (count.size() > 2) {
                int newCount = count.get(tree[start]) - 1;
                if (newCount > 0) {
                    count.put(tree[start], newCount);
                } else {
                    count.remove(tree[start]);
                }
                start++;
            }
            // update the maxSize if necessary
            maxSize = Math.max(i - start + 1, maxSize);
        }
        return maxSize;
    }
}
