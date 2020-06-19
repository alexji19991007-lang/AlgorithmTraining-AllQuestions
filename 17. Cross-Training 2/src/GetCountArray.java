public class GetCountArray {
    public int[] countArray(int[] array) {
        int[] indices = new int[array.length];
        int[] count = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            indices[i] = i;
        }
        mergeSort(array, indices, count, 0, array.length - 1);
        return count;
    }

    public void mergeSort(int[] array, int[] indices, int[] count, int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(array, indices, count, start, mid);
        mergeSort(array, indices, count, mid + 1, end);
        merge(array, indices, count, start, end);
    }

    public void merge(int[] array, int[] indices, int[] count, int start, int end) {
        int mid = start + (end - start) / 2;
        int leftIndex = start, rightIndex = mid + 1;
        // Use a new array to hold the new indexes after sorting
        int[] newIndex = new int[end - start + 1];
        // rightCount counts how many numbers are smaller than the elements on the left half
        int rightCount = 0;
        int cur = 0;
        while (leftIndex <= mid && rightIndex <= end) {
            // if the current element on left half is greater than
            // the current element on right half
            if (array[indices[leftIndex]] > array[indices[rightIndex]]) {
                // put the index of the right half element to the newIndex
                // array (right element is smaller so it should be sorted
                // before the left element)
                newIndex[cur++] = indices[rightIndex++];
                rightCount++;
            }
            // the current left half element is smaller, so we cannot find any elements on the
            // right half that is smaller than the current left half element
            else {
                count[indices[leftIndex]] += rightCount;
                newIndex[cur++] = indices[leftIndex++];
            }
        }
        while (leftIndex <= mid) {
            newIndex[cur] = indices[leftIndex];
            count[indices[leftIndex]] += rightCount;
            leftIndex++;
            cur++;
        }
        while (rightIndex <= end) {
            newIndex[cur++] = indices[rightIndex++];
        }
        // copy the newIndex array into the original indexes array
        for (int i = 0; i < newIndex.length; ++i) {
            indices[start] = newIndex[i];
            start++;
        }
    }
}
