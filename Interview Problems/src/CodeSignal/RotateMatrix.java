package CodeSignal;

public class RotateMatrix {
    public static void main(String[] args) {
        RotateMatrix instance = new RotateMatrix();
        int[][] M = {{1, 2, 3, 4, 5},
                {5, 6, 7, 8, 9},
                {9, 10, 11, 12, 13},
                {13, 14, 15, 16, 17},
                {17, 18, 19, 20, 21}};
        instance.rotate(M, 1);
        instance.printM(M);
        System.out.println();
        int[][] M1 = {{1, 2, 3, 4, 5},
                {5, 6, 7, 8, 9},
                {9, 10, 11, 12, 13},
                {13, 14, 15, 16, 17},
                {17, 18, 19, 20, 21}};
        instance.rotate(M1, 2);
        instance.printM(M1);
        System.out.println();
        int[][] M2 = {{1, 2, 3, 4, 5},
                {5, 6, 7, 8, 9},
                {9, 10, 11, 12, 13},
                {13, 14, 15, 16, 17},
                {17, 18, 19, 20, 21}};
        instance.rotate(M2, 3);
        instance.printM(M2);
    }

    public void rotate(int[][] M, int n) {
        if (n == 1) {
            // rotate 90
            rotate90(M);
        } else if (n == 2) {
            // rotate 180
            rotate180(M);
        } else if (n == 3) {
            // rotate 270
            rotate270(M);
        } else {
            System.out.print("invalid n value");
        }
    }

    public void rotate90(int[][] M) {
        int n = M.length;
        if (n <= 1) {
            return;
        }
        int round = n / 2;
        for (int level = 0; level < round; ++level) {
            int left = level;
            int right = n - 2 - level;
            for (int i = left; i <= right; ++i) {
                if (left == i) {
                    continue;
                }
                int temp = M[left][i];
                M[left][i] = M[n - 1 - i][left];
                M[n - 1 - i][left] = M[n - 1 - left][n - 1 - i];
                M[n - 1 - left][n - 1 - i] = M[i][n - 1 - left];
                M[i][n - 1 - left] = temp;
            }
        }
//        int n = M.length;
//        int[][] res = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i + j == n - 1 || j == i) {
//                    // diagonal and anti-diagonal
//                    res[i][j] = M[i][j];
//                } else {
//                    res[j][n - 1 - i] = M[i][j];
//                }
//            }
//        }
//        return res;
    }

    public void rotate180(int[][] M) {
        int n = M.length;
        if (n <= 1) {
            return;
        }
        int round = n / 2;
        for (int level = 0; level < round; ++level) {
            int left = level;
            int right = n - 2 - level;
            for (int i = left; i <= right; ++i) {
                if (left == i) {
                    continue;
                }
                int temp = M[left][i];
                M[left][i] = M[n - 1 - left][n - 1 - i];
                M[n - 1 - left][n - 1 - i] = temp;
                temp = M[i][n - 1 - left];
                M[i][n - 1 - left] = M[n - 1 - i][left];
                M[n - 1 - i][left] = temp;
            }
        }
//        int n = M.length;
//        int[][] res = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i + j == n - 1 || j == i) {
//                    // diagonal and anti-diagonal
//                    res[i][j] = M[i][j];
//                } else {
//                    res[n - 1 - i][n - 1 - j] = M[i][j];
//                }
//            }
//        }
//        return res;
    }

    public void rotate270(int[][] M) {
        int n = M.length;
        if (n <= 1) {
            return;
        }
        int round = n / 2;
        for (int level = 0; level < round; ++level) {
            int left = level;
            int right = n - 2 - level;
            for (int i = left; i <= right; ++i) {
                if (left == i) {
                    continue;
                }
                int temp = M[left][i];
                M[left][i] = M[i][n - 1 - left];
                M[i][n - 1 - left] = M[n - 1 - left][n - 1 - i];
                M[n - 1 - left][n - 1 - i] = M[n - 1 - i][left];
                M[n - 1 - i][left] = temp;
            }
        }
//        int n = M.length;
//        int[][] res = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i + j == n - 1 || j == i) {
//                    // diagonal and anti-diagonal
//                    res[i][j] = M[i][j];
//                } else {
//                    res[n - 1 - j][i] = M[i][j];
//                }
//            }
//        }
//        return res;
    }

    public void printM(int[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}
