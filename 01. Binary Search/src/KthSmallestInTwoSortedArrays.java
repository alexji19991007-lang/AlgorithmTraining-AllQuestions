// k = 8
// 0 1 2 3 4  5  6  7  8
// 2 4 6 8 10 12 14 16 18
// 1 3 5 7 9  11 13 15 17

// Round 1 -- k = 8
// 2 4 6 8 10 12 14 16 18
//         ^
// 1 3 5 7 9  11 13 15 17
//         ^
// 9 < 10 --> 9 and all its left elements cannot be the answer


// Round 2 -- k = 4
// 2 4 6 8 10 12 14 16 18
//   ^
// x x x x 9  11 13 15 17
//            ^
// 4 < 11 --> 4 and all its left elements cannot be the answer

// Round 3 -- k = 2
// x x 6 8 10 12 14 16 18
//     ^
// x x x x 9  11 13 15 17
//         ^
// 6 < 9 --> 6 and all its left elements cannot be the answer

// Round 4 -- k = 1
// x x x 8 10 12 14 16 18
//       ^
// x x x x 9  11 13 15 17
//         ^
// 8 < 9 --> since k = 1 8 is the final answer
public class KthSmallestInTwoSortedArrays {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {5};
        System.out.println(kth(a, b, 5));
    }

    public static int kth(int[] a, int[] b, int k) {
        // Write your solution here
        return kthHelper(a, 0, b, 0, k);
    }

    public static int kthHelper(int[] a, int aLeft, int[] b, int bLeft, int k) {
        // base cases
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        // Since index starts from aLeft / bLeft, the k/2-th element should be left + k/2 - 1
        int aMid = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;
        // if a.length to small, then remove elements from b first
        if (bMid >= b.length || (aMid < a.length && a[aMid] <= b[bMid])) {
            return kthHelper(a, aMid + 1, b, bLeft, k - k / 2);
        }
        return kthHelper(a, aLeft, b, bMid + 1, k - k / 2);
    }
}



