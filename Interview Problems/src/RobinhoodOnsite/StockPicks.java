package RobinhoodOnsite;

import java.util.*;

public class StockPicks {
    public static void main(String[] args) {
        StockPicks test = new StockPicks();
        String[] picks = {"1,A", "2,B", "3,C", "4,D", "5,A", "6,A", "7,A", "1,B", "8,B", "9,B", "10, B"};
        System.out.println(Arrays.toString(test.pickOfTheWeek(picks)));
    }

    public String[] pickOfTheWeek(String[] picks) {
        if (picks == null || picks.length == 0) {
            return new String[0];
        }
        Map<String, Integer> stockPicks = new HashMap<>();
        Set<String> userIds = new HashSet<>();
        Queue<Map.Entry<String, Integer>> rs = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue().equals(e2.getValue())) {
                return 0;
            }
            return e1.getValue() > e2.getValue() ? -1 : 1;
        });
        for (String pick : picks) {
            String[] data = pick.split(",");
            String stockLabel = data[1].trim();
            String userId = data[0].trim();
            if (userIds.contains(userId)) {
                continue;
            }
            stockPicks.put(stockLabel, stockPicks.getOrDefault(stockLabel, 0) + 1);
            userIds.add(userId);
        }
        for (Map.Entry<String, Integer> stock : stockPicks.entrySet()) {
            rs.offer(stock);
            if (rs.size() > 2) {
                rs.poll();
            }
        }
        String[] result = new String[rs.size()];
        int index = 0;
        while (!rs.isEmpty()) {
            Map.Entry<String, Integer> entry = rs.poll();
            result[index++] = entry.getKey() + "," + entry.getValue();
        }
        return result;
    }
}
