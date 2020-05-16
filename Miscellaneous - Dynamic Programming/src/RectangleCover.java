// 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
// 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，
// 总共有多少种方法？

public class RectangleCover {
    public int rectangleCover(int n) {
        int[] numWays = new int[n];
        numWays[0] = 1;
        numWays[1] = 2;
        for (int i = 3; i < n; ++i) {
            numWays[i] = numWays[i - 1] + numWays[i - 2];
        }
        return numWays[n - 1];
    }
}
