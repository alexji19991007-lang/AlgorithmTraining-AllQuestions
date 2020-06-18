import java.util.ArrayList;
import java.util.List;

public class LargestAndSecondLargest {
    static class Element {
        int value;
        List<Integer> comparedVal;

        Element(int value) {
            this.value = value;
            this.comparedVal = new ArrayList<>();
        }
    }
    public int[] largestAndSecond(int[] array) {
        Element[] helper = convert(array);
        int largerLength = array.length;
        while (largerLength > 1) {
            compareAndSwap(helper, largerLength);
            largerLength = (largerLength + 1) / 2;
        }
        return new int[] { helper[0].value, largest(helper[0].comparedVal) };
    }

    public Element[] convert(int[] array) {
        Element[] res = new Element[array.length];
        for (int i = 0; i < array.length; ++i) {
            res[i] = new Element(array[i]);
        }
        return res;
    }

    public void compareAndSwap(Element[] helper, int largerLength) {
        for (int i = 0; i < largerLength / 2; ++i) {
            if (helper[i].value < helper[largerLength - 1 - i].value) {
                swap(helper, i, largerLength - 1 - i);
            }
            helper[i].comparedVal.add(helper[largerLength - 1 - i].value);
        }
    }

    public int largest(List<Integer> lst) {
        int max = lst.get(0);
        for (int num : lst) {
            max = Math.max(max, num);
        }
        return max;
    }

    public void swap(Element[] array, int left, int right) {
        Element temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
