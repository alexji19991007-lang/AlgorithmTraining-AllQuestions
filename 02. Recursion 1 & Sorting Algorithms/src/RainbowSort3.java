import java.util.Arrays;

public class RainbowSort3 {
    public static void main(String[] args) {
        RainbowSort3 test = new RainbowSort3();
        System.out.println(Arrays.toString(test.rainbowSortIII(new int[]{1, 3, 4, 2, 5, 2, 1}, 5)));
    }

    public int[] rainbowSortIII(int[] array, int k) {
        if (k == 1) {
            return array;
        }
        int[] pointers = new int[k];
        // Two qualities:
        // From pointers[0] to pointers[k - 2]:
        //         All elements to the left of pointers[i] should be less than k + 1;
        // For pointers[k - 1]:
        //         All elements to the right of pointers[k - 1] should be k.
        //
        // The array region from index 0 to pointers[k - 2] & from index
        // pointers[k - 2] to pointers[k - 1] must satisfy the above two qualities.
        // The array region from index pointers[k - 2] to pointers[k - 1] is unexplored.
        pointers[k - 1] = array.length - 1;
        while (pointers[k - 2] <= pointers[k - 1]) {
            int target = array[pointers[k - 2]];
            if (target == k) {
                swap(array, pointers[k - 2], pointers[k - 1]);
                pointers[k - 1]--;
            } else {
                // From the pointers[k - 2], swap until the above two qualities restore
                for (int i = k - 2; i >= target; --i) {
                    swap(array, pointers[i], pointers[i - 1]);
                }
                // For every pointer that experiences a swap, move ahead by one step
                for (int i = k - 2; i >= target - 1; --i) {
                    pointers[i]++;
                }
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
