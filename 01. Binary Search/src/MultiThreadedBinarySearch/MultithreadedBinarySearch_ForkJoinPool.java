package MultiThreadedBinarySearch;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class MultithreadedBinarySearch_ForkJoinPool {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static int search(int[] array, int target) {
        return forkJoinPool.invoke(new BinarySearchTask(array, 0, array.length - 1, target));
    }

    private static class BinarySearchTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int left;
        private final int right;
        private final int target;

        public BinarySearchTask(int[] array, int left, int right, int target) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.target = target;
        }

        @Override
        protected Integer compute() {
            if (left > right) {
                return -1;
            }
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                // Right half search
                BinarySearchTask rightTask = new BinarySearchTask(array, mid + 1, right, target);
                rightTask.fork(); // Forks the right half search to another thread
                return rightTask.join(); // Wait for the result
            } else {
                // Left half search
                BinarySearchTask leftTask = new BinarySearchTask(array, left, mid - 1, target);
                leftTask.fork(); // Forks the left half search to another thread
                return leftTask.join(); // Wait for the result
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;
        int result = search(array, target);
        System.out.println(result); // Should print the index of target (6 in this case)
    }
}