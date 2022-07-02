import java.util.Random;

public class MedianOfTwoUnsortedArrays {
    public static void main(String[] args) {
        int[] a = {9, 11, 5, 10, 8};
        int[] b = {3, 4, 2};
        MedianOfTwoUnsortedArrays test = new MedianOfTwoUnsortedArrays();
        System.out.println(test.median(a, b));
    }

    public double median(int[] a, int[] b) {
        // smallestLargerThanTarget[0]: 在element总数为偶数的情况下，大于median的最小的数字。这个数字将会被用来计算真正的median
        // smallestLargerThanTarget[1]: 每次partition结束后，大于当前pivot的最小的数字
        int[] smallestLargerThanTarget = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int left = 0, right = a.length + b.length - 1;
        int targetIndex = right / 2;
        boolean findTarget = false;

        while (!findTarget) {
            // quick select:
            // 随机选取一个pivot，把所有大于pivot的数字放到pivot后面，所有小于pivot的数字放到pivot前面，返回partition后pivot所在的index
            int pivotIndex = partition(a, b, left, right, smallestLargerThanTarget);
            if (pivotIndex == targetIndex) {
                // 如果pivot所在的index正好是median的index
                if (targetIndex < right) {
                    // right > target说明在这次partition里面存在比pivot大的数字
                    smallestLargerThanTarget[0] = smallestLargerThanTarget[1];
                }
                findTarget = true;
            } else if (pivotIndex > targetIndex) {
                // 如果pivot所在的index大于median的index，我们下一次partition只关注比当前pivot小的数字
                // (i.e. elements to the left of pivot)
                smallestLargerThanTarget[0] = getValue(a, b, pivotIndex);
                right = pivotIndex - 1;
            } else {
                // 如果pivot所在的index小于median的index，我们下一次partition只关注比当前pivot大的数字
                // (i.e. elements to the right of pivot)
                left = pivotIndex + 1;
            }
        }

        int targetValue = getValue(a, b, targetIndex);
        return (a.length + b.length) % 2 == 0 ? (smallestLargerThanTarget[0] + targetValue) / 2.0 : targetValue;
    }

    private int partition(int[] a, int[] b, int left, int right, int[] smallestLargerThanTarget) {
        Random rand = new Random();
        int randomIndex = left + rand.nextInt(right - left + 1);
        int pivot = getValue(a, b, randomIndex);
        swap(a, b, randomIndex, right);
        int start = left, end = right - 1;
        smallestLargerThanTarget[1] = Integer.MAX_VALUE;
        while (start <= end) {
            int value = getValue(a, b, start);
            if (value < pivot) {
                start++;
            } else {
                smallestLargerThanTarget[1] = Math.min(smallestLargerThanTarget[1], value);
                swap(a, b, start, end--);
            }
        }
        swap(a, b, start, right);
        return start;
    }

    private void swap(int[] a, int[] b, int left, int right) {
        int tmp = getValue(a, b, left);
        setValue(a, b, left, getValue(a, b, right));
        setValue(a, b, right, tmp);
    }

    private int getValue(int[] a, int[] b, int index) {
        // 我们把a和b的index合并起来看，从而更方便将a和b同时作为一个整体进行partition。
        // 如果a的长度是4，b的长度是5，那么getValue(6)实际上得到的是b[6 - 4] = b[2]的element
        return index >= a.length ? b[index - a.length] : a[index];
    }

    private void setValue(int[] a, int[] b, int index, int value) {
        if (index < a.length) {
            a[index] = value;
        } else {
            b[index - a.length] = value;
        }
    }
}
