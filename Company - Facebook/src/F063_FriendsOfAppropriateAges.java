import java.util.HashMap;
import java.util.Map;

// LeetCode 825
public class F063_FriendsOfAppropriateAges {
    // Method 1: Use two loops
    // TC: O(n + 121^2) = O(n) where n is the length of the array
    // SC: O(121) = O(1)
    public int numFriendRequests(int[] ages) {
        // We need to use a hash map if the ages are not within [0, 120]
        int[] count = new int[121];
        for (int age : ages) {
            count[age]++;
        }
        int res = 0;
        for (int a = 0; a <= 120; ++a) {
            if (count[a] == 0) continue;
            for (int b = 0; b <= 120; ++b) {
                if (count[b] == 0) continue;
                if (req(a, b)) {
                    // For each age a and each age b != a, if request(a, b), we will make count[a] * count[b] requests.
                    // For each age a, if request(a, a), we will make count[a] * (count[a] - 1) requests.
                    res += count[a] * (count[b] - (a == b ? 1 : 0));
                }
            }
        }
        return res;
    }

    private boolean req(int a, int b) {
        return b > 0.5 * a + 7 && b <= a;
    }

    // Method 2: prefix sum
    // Since the condition for A to request B as a friend is 0.5 * a + 7 < b <= a
    // b should be in range (0.5a + 7 , a]. And 0.5a + 7 < a --> a > 14 && b > 14
    // TC: O(n)
    // SC: O(121) = O(1)
    public int numFriendRequests_prefixSum(int[] ages) {
        int res = 0;
        int[] numInAge = new int[121], sumInAge = new int[121];
        for (int age : ages) {
            numInAge[age]++;
        }
        // sumInAge[i] = # of people with ages smaller than or equal to i
        // we can ignore people with ages smaller than 15 as the will never be requested or send a request
        for (int i = 15; i <= 120; ++i) {
            sumInAge[i] = sumInAge[i - 1] + numInAge[i];
        }
        // Remember a > 14
        for (int i = 15; i <= 120; ++i) {
            if (numInAge[i] == 0) continue;
            // # of people in the age range(0.5a + 7, a]
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
            // exclude oneself
            res += (count - 1) * numInAge[i];
        }
        return res;
    }
}
