package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimalUtilization {
    public List<List<Integer>> applicationPairs(int deviceCapacity, List<List<Integer>> foregroundAppList, List<List<Integer>> backgroundAppList) {
        foregroundAppList.sort((l1, l2) -> l1.get(1) - l2.get(2));
        backgroundAppList.sort((l1, l2) -> l1.get(1) - l2.get(2));
        List<List<Integer>> res = new ArrayList<>();
        int maxUtilization = 0;
        int m = foregroundAppList.size(), n = backgroundAppList.size();
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            int foregroundUse = foregroundAppList.get(i).get(1);
            int backgroundUse = backgroundAppList.get(j).get(1);
            int curUtilization = foregroundUse + backgroundUse;
            if (curUtilization > deviceCapacity) {
                j--;
            } else {
                if (maxUtilization < curUtilization) {
                    maxUtilization = curUtilization;
                    res.clear();
                }
                res.add(Arrays.asList(foregroundAppList.get(i).get(0), backgroundAppList.get(j).get(0)));
                int index = j - 1;
                while (index >= 0 && backgroundUse == backgroundAppList.get(index).get(1)) {
                    res.add(Arrays.asList(foregroundAppList.get(i).get(0), backgroundAppList.get(index--).get(0)));
                }
            }
            i++;
        }
        return res;
    }
}
