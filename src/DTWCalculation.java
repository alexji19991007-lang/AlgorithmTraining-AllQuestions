import java.util.Arrays;

public class DTWCalculation {
    public static void main(String[] args) {
        int[] signalA = {3, 2, 0, 1, 4, 5, 6, 7, 2, 2, 1};
        int[] signalB = {4, 2, 1, 0, 5, 5, 7, 7, 3, 2, 1};
        int[] signalC = {5, 3, 1, 7, 8, 6, 9, 8, 6, 3, 2};
        int[] path1 = {1, 1, 2, 3, 4, 4, 5, 5, 6, 6, 6};
        int[] path2 = {1, 2, 2, 3, 5, 8, 9, 11, 12, 13, 13, 13, 14};
        int[] path3 = {2, 3, 4, 4, 7, 10, 10, 12, 13, 14, 15, 15, 16};
        int[] path4 = {2, 3, 4, 4, 7, 10, 10, 12, 13, 14, 15, 15, 16};
//        calculateMatrix(signalA, signalB);
//        calculateMatrix(signalB, signalC);
//        calculateDistance(path1);
//        calculateDistance(path2);
//        calculateMatrix(signalA, signalC);
//        calculateDistance(path3);
        calculateDistance(path4);
    }

    public static void calculateMatrix(int[] signalA, int[] signalB) {
        int[][] res = new int[signalA.length][signalB.length];
        for (int i = 0; i < signalB.length; ++i) {
            for (int j = 0; j < signalA.length; ++j) {
                int absDiff = Math.abs(signalB[i] - signalA[j]);
                int leftNum = i - 1 >= 0 ? res[j][i - 1] : Integer.MAX_VALUE;
                int upperNum = j - 1 >= 0 ? res[j - 1][i] : Integer.MAX_VALUE;
                int upperLeftNum = i - 1 >= 0 && j - 1 >= 0 ? res[j - 1][i - 1] : Integer.MAX_VALUE;
                int add = Math.min(leftNum, Math.min(upperLeftNum, upperNum));
                add = add != Integer.MAX_VALUE ? add : 0;
                res[j][i] = absDiff + add;
            }
        }
        for (int[] a : res) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static void calculateDistance(int[] path) {
        int sum = 0;
        for (int i = 0; i < path.length - 1; ++i) {
            int diff = path[i] - path[i + 1];
            sum += (diff * diff);
        }
        System.out.println(sum);
    }
}
