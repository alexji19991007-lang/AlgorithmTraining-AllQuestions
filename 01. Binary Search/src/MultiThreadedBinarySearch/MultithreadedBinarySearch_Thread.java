package MultiThreadedBinarySearch;

public class MultithreadedBinarySearch_Thread {
    public static int search(int[] array, int target) throws InterruptedException {
        BinarySearchThread mainThread = new BinarySearchThread(array, 0, array.length - 1, target);
        mainThread.start();
        mainThread.join();
        return mainThread.result;
    }

    private static class BinarySearchThread extends Thread {
        private final int[] array;
        private final int left;
        private final int right;
        private final int target;
        private int result = -1;

        public BinarySearchThread(int[] array, int left, int right, int target) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.target = target;
        }

        @Override
        public void run() {
            if (left > right) {
                result = -1;
                return;
            }
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                result = mid;
                return;
            } else if (array[mid] < target) {
                // Right half search
                BinarySearchThread rightThread = new BinarySearchThread(array, mid + 1, right, target);
                rightThread.start();
                try {
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = rightThread.result;
            } else {
                // Left half search
                BinarySearchThread leftThread = new BinarySearchThread(array, left, mid - 1, target);
                leftThread.start();
                try {
                    leftThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = leftThread.result;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;
        try {
            int result = search(array, target);
            System.out.println(result); // Should print the index of target (6 in this case)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}