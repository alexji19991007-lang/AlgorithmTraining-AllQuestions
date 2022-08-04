package Amazon;

import java.util.*;

public class TeamInefficiency {
    public static void main(String[] args) {
        TeamInefficiency test = new TeamInefficiency();
        List<Integer> efficiencies = Arrays.asList(1, 1, 1, 2, 2, 2, 3, 4, 5);
        System.out.println(test.lowestInefficiencies(efficiencies, 11));
    }

    public List<Integer> lowestInefficiencies(List<Integer> efficiencies, int k) {
        if (efficiencies.size() <= 1 || k == 0) {
            return new ArrayList<>();
        }
        efficiencies.sort(Comparator.naturalOrder());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> {
            if (i1.equals(i2)) {
                return 0;
            }
            return i1 > i2 ? -1 : 1;
        });
        for (int i = 1; i < efficiencies.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                int diff = efficiencies.get(i) - efficiencies.get(j);
                if (maxHeap.size() < k) {
                    maxHeap.offer(diff);
                } else if (maxHeap.peek() > diff){
                    maxHeap.poll();
                    maxHeap.offer(diff);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = k; i >= 0; --i) {
            if (maxHeap.isEmpty()) {
                break;
            }
            res.add(maxHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
