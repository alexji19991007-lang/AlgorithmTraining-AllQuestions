// Name: Alex Ji
// VUnetID: jiy3
// Section: 001
// Email: yu.ji@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Date: , 2018
// Honor statement: I attest that I understand the honor code for this class and have neither given 
//                  nor received any unauthorized aid on this assignment.
// Program description: 
public class MinimumFalling {
    public static void main(String[] args) {
        int[][] A = {{-19, 57}, {-40, -5}};
        System.out.println(minFallingPathSum(A));
    }

    public static int minFallingPathSum(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[] res = new int[m];
        for (int[] x : A) {
            int[] temp = new int[m];
            for (int j = 0; j < m; ++j) {
                int TL = j - 1 >= 0 ? res[j - 1] : Integer.MAX_VALUE;
                int T = res[j];
                int TR = j + 1 < n ? res[j + 1] : Integer.MAX_VALUE;
                temp[j] = x[j] + Math.min(TL, Math.min(T, TR));
            }
            res = temp;
        }
        int globalMin = res[0];
        for (int x : res) {
            globalMin = Math.min(x, globalMin);
        }
        return globalMin;
    }
}
