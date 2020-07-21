public class RainbowSort3 {
    public int[] rainbowSortIII(int[] array, int k) {
        int[] pointers = new int[k + 1];
        while(pointers[k] < array.length) {
            int target = array[pointers[k]];
            for(int i = k; i - 1 >= target; i--){
                swap(array, pointers[i], pointers[i - 1]);
            }
            for(int i = k; i >= target; i--) {
                pointers[i]++;
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
