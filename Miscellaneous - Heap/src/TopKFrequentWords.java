import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    public String[] topKFrequent(String[] combo, int k) {
        if (combo.length == 0) {
            return new String[0];
        }
        Map<String, Integer> freq = new HashMap<>();
        getFreq(combo, freq);
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return changeToArray(minHeap);
    }

    public void getFreq(String[] combo, Map<String, Integer> freq) {
        for (String x : combo) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
    }

    public String[] changeToArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
        String[] res = new String[minHeap.size()];
        int index = res.length - 1;
        while (!minHeap.isEmpty()) {
            res[index--] = minHeap.poll().getKey();
        }
        return res;
    }
}
