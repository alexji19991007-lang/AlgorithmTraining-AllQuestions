import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test2 {
    public static void main(String[] args) {
        int[][][] memo = new int[3][3][3];
        for (int[][] dimension : memo) {
            for (int[] row : dimension) {
                Arrays.fill(row, -1);
            }
        }
        for (int[][] dimension : memo) {
            for (int[] row : dimension) {
                System.out.println(Arrays.toString(row));
            }
        }
    }
}
