package HackerRank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BirthdayCardCollection {
    public List<Integer> hackerCards(List<Integer> collection, int d) {
        Set<Integer> alreadyHave = new HashSet<>(collection);
        List<Integer> res = new ArrayList<>();
        int totalCost = 0, i = 1;
        while (totalCost + i <= d) {
            if (!alreadyHave.contains(i)) {
                res.add(i);
                alreadyHave.add(i);
                totalCost += i;
            }
            i++;
        }
        return res;
    }
}
