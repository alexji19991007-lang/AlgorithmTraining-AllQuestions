import java.util.ArrayList;
import java.util.List;

// LeetCode 1492
public class TheKthFactorOfN {
    public int kthFactor(int n, int k) {
        List<Integer> factors = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrt; ++i) {
            if (n % i == 0) {
                factors.add(i);
                k--;
                if (k == 0) {
                    return i;
                }
            }
        }
        if (sqrt * sqrt == n) {
            k++;
        }
        return k > factors.size() ? -1 : n / factors.get(factors.size() - k);
    }
}
