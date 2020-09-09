package BestBuyAndSellStock;

public class BestBuyAndSell3 {
    public int maxProfit(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        // leftProfits[i] = the maximum profit if we make transaction between day 0 and i
        int[] leftProfits = new int[array.length];
        // rightProfits[r] = the maximum profit if we make transaction between day r and last day.
        int[] rightProfits = new int[array.length];
        int leftMin = array[0];
        int rightMax = array[array.length - 1];
        for (int i = 1; i < array.length; ++i) {
            leftProfits[i] = Math.max(leftProfits[i - 1], array[i] - leftMin);
            leftMin = Math.min(leftMin, array[i]);
            // where r = array.length - 1 - i
            int r = array.length - 1 - i;
            rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - array[r]);
            rightMax = Math.max(rightMax, array[r]);
        }
        // Example:
        // array:          7 1 5 3 6 4
        // leftProfits:    0 0 4 4 5 5
        //                  \ \ \ \ \
        // rightProfits:   5 5 3 3 0 0
        int maxProfit = 0;
        // we want to divide the array into two parts such that the maxProfit is biggest
        for (int i = 0; i < array.length - 1; ++i) {
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
        }
        // Consider an extra case where we only make one transaction (the last element
        // in leftProfits will pair with another 0).
        maxProfit = Math.max(maxProfit, leftProfits[array.length - 1]);
        return maxProfit;
    }
}
