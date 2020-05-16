import java.util.*;

public class MinHeap {
    private int[] mHeap;
    private int mSize;
    private int mCapacity;

    public MinHeap() {
        this.mHeap = new int[16];
        this.mSize = 0;
        this.mCapacity = 16;
    }

    public MinHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input Array Cannot Be Null Or Empty.");
        }
        this.mHeap = array;
        this.mSize = array.length;
        this.mCapacity = array.length;
        heapify();
    }

    public MinHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Capacity Cannot Be Less Than Or Equal To 0");
        }
        this.mHeap = new int[cap];
        this.mSize = 0;
        this.mCapacity = cap;
    }

    private void heapify() {
        for (int i = mSize / 2 - 1; i >= 0; --i) {
            percolateDown(i);
        }
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (mHeap[parentIndex] > mHeap[index]) {
                swap(mHeap, index, parentIndex);
            } else {
                break;
            }
            index = parentIndex;
        }
    }

    private void percolateDown(int index) {
        while (index <= mSize / 2 - 1) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            int indexToSwap = index;
            if (leftIndex <= mSize - 1 && mHeap[leftIndex] < mHeap[indexToSwap]) {
                indexToSwap = leftIndex;
            }
            if (rightIndex <= mSize - 1 && mHeap[rightIndex] < mHeap[indexToSwap]) {
                indexToSwap = rightIndex;
            }
            if (indexToSwap == index) {
                break;
            } else {
                swap(mHeap, index, indexToSwap);
            }
            index = indexToSwap;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Heap");
        }
        return mHeap[0];
    }

    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Heap");
        }
        int res = mHeap[0];
        mHeap[0] = mHeap[mSize - 1];
        mSize--;
        percolateDown(0);
        return res;
    }

    public void offer(int element) {
        if (isFull()) {
            mCapacity *= 1.5;
            int[] newHeap = new int[mCapacity];
            if (mSize >= 0) System.arraycopy(mHeap, 0, newHeap, 0, mSize);
            mHeap = newHeap;
        }
        mHeap[mSize++] = element;
        percolateUp(mSize - 1);
    }

    // Return the original value
    public int update(int index, int element) {
        if (index < 0 || index > mSize - 1) {
            throw new ArrayIndexOutOfBoundsException("Invalid Index");
        }
        int res = mHeap[index];
        mHeap[index] = element;
        if (element < res) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return res;
    }

    public int size() {
        return mSize;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public boolean isFull() {
        return mSize == mCapacity;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
