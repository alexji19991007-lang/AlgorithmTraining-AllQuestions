import java.util.*;

public class TopKFrequentWords {
    public String[] topKFrequent(String[] combo, int k) {
        if (combo.length == 0 || k == 0) {
            return new String[0];
        }
        Map<String, Integer> freq = getWordFreq(combo);
        Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (minHeap.peek().getValue() < entry.getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return toArray(minHeap);
    }

    public Map<String, Integer> getWordFreq(String[] combo) {
        Map<String, Integer> freq = new HashMap<>();
        for (String str : combo) {
            freq.put(str, freq.getOrDefault(str, 0) + 1);
        }
        return freq;
    }

    public String[] toArray(Queue<Map.Entry<String, Integer>> minHeap) {
        int size = minHeap.size();
        String[] res = new String[size];
        for (int i = size - 1; i >= 0; --i) {
            res[i] = minHeap.poll().getKey();
        }
        return res;
    }
}
