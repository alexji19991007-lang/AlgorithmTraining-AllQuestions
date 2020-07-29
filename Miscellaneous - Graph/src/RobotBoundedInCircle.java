// LeetCode 1041
public class RobotBoundedInCircle {
    // North, East, South, West
    public static final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 2 Cases for a limit cycle trajectory
    // Case 1: If the robot returns to the initial point after one cycle
    // Case 2: If the robot doesn't face north at the end of the first cycle

    // TC: O(n), where n = instructions.length()
    // SC: O(1)
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int dir = 0; // initially facing north
        for (int i = 0; i < instructions.length(); ++i) {
            char curIns = instructions.charAt(i);
            if (curIns == 'R') {
                dir = (dir + 1) % 4;
            } else if (curIns == 'L') {
                dir = dir - 1 < 0 ? 3 : dir - 1;
            } else {
                x += DIRS[dir][0];
                y += DIRS[dir][1];
            }
        }
        return (x == 0 && y == 0) || dir != 0;
    }
}
