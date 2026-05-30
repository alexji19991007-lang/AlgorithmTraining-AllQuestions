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
    // iteration i = 0: inner (0..n-1) = n
    // iteration i = 1: inner n-1 (1..n-1) = n - 1
    // iteration i = 2: inner n-2 (2..n-1) = n - 2
    // iteration i = 3: inner n-3 (3..n-1) = n - 3
    // .....
    // 1+2+3+4+..+n = n(n+1)/2 -> n^2 → O(n^2)

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
