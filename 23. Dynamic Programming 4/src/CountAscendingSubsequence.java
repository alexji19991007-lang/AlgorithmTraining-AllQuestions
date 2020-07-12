public class CountAscendingSubsequence {
    public int numIncreasingSubsequences(int[] a) {
        if (a.length <= 1) {
            return a.length;
        }
        int count = 1;
        int[] M = new int[a.length];
        M[0] = 1;
        for (int i = 1; i < a.length; ++i) {
            M[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (a[i] > a[j]) {
                    M[i] += M[j];
                }
            }
            count += M[i];
        }
        return count;
    }
}
