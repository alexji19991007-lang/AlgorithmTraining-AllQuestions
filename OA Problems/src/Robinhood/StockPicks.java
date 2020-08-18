package Robinhood;

import java.util.*;

public class StockPicks {
    public String[] pickOfTheWeek(String[] picks) {
        if (picks == null || picks.length == 0) {
            return new String[0];
        }
        Map<String, Integer> stockPicks = new HashMap<>();
        Set<String> userIds = new HashSet<>();
        TreeMap<Integer, String> rs = new TreeMap<>();
        for (String pick : picks) {
            String[] data = pick.split(",");
            String stockLabel = data[1].trim();
            String userId = data[0].trim();
            if (userIds.contains(userId)) {
                continue;
            }
            if (!stockPicks.containsKey(stockLabel)) {
                stockPicks.put(stockLabel, 0);
            }
            stockPicks.put(stockLabel, stockPicks.get(stockLabel) + 1);
            userIds.add(userId);
        }
        for (Map.Entry<String, Integer> stock : stockPicks.entrySet()) {
            rs.put(stock.getValue(), stock.getKey());
            if (rs.size() >= 6) {
                rs.pollFirstEntry();
            }
        }
        String[] result = new String[rs.size()];
        int index = 0;
        for (Map.Entry<Integer, String> stock : rs.descendingMap().entrySet()) {
            result[index] = stock.getValue() + "," + stock.getKey();
            index++;
        }
        return result;
    }
}
