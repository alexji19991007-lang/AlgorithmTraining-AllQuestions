package Robinhood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoolFeature {
    public static void main(String[] args) {
        CoolFeature test = new CoolFeature();
        int[] a = {1, 2, 3};
        int[] b = {3, 4};
        int[][] query = {{1, 5}, {0, 0, 1}, {1, 5}};
        System.out.println(test.coolFeature(a, b, query).toString());
    }

    public List<Integer> coolFeature(int[] a, int[] b, int[][] query) {
        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        for (int numA : a) {
            mapA.put(numA, mapA.getOrDefault(numA, 0) + 1);
        }
        for (int numB : b) {
            mapB.put(numB, mapB.getOrDefault(numB, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] q : query) {
            if (q[0] == 0) {
                int prevNum = b[q[1]];
                b[q[1]] = q[2];
                if (prevNum == 1) {
                    mapB.remove(prevNum);
                } else {
                    mapB.put(prevNum, mapB.get(prevNum) - 1);
                }
            } else {
                int target = q[1];
                int count = 0;
                for (int key : mapB.keySet()) {
                    if (mapA.containsKey(target - key)) {
                        count += mapB.get(key) * mapA.get(target - key);
                    }
                }
                res.add(count);
            }
        }
        return res;
    }
}
