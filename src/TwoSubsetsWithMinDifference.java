public class TwoSubsetsWithMinDifference {
    public static void main(String[] args) {
        int[] x = {2,9,3,1,1};
        System.out.println(minDifference(x));
    }

    public static int minDifference(int[] array) {
        return findMinDiff(array, array.length - 1, 0, 0);
    }

    public static int findMinDiff(int[] array, int index, int sum1, int sum2) {
        if (index < 0) {
            return Math.abs(sum1 - sum2);
        }
        int includeInFirst = findMinDiff(array, index - 1, sum1 + array[index], sum2);
        int includeInSecond = findMinDiff(array, index - 1, sum1, sum2 + array[index]);
        return Math.min(includeInFirst, includeInSecond);
    }
}
