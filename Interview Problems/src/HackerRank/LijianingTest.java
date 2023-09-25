package HackerRank;

public class LijianingTest {
    public static void main(String[] args) {
        System.out.println(getMinimumCost(new int[]{1, 3, 5, 2, 10}));
    }

    public static int getMinimumCost(int[] arr) {
        int maxLeft = 0, maxRight = 0;
        int maxDiff = 0;
        int diffSquareSum = 0;
        for (int i = 1; i < arr.length; ++i) {
            int diff = Math.abs(arr[i] - arr[i - 1]);
            diffSquareSum += diff * diff;
            if (diff > maxDiff) {
                maxDiff = diff;
                maxRight = i;
                maxLeft = i - 1;
            }
        }
        int numberToBeInserted = (arr[maxRight] + arr[maxLeft]) / 2;
        int leftDiff = numberToBeInserted - arr[maxLeft];
        int rightDiff = arr[maxRight] - numberToBeInserted;
        return diffSquareSum - maxDiff * maxDiff + leftDiff * leftDiff + rightDiff * rightDiff;
    }
}
