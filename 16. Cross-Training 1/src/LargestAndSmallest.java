public class LargestAndSmallest {
    public int[] largestAndSmallest(int[] array) {
        int n = array.length;
        for (int i = 0; i < n / 2; ++i) {
            if (array[i] < array[n - 1 - i]) {
                swap(array, i, n - 1 - i);
            }
        }
        return new int[] { largest(array, 0, (n - 1) / 2), smallest(array, n / 2, n - 1) };
    }

    public int largest(int[] array, int left, int right) {
        int largest = array[left];
        for (int i = left + 1; i <= right; ++i) {
            largest = Math.max(largest, array[i]);
        }
        return largest;
    }

    public int smallest(int[] array, int left, int right) {
        int smallest = array[left];
        for (int i = left + 1; i <= right; ++i) {
            smallest = Math.min(smallest, array[i]);
        }
        return smallest;
    }

    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
