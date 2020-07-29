import java.util.Arrays;

public class MeanderingArray {
    public static void main(String[] args) {
        MeanderingArray test = new MeanderingArray();
        int[] array = new int[]{1, 1, 2, 2, 3, 4, 5, 6, 7};
        test.rearrange(array, 9);
        System.out.println(Arrays.toString(array));
    }

    public void rearrange(int[] arr, int n) {
        // initialize index of first minimum and first 
        // maximum element 
        int maxIndex = n - 1, minIndex = 0;
        // store maximum element of array 
        int maxElement = arr[n - 1] + 1;
        // traverse array elements 
        for (int i = 0; i < n; i++) {
            // at even index : we have to put 
            // maximum element 
            if (i % 2 == 0) {
                arr[i] += (arr[maxIndex] % maxElement) * maxElement;
                maxIndex--;
            }
            // at odd index : we have to put minimum element 
            else {
                arr[i] += (arr[minIndex] % maxElement) * maxElement;
                minIndex++;
            }
        }
        // array elements back to it's original form 
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / maxElement;
        }
    }
}
