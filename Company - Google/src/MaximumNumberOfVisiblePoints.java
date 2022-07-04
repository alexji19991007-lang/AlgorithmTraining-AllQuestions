import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// LeetCode 1610
public class MaximumNumberOfVisiblePoints {
    // TC: O(N*log(N))
    // SC: O(N)
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int samePoint = 0;
        for (List<Integer> p : points) {
            int dx = p.get(0) - location.get(0);
            int dy = p.get(1) - location.get(1);
            if (dx == 0 && dy == 0) {
                samePoint++;
                continue;
            }
            // Math.atan2(dy, dx) gives the angle between the two points' line and the positive x-axis in polar coordinates.
            // Since the value ranges from -PI to +PI, we need to convert it to actual angles by multiplying (180 / Math.PI).
            angles.add(Math.atan2(dy, dx) * (180 / Math.PI));
        }
        Collections.sort(angles);
        // Suppose the sorted list is [-179, -92, -10, 7, 55, 164, 167] and your vision angle is 90, we can actually
        // see [-179, 167, 164] together but the algorithm will not be able to detect that. So we need to add 360 to each
        // angle and append the new values to the original list. Surely some new values are redundant but that helps us
        // discover all possible cases.
        int listSize = angles.size();
        for (int i = 0; i < listSize; ++i) {
            angles.add(angles.get(i) + 360);
        }
        int res = samePoint;
        for (int i = 0, j = 0; i < angles.size(); ++i) {
            while (angles.get(i) - angles.get(j) > angle) {
                j++;
            }
            res = Math.max(res, samePoint + i - j + 1);
        }
        return res;
    }
}
