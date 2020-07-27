/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * // Constructor initializes an empty nested list.
 * public NestedInteger();
 * <p>
 * // Constructor initializes a single integer.
 * public NestedInteger(int value);
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // Set this NestedInteger to hold a single integer.
 * public void setInteger(int value);
 * <p>
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * public void add(NestedInteger ni);
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
// LeetCode 339
public class F043_NestedListWeightSum {
    // TC: O(n)
    // SC: O(d) where d is the maximum depth
//    public int depthSum(List<NestedInteger> nestedList) {
//        return sumHelper(nestedList, 1);
//    }
//
//    public int sumHelper(List<NestedInteger> nestedList, int depth) {
//        int sum = 0;
//        for (NestedInteger n : nestedList) {
//            if (n.isInteger()) {
//                sum += n.getInteger() * depth;
//            } else {
//                sum += sumHelper(n.getList(), depth + 1);
//            }
//        }
//        return sum;
//    }
}

