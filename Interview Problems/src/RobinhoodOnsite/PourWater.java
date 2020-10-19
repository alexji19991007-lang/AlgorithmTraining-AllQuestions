package RobinhoodOnsite;

public class PourWater {
    public int[] pourWater(int[] heights, int V, int K) {
        int n = heights.length;
        int cur = K;
        // cur is outside of the loop because we don't need to start scanning at K each time.
        // Water tends to fall back to similar position based on the rule defined in the question.
        // Therefore we only need to start scanning at where cur previously is, scanning left then
        // right to see if the next drop can 'get out' of the local minima. And the only way for
        // cur to get out of local minima is until it gets fully filled.
        for (int i = 0; i < V; ++i) {
            while (cur > 0 && heights[cur] >= heights[cur - 1]) {
                cur--;
            }
            while (cur < n - 1 && heights[cur] >= heights[cur + 1]) {
                cur++;
            }
            while (cur > K && heights[cur] == heights[cur - 1]) {
                cur--;
            }
            heights[cur]++;
        }
        return heights;
    }
}
