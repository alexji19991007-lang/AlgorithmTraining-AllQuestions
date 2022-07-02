import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// LeetCode 2034
public class StockPriceFluctuation {
    // TC: O(N*log(N))
    // SC: O(N)
    int latestTime;
    Map<Integer, Integer> timeStampToPrice;
    TreeMap<Integer, Integer> priceToFrequency;

    public StockPriceFluctuation() {
        latestTime = 0;
        timeStampToPrice = new HashMap<>();
        priceToFrequency = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(timestamp, latestTime);
        if (timeStampToPrice.containsKey(timestamp)) {
            int oldPrice = timeStampToPrice.get(timestamp);
            priceToFrequency.put(oldPrice, priceToFrequency.get(oldPrice) - 1);
            if (priceToFrequency.get(oldPrice) == 0) {
                priceToFrequency.remove(oldPrice);
            }
        }
        timeStampToPrice.put(timestamp, price);
        priceToFrequency.put(price, priceToFrequency.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timeStampToPrice.get(latestTime);
    }

    public int maximum() {
        return priceToFrequency.lastKey();
    }

    public int minimum() {
        return priceToFrequency.firstKey();
    }
}
