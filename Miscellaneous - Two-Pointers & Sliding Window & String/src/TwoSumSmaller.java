import java.util.Arrays;

public class TwoSumSmaller {
    public int smallerPairs(int[] array, int target) {
        Arrays.sort(array);
        int res = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > target) {
                return res;
            }
            for (int j = i + 1; j < array.length; ++j) {
                int sum = array[i] + array[j];
                if (sum < target) {
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }
}
