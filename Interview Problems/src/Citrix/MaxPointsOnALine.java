package Citrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 149
public class MaxPointsOnALine {
    public static void main(String[] args) {
        MaxPointsOnALine test = new MaxPointsOnALine();
        int[][] points = {{0, 0}, {94911151, 94911150}, {94911152, 94911151}};
        System.out.println(test.maxPoints(points));
    }

    public int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            int[] seed = points[i];
            int same = 1, sameX = 0, most = 0;
            Map<List<Integer>, Integer> count = new HashMap<>();
            for (int j = 0; j < points.length; ++j) {
                if (i == j) {
                    continue;
                }
                int[] temp = points[j];
                if (temp[0] == seed[0] && temp[1] == seed[1]) {
                    // Handle points that overlap.
                    same++;
                } else if (temp[0] == seed[0]) {
                    // We cannot handle points with the same x with the logic in the else branch
                    // since denominator cannot be 0.
                    sameX++;
                } else {
                    // We can handle points with the same y using slope.
                    List<Integer> slope = getSlope(temp[1] - seed[1] , temp[0] - seed[0]);
                    count.put(slope, count.getOrDefault(slope, 0) + 1);
                    most = Math.max(most, count.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            res = Math.max(most, res);
        }
        return res;
    }

    // return the irreducible slope by reducing dy and dx
    public List<Integer> getSlope(int dy, int dx){
        if(dx == 0) return Arrays.asList(1, 0);
        if(dy == 0) return Arrays.asList(0, 1);
        int d = gcd(dy, dx);
        return Arrays.asList(dy/d, dx/d);
    }

    // return the most common divisor of m and n
    // Euclidean algorithm
    public int gcd(int m, int n){
        // m % n == remainder
        //     m      n
        // until 2 % 0 = ..., then gcd is 2, i.e. m
        if (n == 0) return m;
        return gcd(n, m % n);
    }
}
