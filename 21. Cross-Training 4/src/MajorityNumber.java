public class MajorityNumber {
    public int majority(int[] array) {
        int candidate = array[0];
        int count = 1;
        for (int i = 1; i < array.length; ++i) {
            if (count == 0) {
                candidate = array[i];
                count++;
            } else if (array[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
