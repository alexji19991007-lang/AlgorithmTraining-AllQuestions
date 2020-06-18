public class Move0sToTheEnd {
    // Preserve Original Relative Order
    public int[] moveZero(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int slow = 0;
        for (int fast = 1; fast < array.length; ++fast) {
            if (array[slow] != 0) {
                slow++;
            } else if (array[fast] != 0) {
                swap(array, slow, fast);
                slow++;
            }
        }
        return array;
    }

    public void swap(int[] array, int slow, int fast) {
        int temp = array[slow];
        array[slow] = array[fast];
        array[fast] = temp;
    }
}
