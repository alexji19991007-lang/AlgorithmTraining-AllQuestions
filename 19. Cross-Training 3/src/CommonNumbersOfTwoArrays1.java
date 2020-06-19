import java.util.*;

public class CommonNumbersOfTwoArrays1 {
    public List<Integer> common(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int x : a) {
            set.add(x);
        }
        for (int y : b) {
            if (set.contains(y)) {
                res.add(y);
            }
        }
        Collections.sort(res);
        return res;
    }
}
