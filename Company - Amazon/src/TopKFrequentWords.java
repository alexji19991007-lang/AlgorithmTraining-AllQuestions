import java.util.*;

// LeetCode 692
public class TopKFrequentWords {
    public static void main(String[] args) {
        TopKFrequentWords test = new TopKFrequentWords();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(test.topKFrequent(words, 2));
    }

    // TC: klogk + (n - k)logk + klogk = O((n + k)logk)
    // SC: O(k)
    public List<String> topKFrequent(String[] words, int k) {
        if (words.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> freq = getWordFreq(words);
        Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, (e1, e2) -> {
            if (e1.getValue() < e2.getValue()) {
                return -1;
            } else if (e1.getValue() > e2.getValue()) {
                return 1;
            } else {
                return e2.getKey().compareTo(e1.getKey());
            }
        });
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (minHeap.peek().getValue() < entry.getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            } else if (minHeap.peek().getValue().equals(entry.getValue())) {
                if (minHeap.peek().getKey().compareTo(entry.getKey()) > 0) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }
        int size = minHeap.size();
        String[] res = new String[size];
        for (int i = size - 1; i >= 0; --i) {
            res[i] = minHeap.poll().getKey();
        }
        return Arrays.asList(res);
    }

    public Map<String, Integer> getWordFreq(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String str : words) {
            freq.put(str, freq.getOrDefault(str, 0) + 1);
        }
        return freq;
    }
}
