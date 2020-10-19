package RobinhoodOnsite;

import java.util.Map;

public class MaxMinFirstLastPrice {
    public static void main(String[] args) {
        MaxMinFirstLastPrice test = new MaxMinFirstLastPrice();
        String input = "3:12,1:15,4:18,1:30,5:40,9:47,2:101,6:103,5:105,3:107,5:108,8:120,9:121,7:122,9:124,3:125,2:126,3:127,8:128,4:129";
        System.out.println(test.aggregate_prices(input));
    }

    String aggregate_prices(String prices_to_parse) {
        String[] prices = prices_to_parse.split(",");
        int[][] priceInfo = new int[prices.length][2];
        for (int i = 0; i < prices.length; ++i) {
            String curPrice = prices[i];
            String[] curPriceArray = curPrice.split(":");
            priceInfo[i][0] = Integer.parseInt(curPriceArray[1]);
            priceInfo[i][1] = Integer.parseInt(curPriceArray[0]);
        }
        StringBuilder res = new StringBuilder();
        int[] timeInterval = {0, 9};
        int endSignal = priceInfo[priceInfo.length - 1][0];
        String prevAns = "";
        while (timeInterval[0] < endSignal) {
            String ans = findMaxMinFirstLastPrice(priceInfo, timeInterval);
            if (ans.equals("") && !prevAns.equals("")) {
                int firstComma = prevAns.indexOf(",");
                String curAns = "{" + timeInterval[0] + prevAns.substring(firstComma);
                res.append(curAns);
            } else {
                res.append(ans);
                prevAns = ans;
            }
            timeInterval[0] = timeInterval[1] + 1;
            timeInterval[1] = timeInterval[0] + 9;
        }
        return res.toString();
    }


    // timeInterval = int[2];
// timeInterval[0] = startTime;
// timeInterval[1] = endTime;
    public String findMaxMinFirstLastPrice(int[][] prices, int[] timeInterval) {
        StringBuilder res = new StringBuilder();
        // 1. Find the first day that is just >= timeInterval[0]
        int firstIndex = findFirst(prices, timeInterval[0]);
        if (firstIndex == -1) {
            return "";
        }
        res.append("{").append(timeInterval[0]).append(",");
        int firstPrice = prices[firstIndex][1];
        res.append(firstPrice).append(",");
        // 2. Find the day that is just <= timeInterval[1]
        int lastIndex = findLast(prices, timeInterval[1], firstIndex);
        if (lastIndex == -1) {
            return "";
        }
        int lastPrice = prices[lastIndex][1];
        res.append(lastPrice).append(",");
        // 3. Do the max and min
        int maxPrice = Integer.MIN_VALUE, minPrice = Integer.MAX_VALUE;
        for (int i = firstIndex; i <= lastIndex; ++i) {
            maxPrice = Math.max(maxPrice, prices[i][1]);
            minPrice = Math.min(minPrice, prices[i][1]);
        }
        res.append(maxPrice).append(",").append(minPrice).append("}");
        return res.toString();
    }

    public int findFirst(int[][] prices, int target) {
        int left = 0, right = prices.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (prices[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (prices[left][0] >= target) {
            return left;
        } else if (prices[right][0] >= target) {
            return right;
        }
        return -1;
    }

    public int findLast(int[][] prices, int target, int left) {
        int right = prices.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (prices[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (prices[right][0] <=target) {
            return right;
        } else if (prices[left][0] <= target) {
            return left;
        }
        return -1;
    }
}
