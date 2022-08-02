package Codility;

import java.util.*;

public class AssassinEscape {
    public static void main(String[] args) {
        AssassinEscape test = new AssassinEscape();
        String[] s = {"X.....>", "..v..X.", ".>..X..", "A......"};
        String[] s1 = {"...Xv", "AX..^", ".XX.."};
        System.out.println(test.canEscape(s1));
    }

    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean canEscape(String[] s) {
        Board game = new Board(s);
        //game.print();
        if (!game.canGo) {
            return false;
        }
        boolean[][] visited = new boolean[game.n][game.m];
        Queue<int[]> queue = new ArrayDeque<>();
        int startI = game.assassin[0], startJ = game.assassin[1];
        visited[startI][startJ] = true;
        queue.offer(new int[]{startI, startJ});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curI = cur[0], curJ = cur[1];
            if (curI == game.n - 1 && curJ == game.m - 1) {
                return true;
            }
            for (int[] dir : DIRS) {
                int nextI = curI + dir[0], nextJ = curJ + dir[1];
                if (isValidStep(nextI, nextJ, visited, game)) {
                    queue.offer(new int[] {nextI, nextJ});
                    visited[nextI][nextJ] = true;
                }
            }
        }
        return false;
    }

    private boolean isValidStep(int i, int j, boolean[][] visited, Board game) {
        return i >= 0 && i < game.n && j >= 0 && j < game.m && !visited[i][j] && game.board[i][j] == '.';
    }

    static class Board {
        int[] assassin;
        char[][] board;
        int n;
        int m;
        boolean canGo;

        public Board(String[] array) {
            this.assassin = new int[2];
            this.board = new char[array.length][array[0].length()];
            this.n = array.length;
            this.m = array[0].length();
            this.canGo = true;
            processBoard(array);
        }

        private void processBoard(String[] array) {
            char exitChar = array[n - 1].charAt(m - 1);
            Set<Character> guardSet = new HashSet<>(Arrays.asList('^', '>', '<', 'v'));
            if (exitChar == 'X' || guardSet.contains(exitChar)) {
                canGo = false;
                return;
            }
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    char curChar = array[i].charAt(j);
                    if (curChar == 'X') {
                        board[i][j] = 'X';
                        continue;
                    }
                    if (curChar == 'A') {
                        board[i][j] = curChar;
                        assassin[0] = i;
                        assassin[1] = j;
                        continue;
                    }
                    if (curChar == '.' && board[i][j] != 'Y') {
                        board[i][j] = '.';
                    }
                    if (curChar == '^') {
                        board[i][j] = curChar;
                        int curI = i - 1;
                        while (curI >= 0 && (array[curI].charAt(j) == '.' || array[curI].charAt(j) == 'Y')) {
                            if (array[curI].charAt(j) == 'A') {
                                canGo = false;
                                return;
                            }
                            board[curI][j] = 'Y';
                            curI--;
                        }
                    } else if (curChar == '>') {
                        board[i][j] = curChar;
                        int curJ = j + 1;
                        while (curJ < m && (array[i].charAt(curJ) == '.' || array[i].charAt(curJ) == 'Y')) {
                            if (array[i].charAt(curJ) == 'A' || (i == n - 1 && curJ == m - 1)) {
                                canGo = false;
                                return;
                            }
                            board[i][curJ] = 'Y';
                            curJ++;
                        }
                    } else if (curChar == 'v') {
                        board[i][j] = curChar;
                        int curI = i + 1;
                        while (curI < n && (array[curI].charAt(j) == '.' || array[curI].charAt(j) == 'Y')) {
                            if (array[curI].charAt(j) == 'A' || (curI == n - 1 && j == m - 1)) {
                                canGo = false;
                                return;
                            }
                            board[curI][j] = 'Y';
                            curI++;
                        }
                    } else if (curChar == '<'){
                        board[i][j] = curChar;
                        int curJ = j - 1;
                        while (curJ >= 0 && (array[i].charAt(curJ) == '.' || array[i].charAt(curJ) == 'Y')) {
                            if (array[i].charAt(curJ) == 'A' || (i == n - 1 && curJ == m - 1)) {
                                canGo = false;
                                return;
                            }
                            board[i][curJ] = 'Y';
                            curJ--;
                        }
                    }
                }
            }
        }

        public void print() {
            for (char[] array : board) {
                for (char x : array) {
                    System.out.print(x);
                }
                System.out.println();
            }
        }
    }
}
