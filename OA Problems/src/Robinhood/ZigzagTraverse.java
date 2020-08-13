package Robinhood;

public class ZigzagTraverse {
    public static void main(String[] args){
        ZigzagTraverse instance = new ZigzagTraverse();
        int M[] = {1,  2,  3,  4, 5,  6,  7,  8, 9, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20};
        int[][] res = instance.print(M, 5, 4);
        instance.print(res);
    }

    public int[][] print(int[] M, int row, int col){
        int[][] res = new int[row][col];
        int idx = 0;
        for(int line = row + col - 1; line > 0; line--){
            int start_col = Math.max(0, line - row);
            int line_count = Math.min(Math.min(line, col - start_col), row);
            for(int j = 0; j < line_count; j++){
                res[Math.min(row, line) - j - 1][start_col + j] = M[idx];
                idx++;
            }
        }
        return res;
    }

    public void print(int[][] M){
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M[0].length; j++){
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}
