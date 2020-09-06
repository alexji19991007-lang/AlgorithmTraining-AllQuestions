import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InitialPublicOffering {
    public static void main(String[] args) {
        int[][] bids = new int[][]{{1, 5, 5, 0}, {2, 7, 8, 1}, {3, 7, 5, 1}, {4, 10, 3, 3}};
        InitialPublicOffering test = new InitialPublicOffering();
        System.out.println(test.unAwarded(bids, 18).toString());
    }

    public List<Integer> unAwarded(int[][] bids, int totalShares) {
        List<Integer> noShares = new ArrayList<>();
        Arrays.sort(bids, (i1, i2) -> {
            if (i1[2] == i2[2]) {
                return i1[3] < i2[3] ? -1 : 1;
            }
            return i1[2] > i2[2] ? -1 : 1;
        });
        for (int[] bid : bids) {
            if (totalShares <= 0) {
                noShares.add(bid[0]);
            } else {
                totalShares -= bid[1];
            }
        }
        Collections.sort(noShares);
        return noShares;
    }
}
