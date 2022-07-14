import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();

    void turnRight();

    // Clean the current cell.
    void clean();
}

// LeetCode 489
public class RobotRoomCleaner {
    // TC: O(N - M), where N is the number of cells in the room and M is the number of obstacles
    // SC: O(N - M), we maintained a hash set to store all visited cells
    public static final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public Robot robot;

    public void cleanRoom(Robot robot) {
        Set<List<Integer>> visited = new HashSet<>();
        this.robot = robot;
        backtrack(visited, 0, 0, 0);
    }

    public void backtrack(Set<List<Integer>> visited, int row, int col, int facing) {
        visited.add(Arrays.asList(row, col));
        robot.clean();
        for (int i = 0; i < 4; ++i) {
            int newFacing = (facing + i) % 4;
            int newRow = row + DIRS[newFacing][0];
            int newCol = col + DIRS[newFacing][1];
            if (!visited.contains(Arrays.asList(newRow, newCol)) && robot.move()) {
                backtrack(visited, newRow, newCol, newFacing);
                goBack();
            }
            robot.turnRight();
        }
    }

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
