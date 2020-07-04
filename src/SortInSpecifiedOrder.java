import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortInSpecifiedOrder {
    static class MyComparator implements Comparator<Integer> {
        private Map<Integer, Integer> map;

        public MyComparator(int[] array) {
            map = new HashMap<>();
            for (int i = 0; i < array.length; ++i) {
                map.put(array[i], i);
            }
        }

        @Override
        public int compare(Integer i1, Integer i2) {
            Integer index1 = map.get(i1);
            Integer index2 = map.get(i2);
            if (index1 != null && index2 != null) {
                return index1.compareTo(index2);
            } else if (index1 == null && index2 == null) {
                return i1.compareTo(i2);
            }
            return index1 != null ? -1 : 1;
        }
    }

    public int[] sortSpecial(int[] A1, int[] A2) {
        Integer[] refArray = toRefArray(A1);
        Arrays.sort(refArray, new MyComparator(A2));
        for (int i = 0; i < A1.length; ++i) {
            A1[i] = refArray[i];
        }
        return A1;
    }

    public Integer[] toRefArray(int[] A1) {
        Integer[] refArray = new Integer[A1.length];
        for (int i = 0; i < A1.length; ++i) {
            refArray[i] = A1[i];
        }
        return refArray;
    }
}
