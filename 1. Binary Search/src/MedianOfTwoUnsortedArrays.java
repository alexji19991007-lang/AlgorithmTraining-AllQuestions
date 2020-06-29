public class MedianOfTwoUnsortedArrays {
    public static void main(String[] args) {
        int[] a = {2,4,9,1,3,7};
        int[] b = {};
        MedianOfTwoUnsortedArrays test = new MedianOfTwoUnsortedArrays();
        System.out.println(test.median(a, b));
    }

    public double median(int[] a, int[] b) {
        if (a.length == 0) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        int length = a.length + b.length;
        int leftMidIndex = (length - 1) / 2, rightMidIndex = length / 2;
        int left = 0, right = length - 1;
        boolean findLeftMid = false, findRightMid = false;
        while (!findLeftMid || !findRightMid) {
            int pivotIndex = partition(a, b, left, right);
            if (pivotIndex == leftMidIndex) {
                findLeftMid = true;
            }
            if (pivotIndex == rightMidIndex) {
                findRightMid = true;
            }
            if (pivotIndex <= leftMidIndex) {
                left = pivotIndex + 1;
            }
            if (pivotIndex > rightMidIndex) {
                right = pivotIndex + 1;
            }
        }
        return (getNum(a, b, leftMidIndex) + getNum(a, b, rightMidIndex)) / 2.0;
    }

    private int partition(int[] a, int[] b, int left, int right) {
        int pivot = right;
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (getNum(a, b, i) < getNum(a, b, right)) {
                i++;
            } else if (getNum(a, b, j) >= getNum(a, b, right)) {
                j--;
            } else {
                swap(a, b, i++, j++);
            }
        }
        swap(a, b, i, pivot);
        return i;
    }

    private void swap(int[] a, int[] b, int i, int j) {
        int elementI = getNum(a, b, i);
        int elementJ = getNum(a, b, j);
        setNum(a, b, i, elementJ);
        setNum(a, b, j, elementI);
    }

    private int getNum(int[] a, int[] b, int i) {
        return i < a.length ? a[i] : b[i - a.length];
    }

    private void setNum(int[] a, int[] b, int i, int x) {
        if (i < a.length) {
            a[i] = x;
        } else {
            b[i - a.length] = x;
        }
    }
}
