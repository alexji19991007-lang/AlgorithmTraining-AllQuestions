package Robinhood;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MatrixQueries {
    public long[] matrixQueries(int n, int m, int[][] queries) {
        Set<Integer> deactiveRows = new HashSet<>();
        Set<Integer> deactiveCols = new HashSet<>();
        ArrayList<Long> outputList = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 0) {
                long minRow = findMin(deactiveRows, n);
                long minCol = findMin(deactiveCols, m);
                outputList.add(minRow * minCol);
            } else if (query[0] == 1) {
                deactiveRows.add(query[1]);
            } else if (query[0] == 2) {
                deactiveCols.add(query[1]);
            }
        }
        long[] resArr = new long[outputList.size()];
        for(int i = 0; i < outputList.size(); i++){
            resArr[i] = outputList.get(i);
        }
        return resArr;
    }

    public long findMin(Set<Integer> set, int n){
        for(int i = 1; i <= n; i++){
            if(!set.contains(i)) return i;
        }
        return 0L;
    }
}
