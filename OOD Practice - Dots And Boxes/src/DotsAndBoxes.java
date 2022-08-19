public class DotsAndBoxes {
    private final int rows;
    private final int cols;
    // Horizontal Lines
    private final int[][] rowStatus;

    // Vertical Lines
    private final int[][] colStatus;
    private int curTurn;
    private int playerOneScore;
    private int playerTwoScore;

    public DotsAndBoxes(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.rowStatus = new int[rows][cols - 1];
        this.colStatus = new int[rows - 1][cols];
        this.curTurn = 1;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
    }

    public void connect(int r1, int c1, int r2, int c2) {
        if (!(inBound(r1, c1) && inBound(r2, c2) && isValidConnection(r1, c1, r2, c2))) {
            System.out.println("Invalid Connection Between {" + r1 + ", " + c1 + "} and {" + r2 + ", " + c2 + "}");
            return;
        }
        int tempRMax = Math.max(r1, r2), tempRMin = Math.min(r1, r2);
        int tempCMax = Math.max(c1, c2), tempCMin = Math.min(c1, c2);
        r1 = tempRMin;
        r2 = tempRMax;
        c1 = tempCMin;
        c2 = tempCMax;
        System.out.println("Connecting Points {" + r1 + ", " + c1 + "} and {" + r2 + ", " + c2 + "}");
        if (r2 - r1 == 1 && c1 == c2) {
            // Vertical Line
            colStatus[r1][c1] = curTurn;
            curTurn = checkLeftRightSquare(r1, c1, r2, c2) ? curTurn : -curTurn;
        } else if (c2 - c1 == 1 && r1 == r2) {
            // Horizontal Line
            rowStatus[r1][c1] = curTurn;
            curTurn = checkUpperBottomSquare(r1, c1, r2, c2) ? curTurn : -curTurn;
        }
    }

    public void checkWinner() {
        System.out.println("The Scoreboard: ");
        System.out.println("Player 1: " + playerOneScore);
        System.out.println("Player 2: " + playerTwoScore);
        if (playerOneScore > playerTwoScore) {
            System.out.println("Player 1 wins");
        } else if (playerOneScore < playerTwoScore) {
            System.out.println("Player 2 wins");
        } else {
            System.out.println("The game is draw");
        }
    }

    private boolean checkLeftRightSquare(int r1, int c1, int r2, int c2) {
        int r0 = r1;
        int r3 = r2;
        // Check Left Square
        int c0 = c1 - 1;
        int c3 = c2 - 1;
        boolean hasScored = false;
        if (c0 >= 0 && c3 >= 0) {
            // Upper Horizontal Line
            int sideOne = rowStatus[r0][c0];
            // Lower Horizontal Line
            int sideTwo = rowStatus[r3][c3];
            // Left Vertical Line
            int sideThree = colStatus[r0][c0];
            if (sideOne != 0 && sideTwo != 0 && sideThree != 0) {
                System.out.println("Square formed with upper-left vertex {" + r0 + ", " + c0 + "}");
                if (curTurn == 1) {
                    System.out.println("Player One earned one point");
                    playerOneScore++;
                } else {
                    System.out.println("Player Two earned one point");
                    playerTwoScore++;
                }
                hasScored = true;
            }
        }
        // Check Right Square
        c0 = c1 + 1;
        c3 = c2 + 1;
        if (c0 < cols && c3 < cols) {
            // Upper Horizontal Line
            int sideOne = rowStatus[r1][c1];
            // Lower Horizontal Line
            int sideTwo = rowStatus[r2][c2];
            // Right Vertical Line
            int sideThree = colStatus[r0][c0];
            if (sideOne != 0 && sideTwo != 0 && sideThree != 0) {
                System.out.println("Square formed with upper-left vertex {" + r1 + ", " + c1 + "}");
                if (curTurn == 1) {
                    System.out.println("Player One earned one point");
                    playerOneScore++;
                } else {
                    System.out.println("Player Two earned one point");
                    playerTwoScore++;
                }
                hasScored = true;
            }
        }
        return hasScored;
    }

    private boolean checkUpperBottomSquare(int r1, int c1, int r2, int c2) {
        int c0 = c1;
        int c3 = c2;
        // Check Upper Square
        int r0 = r1 - 1;
        int r3 = r2 - 1;
        boolean hasScored = false;
        if (r0 >= 0 && r3 >= 0) {
            // Upper Horizontal Line
            int sideOne = rowStatus[r0][c0];
            // Left Vertical Line
            int sideTwo = colStatus[r0][c0];
            // Right Vertical Line
            int sideThree = colStatus[r3][c3];
            if (sideOne != 0 && sideTwo != 0 && sideThree != 0) {
                System.out.println("Square formed with upper-left vertex {" + r0 + ", " + c0 + "}");
                if (curTurn == 1) {
                    System.out.println("Player One earned one point");
                    playerOneScore++;
                } else {
                    System.out.println("Player Two earned one point");
                    playerTwoScore++;
                }
                hasScored = true;
            }
        }
        // Check Bottom Square
        r0 = r1 + 1;
        r3 = r2 + 1;
        if (r0 < rows && r3 < rows) {
            // Lower Horizontal Line
            int sideOne = rowStatus[r0][c0];
            // Left Vertical Line
            int sideTwo = rowStatus[r1][c1];
            // Right Vertical Line
            int sideThree = colStatus[r2][c2];
            if (sideOne != 0 && sideTwo != 0 && sideThree != 0) {
                System.out.println("Square formed with upper-left vertex {" + r1 + ", " + c1 + "}");
                if (curTurn == 1) {
                    System.out.println("Player One earned one point");
                    playerOneScore++;
                } else {
                    System.out.println("Player Two earned one point");
                    playerTwoScore++;
                }
                hasScored = true;
            }
        }
        return hasScored;
    }

    private boolean inBound(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private boolean isValidConnection(int r1, int c1, int r2, int c2) {
        return (Math.abs(r2 - r1) == 1 && c1 == c2) || (Math.abs(c2 - c1) == 1 && r1 == r2);
    }
}
