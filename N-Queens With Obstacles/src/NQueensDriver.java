import java.util.*;

public class NQueensDriver {
    public static void main(String[] args) {
        // Put obstacles.
        List<Coordinate> obstacles = new ArrayList<>();
        obstacles.add(new Coordinate(0, 7));

        // Initialize the game object.
        NQueensWithObstacles game = new NQueensWithObstacles(8, obstacles);

        // Print out the board.
        System.out.println("Board View:");
        game.printBoard();
        System.out.println();

        // Print out the solution sets.
        System.out.println("Solution Sets:");
        List<List<Coordinate>> solutionSet = game.findAllSolutions();
        int solutionNumber = 1;
        for (List<Coordinate> list : solutionSet) {
            System.out.print("Solution " + solutionNumber++ + ": ");
            for (Coordinate c : list) {
                System.out.print(c.toString() + " ");
            }
            System.out.println();
        }
    }
}
