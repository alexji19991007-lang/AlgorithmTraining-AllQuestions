// LeetCode 777
public class SwapAdjacentInLRString {
    public static void main(String[] args) {
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";
        SwapAdjacentInLRString test = new SwapAdjacentInLRString();
        System.out.println(test.canTransform(start, end));
    }

    public boolean canTransform(String start, String end) {
        // An important thing to notice is that after transformation, the relative positions of Rs and Ls will not change
        // So if we meet an 'X' in any string we can just skip it and only check those Rs and Ls.
        int p1 = 0, p2 = 0;
        int startXCount = 0, endXCount = 0;
        while (p1 < start.length() || p2 < end.length()) {
            while (p1 < start.length() && start.charAt(p1) == 'X') {
                p1++;
                startXCount++;
            }
            while (p2 < end.length() && end.charAt(p2) == 'X') {
                p2++;
                endXCount++;
            }
            if (p1 == start.length() && p2 == end.length()) {
                return startXCount == endXCount;
            }
            if (p1 == start.length() || p2 == end.length()) {
                return false;
            }
            // If the character is 'L', it can only be moved to the left. p1 should be greater or equal to p2.
            if (start.charAt(p1) != end.charAt(p2)) {
                return false;
            }
            // If the character is 'R', it can only be moved to the right. p2 should be greater or equal to p1.
            if ((start.charAt(p1) == 'L' && p2 > p1) || (start.charAt(p1) == 'R' && p1 > p2)) {
                return false;
            }
            p1++;
            p2++;
        }
        return startXCount == endXCount;
    }
}
