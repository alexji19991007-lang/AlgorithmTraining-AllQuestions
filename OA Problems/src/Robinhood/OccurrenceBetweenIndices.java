package Robinhood;

public class OccurrenceBetweenIndices {
    public static void main(String[] args) {
        OccurrenceBetweenIndices test = new OccurrenceBetweenIndices();
        int[] a = {1, 1, 2, 2, 3, 3, 1, 2, 3, 4};
        int[][] queries = {{0, 6, 2}};
        System.out.println(test.occurrenceBetween(a, queries));
    }

    public int occurrenceBetween(int[] a, int[][] queries) {
        int[][] cumulativeOccur = new int[101][a.length + 1];
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < 101; ++j) {
                cumulativeOccur[j][i + 1] = cumulativeOccur[j][i];
            }
            cumulativeOccur[a[i]][i + 1]++;
        }
        int res = 0;
        for (int[] query : queries) {
            int i = query[0], j = query[1], target = query[2];
            res += cumulativeOccur[target][j + 1] - cumulativeOccur[target][i];
        }
        return res;
    }
}
