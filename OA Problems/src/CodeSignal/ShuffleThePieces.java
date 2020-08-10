package CodeSignal;

import java.util.HashMap;
import java.util.Map;

public class ShuffleThePieces {
    public static void main(String[] args) {
        ShuffleThePieces test = new ShuffleThePieces();
        int[] arr = {1, 2, 5, 3, 6};
        int[][] pieces = {{5}, {1, 2}, {6, 3}};
        System.out.println(test.shuffleThePieces(arr, pieces));
    }

    public boolean shuffleThePieces(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>();
        int totalLen = 0;
        for (int i = 0; i < pieces.length; ++i) {
            int[] piece = pieces[i];
            int start = piece[0];
            map.put(start, i);
            totalLen += piece.length;
        }
        if (totalLen != arr.length) {
            return false;
        }
        int i = 0;
        while (i < arr.length) {
            int cur = arr[i];
            if (!map.containsKey(cur)) {
                return false;
            }
            int index = map.get(cur);
            for (int j = 0; j < pieces[index].length; ++j) {
                if (arr[i++] != pieces[index][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
