import java.util.*;

public class CommonNumbersOfTwoSortedArrays {
    public List<Integer> common(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : A) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        for (int x : B) {
            if (freq.containsKey(x) && freq.get(x) > 0) {
                res.add(x);
                freq.put(x, freq.get(x) - 1);
            }
        }
        return res;
    }
}
