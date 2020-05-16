public class SelectionSort {
    public int[] solve(int[] array) {
        for (int i = 0; i < array.length; ++ i) {
            int minIndex = i;
            for (int j = i; j < array.length; ++j) {
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
