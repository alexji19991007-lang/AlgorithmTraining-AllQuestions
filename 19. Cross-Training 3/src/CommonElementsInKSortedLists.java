import java.util.ArrayList;
import java.util.List;

public class CommonElementsInKSortedLists {
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> res = input.get(0);
        for (int i = 1; i < input.size(); ++i) {
            if (res.size() == 0) {
                break;
            }
            res = helper(res, input.get(i));
        }
        return res;
    }

    public List<Integer> helper(List<Integer> a, List<Integer> b) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            int compare = a.get(i).compareTo(b.get(j));
            if (compare == 0) {
                res.add(a.get(i));
                i++;
                j++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
