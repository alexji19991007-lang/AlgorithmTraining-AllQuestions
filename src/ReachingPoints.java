// LeetCode 780
public class ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            }
            // Say if tx > ty, then we have to subtract ty from tx until tx becomes
            // smaller than ty, i.e. util tx = tx % ty.
            // Note that if ty <= sy, then ty should not change any more, so all we have
            // to do is just check if the difference between the current tx and sx is a
            // multiple of ty, which means to check if we can continuously subtract ty from
            // tx and finally make tx = sx.
            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                } else {
                    return (tx - sx) % ty == 0;
                }
            } else {
                if (tx > sx) {
                    ty %= tx;
                } else {
                    return (ty - sy) % tx == 0;
                }
            }
        }
        return tx == sx && ty == sy;
    }
}
