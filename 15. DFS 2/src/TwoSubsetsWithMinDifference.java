public class TwoSubsetsWithMinDifference {
    public int minDifference(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        int[] diff = new int[]{Integer.MAX_VALUE};
        helper(array, 0, sum, 0, 0, diff);
        return diff[0];
    }

    public void helper(int[] array, int index, int sum, int size, int cur, int[] diff) {
        if (size == array.length / 2) {
            diff[0] = Math.min(diff[0], Math.abs(sum - cur - cur));
            return;
        }
        if (index == array.length) {
            return;
        }
        helper(array, index + 1, sum, size + 1, cur + array[index], diff);
        helper(array, index + 1, sum, size, cur, diff);
    }
}
