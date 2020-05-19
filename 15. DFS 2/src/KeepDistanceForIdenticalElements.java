public class KeepDistanceForIdenticalElements {
    public int[] keepDistance(int k) {
        int[] array = new int[2 * k];
        for (int i = 0; i < k; ++i) {
            array[i * 2] = i + 1;
            array[i * 2 + 1] = i + 1;
        }
        boolean[] used = new boolean[k + 1];
        return helper(array, 0, used) ? array : null;
    }

    public boolean helper(int[] array, int index, boolean[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = index; i < array.length; ++i) {
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                swap(array, index, i);
                used[cur] = !used[cur];
                if (helper(array, index + 1, used)) {
                    return true;
                }
                swap(array, index, i);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    public void swap(int[] array, int left, int right) {
        int temp = array[right];
        array[right] = array[left];
        array[left] = temp;
    }
}
