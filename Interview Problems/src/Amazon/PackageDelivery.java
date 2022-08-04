package Amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageDelivery {
    public static void main(String[] args) {
        PackageDelivery test = new PackageDelivery();
        List<Integer> weights = Arrays.asList(2, 4, 6, 6, 4);
        System.out.println(test.minTimes(weights));
    }

    public int minTimes(List<Integer> weights) {
        Map<Integer, Integer> countWeights = new HashMap<>();
        for (int w : weights) {
            countWeights.put(w, countWeights.getOrDefault(w, 0) + 1);
        }
        int totalTimes = 0;
        for (Map.Entry<Integer, Integer> entry : countWeights.entrySet()) {
            int count = entry.getValue();
            if (count <= 1) {
                return -1;
            }
            int remainingWeightIfAllThree = count % 3;
            int times = (remainingWeightIfAllThree == 0 ? 0 : 1) + count / 3;
            totalTimes += times;
        }
        return totalTimes;
    }
}
