public class ComputeArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // If min(C, G) < left, no overlap
        int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
        // If min(D, H) < bottom, no overlap
        int bottom = Math.max(B, F), top = Math.max(Math.min(D, H), bottom);
        return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
    }
}
