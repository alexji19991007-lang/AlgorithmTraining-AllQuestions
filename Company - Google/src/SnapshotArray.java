import java.util.ArrayList;
import java.util.List;

// LeetCode 1146
public class SnapshotArray {
    private List<int[]>[] array;
    private int id;

    public SnapshotArray(int length) {
        array = new List[length];
        for (int i = 0; i < length; ++i) {
            array[i] = new ArrayList<>();
            array[i].add(new int[]{-1, 0});
        }
    }

    public void set(int index, int val) {
        array[index].add(new int[]{id, val});
    }

    public int snap() {
        return id++;
    }

    public int get(int index, int snap_id) {
        List<int[]> temp = array[index];
        int left = 0, right = temp.size() - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            int curSnapId = temp.get(mid)[0];
            if (curSnapId <= snap_id) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return temp.get(left)[1];
    }
}
