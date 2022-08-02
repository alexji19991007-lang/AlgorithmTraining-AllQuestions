package Codility;

public class LightBulbs {
    public int countMoments(int[] A) {
        int res = 0;
        int rightMostLightBulb = 0;
        for (int i = 0; i < A.length; ++i) {
            rightMostLightBulb = Math.max(rightMostLightBulb, A[i]);
            if (rightMostLightBulb == i + 1) {
                res++;
            }
        }
        return res;
    }
}
