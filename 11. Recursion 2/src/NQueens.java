import java.util.*;

public class NQueens {
    private static boolean[] usedCols;
    private static boolean[] usedDiagonals;
    private static boolean[] usedRevDiagonals;

    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        usedCols = new boolean[n];
        usedDiagonals = new boolean[2 * n - 1];
        usedRevDiagonals = new boolean[2 * n - 1];
        queensHelper(n, 0, solution, res);
        return res;
    }

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
