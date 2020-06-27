public class UniqueBinarySearchTrees {
    /* For a sequence 1, 2, 3, 4, ..., i - 1, i, i + 1, ..., n,
    *  pick one root i, so everything on the left (1 to i - 1) will be the left subtree and
    *  everything on the right (i + 1 to n) will be the right subtree.
    *
    *  So all we have to know is how many ways we can make a tree of size i - 1 and
    *  size n - (i + 1) + 1 in order to compute the final answer. We can do it using dp.
    *  G(n): the number of unique BST for a sequence of length n.
    *  F(i, n): the number of unique BST, where the number i is served as the root of BST.
    *  F(i, n) = G(i - 1) * G(n - i)
    *  G(n) = Sum(from i = 1 to i = n) F(i, n)
    *       = Sum(from i = 1 to i = n)(G(i - 1) * G(n - i))
    */
    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
