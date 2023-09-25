package CitadelHackerrank;

public class MathLegoBlock {
    public int mathLegoBlock(int n, int[] rowA, int m, int[] rowB) {
        int sumA = 0, zeroInA = 0;
        int sumB = 0, zeroInB = 0;
        for (int x : rowA) {
            if (x == 0) {
                zeroInA++;
            }
            sumA += x;
        }
        for (int x : rowB) {
            if (x == 0) {
                zeroInB++;
            }
            sumB += x;
        }
        if ((sumA < sumB && zeroInA == 0) || (sumB < sumA && zeroInB == 0) || (sumA == sumB && (zeroInA == 0 || zeroInB == 0))) {
            return -1;
        }
        return Math.max(sumA + zeroInA, sumB + zeroInB);
    }
}
