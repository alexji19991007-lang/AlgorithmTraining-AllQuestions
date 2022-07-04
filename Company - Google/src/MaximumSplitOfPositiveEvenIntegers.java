import java.util.ArrayList;
import java.util.List;

// LeetCode 2178
public class MaximumSplitOfPositiveEvenIntegers {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> res = new ArrayList<>();
        if (finalSum % 2 == 0) {
            long i = 2;
            while (finalSum - i >= 0) {
                res.add(i);
                finalSum -= i;
                i += 2;
            }
            res.set(res.size() - 1, res.get(res.size() - 1) + finalSum);
        }
        return res;
    }
}
