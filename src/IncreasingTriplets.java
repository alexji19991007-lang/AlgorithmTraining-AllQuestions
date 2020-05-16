public class IncreasingTriplets {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } else if (n <= big) {
                // big only gets updated when there exists a small that comes before it.
                big = n;
            } else {
                // 如果我们发现一个数字比当前的small和big都大，则我们发现了triplets
                return true;
            }
        }
        return false;
    }
    // Special case: 1 3 0 5 (最终small = 0， big = 3, small comes after big, seems like a violation)
    // If you observe carefully, the moment we updated big from MAX to some other value, that means
    // that there clearly was a value less than it (which would have been assigned to small in the
    // past). What this means is that once you find a value bigger than big, you've implicitly found
    // an increasing triplet.
}
