public class F017_SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int aRow = A.length, aCol = A[0].length, bCol = B[0].length;
        int[][] res = new int[aRow][bCol];
        for (int i = 0; i < aRow; ++i) {
            for (int j = 0; j < aCol; ++j) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < bCol; ++k) {
                        // A在第几个column B在第几个row
                        if (B[j][k] != 0) {
                            res[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        return res;
    }
}
