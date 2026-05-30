import java.util.*;

public class NQueens {
    private boolean[] usedCols;
    // 正对角线↙：usedDiagonals[row + col]
    // (0,0) → 0
    // (1,1) → 2
    // (2,2) → 4
    // 同一条↙对角线，row + col 相同
    private boolean[] usedDiagonals;
    // 反对角线↘：usedRevDiagonals[col - row + n - 1]
    // (0,2) → 2 → 5
    // (1,1) → 0 → 3
    // (2,0) → -2 → 1
    // 原本是 col - row（会有负数）
    // 所以：+ (n - 1)，变成非负 index
    private boolean[] usedRevDiagonals;

    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        usedCols = new boolean[n];
        usedDiagonals = new boolean[2 * n - 1];
        usedRevDiagonals = new boolean[2 * n - 1];
        queensHelper(n, 0, solution, res);
        return res;
    }

    // 这种recursion已经“天然保证”了一件事：
    // 每一行只放一个皇后，因为 recursion 是按 row 一行一行往下走的
    public void queensHelper(int n, int row, List<Integer> solution, List<List<Integer>> res) {
        if (row == n) {
            res.add(new ArrayList<>(solution));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (isValid(n, row, i)) {
                // Do
                mark(n, row, i, true);
                solution.add(i);
                // Recursive call
                queensHelper(n, row + 1, solution, res);
                // Undo
                mark(n, row, i, false);
                solution.remove(solution.size() - 1);
            }
        }
    }

    public boolean isValid(int n, int row, int col) {
        return !usedCols[col] && !usedDiagonals[row + col] && !usedRevDiagonals[col - row + n - 1];
    }

    public void mark(int n, int row, int col, boolean toWhat) {
        usedCols[col] = toWhat;
        usedDiagonals[row + col] = toWhat;
        usedRevDiagonals[col - row + n - 1] = toWhat;
    }
}
